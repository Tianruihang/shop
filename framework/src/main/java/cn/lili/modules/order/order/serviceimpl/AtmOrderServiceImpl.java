package cn.lili.modules.order.order.serviceimpl;

import cn.hutool.core.convert.Convert;
import cn.lili.common.enums.ResultCode;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.utils.StringUtils;
import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dos.AtmUserMachine;
import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import cn.lili.modules.order.order.entity.dto.AtmOrderDTO;
import cn.lili.modules.order.order.entity.dto.AtmOrderSearchParams;
import cn.lili.modules.order.order.entity.dto.ExchangeRuleSearchParams;
import cn.lili.modules.order.order.mapper.AtmOrderMapper;
import cn.lili.modules.order.order.service.AtmOrderService;
import cn.lili.modules.order.order.service.ExchangeRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class AtmOrderServiceImpl extends ServiceImpl<AtmOrderMapper, AtmOrder> implements AtmOrderService {

    @Autowired
    private AtmOrderMapper atmOrderMapper;
    @Autowired
    private ExchangeRuleService exchangeRuleService;
    @Autowired
    private AtmUserPointsService atmUserPointsService;

    @Override
    public List<AtmOrderDTO> queryList(AtmOrderSearchParams atmOrderSearchParams) {
        //AtmOrderSearchParams 转化为 AtmOrder
        AtmOrderDTO atmOrder = Convert.convert(AtmOrderDTO.class, atmOrderSearchParams);
        return atmOrderMapper.queryList(atmOrder.queryWrapper());
    }

    @Override
    public int createBuyOrder(AtmOrder atmOrder) {
        AuthUser currentUser = UserContext.getCurrentUser();
        //查询用户等级
        AtmUserPoints atmUserPoints = atmUserPointsService.queryByUserId(currentUser.getId());
        if (atmOrder.getType() == 1){
            //查询用户等级规则

        }
        //获取交易规则 查询订单类型 0 平价区 1 溢价区
        ExchangeRuleSearchParams exchangeRuleSearchParams = new ExchangeRuleSearchParams();
        exchangeRuleSearchParams.setType(atmOrder.getType());
        ExchangeRule exchangeRule = exchangeRuleService.queryLast(exchangeRuleSearchParams);

        atmOrder.setUserId(currentUser.getId());
        atmOrder.setCharge(exchangeRule.getCharge());
        atmOrder.setStatus(0);
        return this.baseMapper.insert(atmOrder);
    }

    @Override
    public int payOrder(AtmOrder atmOrder) {
        AtmOrder atmOrder1 = this.baseMapper.selectById(atmOrder.getId());
        if (StringUtils.isNotEmpty(atmOrder.getPayImg()))
            atmOrder1.setPayImg(atmOrder.getPayImg());
        //查询用户余额是否足够
        AtmUserPoints atmUserPointsFault = atmUserPointsService.queryByUserId(atmOrder1.getUserId());
        //检查用户钱包余额是否大于订单售卖金额*订单数量
        if (atmUserPointsFault.getWallet().compareTo(atmOrder1.getSellPrice().multiply(new BigDecimal(atmOrder1.getNum()))) < 0) {
            throw new ServiceException(ResultCode.USER_POINTS_NOT_ENOUGH);
        }
        //计算支付价格= 交易价格 * 手续费 / 100
        BigDecimal payPrice = atmOrder1.getSellPrice().multiply(atmOrder1.getCharge()).divide(new BigDecimal(100));
        atmOrder.setPayPrice(payPrice);
        atmOrder1.setStatus(1);
        this.baseMapper.updateById(atmOrder1);
        //更新用户余额 减去支付价格
        AtmUserPoints atmUserPoints = atmUserPointsService.queryByUserId(atmOrder1.getUserId());
        atmUserPoints.setWallet(atmUserPoints.getWallet().subtract(payPrice));
        atmUserPointsService.updateById(atmUserPoints);
        //获取卖家用户
        AtmUserPoints atmUserPoints1 = atmUserPointsService.queryByUserId(atmOrder1.getPayUserId());
        //更新卖家用户余额 加上交易价格
        atmUserPoints1.setWallet(atmUserPoints1.getWallet().add(atmOrder1.getSellPrice()));
        atmUserPointsService.updateById(atmUserPoints1);
        return 1;
    }

    @Override
    public int updateOrder(AtmOrder atmOrder) {
        return this.baseMapper.updateById(atmOrder);
    }

    @Override
    public List<AtmOrder> queryUnfinishedOrder(String userId,int type) {
        AtmOrderDTO atmOrder = new AtmOrderDTO();
        atmOrder.setUserId(userId);
        atmOrder.setType(type);
        return this.baseMapper.selectList(atmOrder.queryWrapper());
    }
}

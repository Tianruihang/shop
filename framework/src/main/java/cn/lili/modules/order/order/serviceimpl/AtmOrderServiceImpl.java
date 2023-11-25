package cn.lili.modules.order.order.serviceimpl;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dto.AtmOrderSearchParams;
import cn.lili.modules.order.order.mapper.AtmOrderMapper;
import cn.lili.modules.order.order.service.AtmOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AtmOrderServiceImpl extends ServiceImpl<AtmOrderMapper, AtmOrder> implements AtmOrderService {

    @Autowired
    private AtmOrderMapper atmOrderMapper;

    @Override
    public List<AtmOrder> queryList(AtmOrderSearchParams atmOrderSearchParams) {
        return atmOrderMapper.queryList(atmOrderSearchParams.queryWrapper());
    }

    @Override
    public int createBuyOrder(AtmOrder atmOrder) {
        AuthUser currentUser = UserContext.getCurrentUser();
        atmOrder.setUserId(currentUser.getId());
        atmOrder.setStatus(0);
        return this.baseMapper.insert(atmOrder);
    }

    @Override
    public int payOrder(AtmOrder atmOrder) {
        return this.baseMapper.updateById(atmOrder);
    }
}

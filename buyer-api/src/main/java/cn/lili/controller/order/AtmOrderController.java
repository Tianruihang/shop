package cn.lili.controller.order;

import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.exception.ServiceException;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.member.service.MemberService;
import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import cn.lili.modules.order.order.entity.dto.AtmOrderDTO;
import cn.lili.modules.order.order.entity.dto.AtmOrderSearchParams;
import cn.lili.modules.order.order.entity.dto.ExchangeRuleSearchParams;
import cn.lili.modules.order.order.service.AtmOrderService;
import cn.lili.modules.order.order.service.ExchangeRuleService;
import cn.lili.modules.permission.entity.dos.AdminUser;
import cn.lili.modules.permission.service.AdminUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "买家端,订单接口")
@RequestMapping("/buyer/atm/order")
public class AtmOrderController {

    @Autowired
    private AtmOrderService atmOrderService;
    @Autowired
    private ExchangeRuleService exchangeRuleService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AtmUserPointsService atmUserPointsService;

    //列表页面
    @GetMapping
    public ResultMessage<List<AtmOrderDTO>> queryList(AtmOrderSearchParams atmOrderSearchParams) {
        return ResultUtil.data(atmOrderService.queryList(atmOrderSearchParams));
    }

    //查询其他用户的订单
    @GetMapping("other")
    public ResultMessage<List<AtmOrderDTO>> queryOtherList(AtmOrderSearchParams atmOrderSearchParams) {
        atmOrderSearchParams.setOtherOrder(1);
        return ResultUtil.data(atmOrderService.queryList(atmOrderSearchParams));
    }

    //更新页面
    @PutMapping
    public ResultMessage<Object> updateOrder(AtmOrder atmOrder){
        //保存
        atmOrderService.updateOrder(atmOrder);
        return ResultUtil.success();
    }

    @PutMapping("/payOrder")
    public ResultMessage<Object> payOrder(AtmOrder atmOrder){
        //保存
        atmOrderService.payOrder(atmOrder);
        return ResultUtil.success();
    }

    /**
     * 保存订单
     */
    @PostMapping
    public ResultMessage<Object> createBuyOrder(AtmOrder atmOrder) {
        //创建订单时，需要校验支付密码
        log.info("用户信息：{}", UserContext.getCurrentUser());
        //获取用户信息
        Member member = memberService.getById(UserContext.getCurrentUser().getId());        //校验支付密码
        if (!new BCryptPasswordEncoder().matches(atmOrder.getPms(), member.getPassword())) {
            throw new ServiceException(ResultCode.USER_PASSWORD_ERROR);
        }
        //查询用户是否有未完成的订单
        List<AtmOrder> atmOrders = atmOrderService.queryUnfinishedOrder(member.getId(), atmOrder.getType());
        if (atmOrders != null && atmOrders.size() > 0) {
            throw new ServiceException(ResultCode.ORDER_UNFINISHED);
        }
        //保存
        atmOrderService.createBuyOrder(atmOrder);
        return ResultUtil.success();
    }

    @GetMapping("lastRule")
    public ResultMessage<ExchangeRule> lastRule(ExchangeRuleSearchParams exchangeRule) {
        return ResultUtil.data(exchangeRuleService.queryLast(exchangeRule));
    }

    //获取规则列表
    @GetMapping("ruleList")
    public ResultMessage<List<ExchangeRule>> ruleList(ExchangeRuleSearchParams exchangeRule) {
        return ResultUtil.data(exchangeRuleService.queryMaxPriceList(exchangeRule));
    }
}

package cn.lili.controller.order;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import cn.lili.modules.order.order.entity.dto.AtmOrderSearchParams;
import cn.lili.modules.order.order.service.AtmOrderService;
import cn.lili.modules.order.order.service.ExchangeRuleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "买家端,订单接口")
@RequestMapping("/buyer/atm/order")
public class AtmOrderController {

    @Autowired
    private AtmOrderService atmOrderService;
    @Autowired
    private ExchangeRuleService exchangeRuleService;

    //列表页面
    @GetMapping
    public ResultMessage<List<AtmOrder>> queryList(AtmOrderSearchParams atmOrderSearchParams) {
        return ResultUtil.data(atmOrderService.queryList(atmOrderSearchParams));
    }

    //更新页面
    @PutMapping
    public ResultMessage<Object> updateOrder(AtmOrder atmOrder){
        //保存
        atmOrderService.updateOrder(atmOrder);
        return ResultUtil.success();
    }

    @PutMapping("payOrder")
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
        //保存
        atmOrderService.createBuyOrder(atmOrder);
        return ResultUtil.success();
    }

    @GetMapping("lastRule")
    public ResultMessage<ExchangeRule> lastRule() {
        return ResultUtil.data(exchangeRuleService.queryLast());
    }
}

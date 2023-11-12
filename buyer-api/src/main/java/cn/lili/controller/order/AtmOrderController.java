package cn.lili.controller.order;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dto.AtmOrderSearchParams;
import cn.lili.modules.order.order.service.AtmOrderService;
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

    //列表页面
    @GetMapping
    public ResultMessage<List<AtmOrder>> queryList(AtmOrderSearchParams atmOrderSearchParams) {
        return ResultUtil.data(atmOrderService.queryList(atmOrderSearchParams));
    }

    //更新页面
    @PutMapping
    public ResultMessage<Object> payOrder(AtmOrder atmOrder){
        //保存
        atmOrderService.payOrder(atmOrder);
        return ResultUtil.success();
    }

    /**
     * 保存订单
     */
    @PostMapping
    public ResultMessage<Object> createBuyOrder(@RequestBody AtmOrder atmOrder) {
        //保存
        atmOrderService.createBuyOrder(atmOrder);
        return ResultUtil.success();
    }
}

package cn.lili.modules.order.order.service;

import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dto.AtmOrderDTO;
import cn.lili.modules.order.order.entity.dto.AtmOrderSearchParams;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AtmOrderService extends IService<AtmOrder> {

    /**
     * 查询列表
     */
    List<AtmOrderDTO> queryList(AtmOrderSearchParams atmOrderSearchParams);

    //创建购买订单
    int createBuyOrder(AtmOrder atmOrder);

    //支付订单
    int payOrder(AtmOrder atmOrder);

    int updateOrder(AtmOrder atmOrder);
}

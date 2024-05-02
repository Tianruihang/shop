package cn.lili.modules.order.order.entity.dto;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AtmOrderDTO implements Serializable {

    private static final long serialVersionUID = 2233811628066468683L;

    private String id;
    //用户id
    private String userId;
    //购买用户id
    private String payUserId;
    //订单类型 0 平价区 1 溢价区
    private int type;
    //数量
    private int num;
    //卖出价格
    private BigDecimal sellPrice;
    //买入价格
    private BigDecimal payPrice;
    //支付密码
    private String pms;
    //状态
    private Integer status;
    //支付时间
    private Date payTime;
    //手续费
    private int charge;
    //支付类型
    private Integer payType;
    //支付截图
    private String payImg;
    //用户头像
    private String face;
    //订单id
    private String orderId;
    //其他用户订单
    private Integer otherOrder;

    public <AtmOrder> QueryWrapper<AtmOrder> queryWrapper(){
        AuthUser currentUser = UserContext.getCurrentUser();
        QueryWrapper<AtmOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("type", type);
        if (userId != null)
            wrapper.eq("user_id", userId);
        if (payUserId != null)
            wrapper.eq("pay_user_id", payUserId);
        if (otherOrder != null && otherOrder == 1)
            wrapper.ne("user_id", currentUser.getId());
//        wrapper.eq("user_id", currentUser.getId());
        //根据支付类型查询
        if (payType != null)
            wrapper.eq("pay_type", payType);
        //根据状态查询
        if (status != null)
            wrapper.eq("status", status);
        if (orderId != null)
            wrapper.eq("a.id", orderId);
        //根据创建时间倒序
        wrapper.orderByDesc("create_time");
        return wrapper;
    }
}

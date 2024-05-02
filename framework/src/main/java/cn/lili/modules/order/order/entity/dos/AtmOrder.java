package cn.lili.modules.order.order.entity.dos;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("atm_order")
@ApiModel(value = "交易订单")
public class AtmOrder extends BaseEntity {
    private static final long serialVersionUID = 2233811628066468683L;

    //用户id
    private String userId;
    //购买用户id
    private String payUserId;
    //订单类型 0 平价区 1 溢价区
    private int type;
    //数量
    private Integer num;
    //卖出价格
    private BigDecimal sellPrice;
    //买入价格
    private BigDecimal payPrice;
    //支付密码
    private String pms;
    //状态 订单状态 0 待支付 1 已支付 2 已退回 3 待支付
    private int status;
    //支付时间
    private Date payTime;
    //手续费
    private BigDecimal charge;
    //支付类型
    private int payType;
    //支付截图
    private String payImg;
}

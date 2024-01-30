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
    //数量
    private int num;
    //卖出价格
    private BigDecimal sellPrice;
    //买入价格
    private BigDecimal payPrice;
    //支付密码
    private String pms;
    //状态
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

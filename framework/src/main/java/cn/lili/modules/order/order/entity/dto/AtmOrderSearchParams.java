package cn.lili.modules.order.order.entity.dto;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode()
@Data
public class AtmOrderSearchParams {
    private static final long serialVersionUID = -6380573339089959194L;

    private String orderId;
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
    private Integer status;
    //创建时间
    private Date createTime;
    //支付时间
    private Date payTime;
    //手续费
    private int charge;
    //支付类型
    private int payType;
    //订单类型
    private int type;
    //查看其他用户订单
    private Integer otherOrder;


}

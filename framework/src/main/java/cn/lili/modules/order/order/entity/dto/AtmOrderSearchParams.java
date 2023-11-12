package cn.lili.modules.order.order.entity.dto;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AtmOrderSearchParams extends PageVO {
    private static final long serialVersionUID = -6380573339089959194L;

    //用户id
    private String userId;
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
    //创建时间
    private Date createTime;
    //支付时间
    private Date payTime;
    //手续费
    private int charge;
    //支付类型
    private int payType = 0;

    public <T> QueryWrapper<T> queryWrapper(){
        AuthUser currentUser = UserContext.getCurrentUser();
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", currentUser.getId());
        //根据支付类型查询
        wrapper.eq("pay_type", payType);
        //根据状态查询
        wrapper.eq("status", status);
        //根据创建时间倒序
        wrapper.orderByDesc("create_time");
        return new QueryWrapper<>();
    }
}

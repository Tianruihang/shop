package cn.lili.modules.order.order.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("atm_user_grade_rule")
@ApiModel(value = "会员积分规则")
public class AtmUserGradeRule extends BaseEntity {

    private static final long serialVersionUID = 2233811628066468683L;

    String Id;
    String grade;//等级
    BigDecimal charge; //手续费
    String userPower;//算力
    String teamPower;//团队算力
}

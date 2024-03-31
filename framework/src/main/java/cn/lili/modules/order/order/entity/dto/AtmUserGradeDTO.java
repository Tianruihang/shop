package cn.lili.modules.order.order.entity.dto;

import cn.lili.modules.order.order.entity.dos.AtmUserGradeRule;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AtmUserGradeDTO implements Serializable {

    private static final long serialVersionUID = 2233811628066468683L;

    String Id;
    String grade;//等级
    BigDecimal charge; //手续费
    String userPower;//算力
    String teamPower;//团队算力

    public <AtmUserGradeRule> QueryWrapper<AtmUserGradeRule> queryWrapper(){
        QueryWrapper<AtmUserGradeRule> wrapper = new QueryWrapper<>();
        if (grade != null)
            wrapper.eq("grade", grade);
        return wrapper;
    }
}

package cn.lili.modules.order.order.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("atm_mining_machine")
@ApiModel(value = "矿机")
public class AtmMingMachine extends BaseEntity {
    private static final long serialVersionUID = 2233811628066468683L;

    private String name;
    private BigDecimal price;
    private int limitHours;
    private int limitNum;
    private int type;
    private BigDecimal hourPoints;
    private BigDecimal sumPoints;
    private int power;
    private String payType;

}

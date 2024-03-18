package cn.lili.modules.order.order.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@TableName("atm_user_machine")
@ApiModel(value = "矿机数据")
public class AtmUserMachine extends BaseEntity {

    private static final long serialVersionUID = 2233811628066468683L;

    String id;
    String userId;
    String MachineId;
    Date createTime;
    Date endTime;
}

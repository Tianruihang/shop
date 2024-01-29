package cn.lili.modules.member.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("atm_user_recharge")
@ApiModel(value = "会员充值记录")
public class AtmUserRecharge extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //会员id
    private String userId;
    private int type; //币种类型
    private int num;//充币数量
    private String fileImg;//上传凭证
    @ApiModelProperty(value = "0:待审核 1:审核通过 2:审核失败")
    private int status;//状态
}

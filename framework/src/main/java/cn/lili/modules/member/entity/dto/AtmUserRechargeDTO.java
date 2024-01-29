package cn.lili.modules.member.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AtmUserRechargeDTO implements Serializable {

    private static final long serialVersionUID = 2233811628066468683L;

    private String id;
    //用户id
    private String userId;
    private int type; //币种类型
    private int num;//充币数量
    private String fileImg;//上传凭证
    private int status;//状态

}

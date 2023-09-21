package cn.lili.modules.member.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberAuthDTO {

    private static final long serialVersionUID = 1L;

    private String userId;

    @NotEmpty(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String trueName;

    @NotEmpty(message = "身份证号不能为空")
    @ApiModelProperty(value = "身份证号")
    private String idCardNum;

    @NotEmpty(message = "身份证正面不能为空")
    @ApiModelProperty(value = "身份证正面")
    private String onImg;

    @NotEmpty(message = "身份证反面不能为空")
    @ApiModelProperty(value = "身份证反面")
    private String backImg;

    //手持身份证
    @NotEmpty(message = "手持身份证不能为空")
    @ApiModelProperty(value = "手持身份证")
    private String authImg;

    @ApiModelProperty(value = "支付宝账号")
    private String zfbImg;//支付宝收款码

    @ApiModelProperty(value = "微信账号")
    private String weChatImg;//微信收款码

    @ApiModelProperty(value = "0:待审核 1:审核通过 2:审核失败")
    private int status;
}

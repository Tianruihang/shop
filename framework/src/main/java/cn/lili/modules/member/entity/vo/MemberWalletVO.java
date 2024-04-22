package cn.lili.modules.member.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberWalletVO extends MemberVO{

    private String userId;
    private BigDecimal num;//充币数量
    private int type;
    private String cashType;
    private String fileImg;//上传凭证
    @ApiModelProperty(value = "0:待审核 1:审核通过 2:审核失败")
    private int status;//状态
}

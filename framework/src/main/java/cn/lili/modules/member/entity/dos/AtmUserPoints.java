package cn.lili.modules.member.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("atm_user_points")
@ApiModel(value = "会员积分")
public class AtmUserPoints extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员id")
    private String userId;

    @ApiModelProperty(value = "会员真实姓名")
    private String trueName;

    @ApiModelProperty(value = "会员积分")
    private int points; //积分

    @ApiModelProperty(value = "会员等级")
    private String grade;//等级

    @ApiModelProperty(value = "钻石")
    private int num;//钻石

    @ApiModelProperty(value = "会员余额")
    private BigDecimal wallet;//余额

    @ApiModelProperty(value = "会员推荐人数")
    private String userNum;//推荐人数

    @ApiModelProperty(value = "账号类型")
    int type;//账号类型 0 普通会员 1:合伙人

    @ApiModelProperty(value = "分享人Id")
    private String shareId; //分享人Id

    @ApiModelProperty(value = "算力")
    private String userPower;//算力

    @ApiModelProperty(value = "团队算力")
    private String teamPower;//团队算力

    @ApiModelProperty(value = "分红")
    private int dividend;//分红

    @ApiModelProperty(value = "数据包 道具")
    private String pag;//数据包 道具

    @ApiModelProperty(value = "身份证号码")
    private String idCardNum;//身份证号码

    @ApiModelProperty(value = "银行卡号")
    private String cardNum;//收款银行卡

    @ApiModelProperty(value = "身份证正面")
    private String onImg;//身份证正面

    @ApiModelProperty(value = "身份证反面")
    private String backImg;//身份证背面

    @ApiModelProperty(value = "手持身份证")
    private String authImg;//手持身份证

    @ApiModelProperty(value = "支付宝账号")
    String zfbImg;//支付宝收款码

    @ApiModelProperty(value = "微信账号")
    String WeChatImg;//微信收款码

    @ApiModelProperty(value = "0:待审核 1:审核通过 2:审核失败")
    private int status;
}

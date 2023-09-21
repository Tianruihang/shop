package cn.lili.modules.member.entity.vo;

import cn.lili.common.enums.ClientTypeEnum;
import cn.lili.common.security.sensitive.Sensitive;
import cn.lili.common.security.sensitive.enums.SensitiveStrategy;
import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.member.entity.dos.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author paulG
 * @since 2021/11/8
 **/
@Data
@NoArgsConstructor
public class MemberAuthVO implements Serializable {

    private static final long serialVersionUID = 1810890757303309436L;

    @ApiModelProperty(value = "唯一标识", hidden = true)
    private String id;

    @ApiModelProperty(value = "会员用户名")
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String username;

    @ApiModelProperty(value = "昵称")

    private String nickName;

    @ApiModelProperty(value = "会员性别,1为男，0为女")
    private Integer sex;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "会员生日")
    private Date birthday;

    @ApiModelProperty(value = "会员地址ID")
    private String regionId;

    @ApiModelProperty(value = "会员地址")
    private String region;

    @ApiModelProperty(value = "手机号码", required = true)
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String mobile;

    @ApiModelProperty(value = "积分数量")
    private Long point;

    @ApiModelProperty(value = "积分总数量")
    private Long totalPoint;

    @ApiModelProperty(value = "会员头像")
    private String face;

    @ApiModelProperty(value = "会员状态")
    private Boolean disabled;

    @ApiModelProperty(value = "是否开通店铺")
    private Boolean haveStore;

    @ApiModelProperty(value = "店铺ID")
    private String storeId;

    @ApiModelProperty(value = "openId")
    private String openId;

    /**
     * @see ClientTypeEnum
     */
    @ApiModelProperty(value = "客户端")
    private String clientEnum;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后一次登录时间")
    private Date lastLoginDate;

    @ApiModelProperty(value = "会员等级ID")
    private String gradeId;

    @ApiModelProperty(value = "经验值数量")
    private Long experience;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "积分id")
    private String pointId;

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

    public MemberAuthVO(Member member) {
        BeanUtil.copyProperties(member, this);
    }
}

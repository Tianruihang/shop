package cn.lili.modules.member.entity.vo;

import cn.lili.common.enums.SwitchEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会员搜索VO
 *
 * @author Bulbasaur
 * @since 2020/12/15 10:48
 */
@Data
public class MemberSearchVO {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户手机号码")
    private String mobile;

    /**
     * @see SwitchEnum
     */
    @ApiModelProperty(value = "会员状态")
    private String disabled;

    @ApiModelProperty(value = "0:待审核 1:审核通过 2:审核失败")
    private int status;
}

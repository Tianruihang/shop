package cn.lili.modules.member.entity.dto;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AtmUserRechargeDTO implements Serializable {

    private static final long serialVersionUID = 2233811628066468683L;

    private String id;
    //用户id
    private String userId;
    private int type; //充币为0 提币为1
    private String cashType;//币种类型
    private int num;//充币数量
    private String fileImg;//上传凭证
    private int status;//状态

    public <AtmUserRechargeDTO> QueryWrapper<AtmUserRechargeDTO> queryWrapper(){
        QueryWrapper<AtmUserRechargeDTO> wrapper = new QueryWrapper<>();
        if (status == 3){

        }else
            wrapper.eq("au.status", status);
        if (type != -1)
            wrapper.eq("au.type", type);
        return wrapper;
    }

}

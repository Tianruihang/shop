package cn.lili.modules.member.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@TableName("atm_wallet_grade")
@ApiModel(value = "会员等级")
public class AtmWalletGrade extends BaseEntity {
}

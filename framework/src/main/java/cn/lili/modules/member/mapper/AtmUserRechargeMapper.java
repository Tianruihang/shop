package cn.lili.modules.member.mapper;

import cn.lili.modules.member.entity.dos.AtmUserRecharge;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.dto.AtmUserRechargeDTO;
import cn.lili.modules.member.entity.vo.MemberWalletVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AtmUserRechargeMapper extends BaseMapper<AtmUserRecharge> {

    @Select("select au.id,m.*,au.user_id,au.num,au.file_img,au.status from atm_user_recharge au left join li_member m on au.user_id = m.id ${ew.customSqlSegment}")
    IPage<MemberWalletVO> pageByAtmUserRecharge(IPage<AtmUserRechargeDTO> page,@Param(Constants.WRAPPER) Wrapper<Member> queryWrapper);
}

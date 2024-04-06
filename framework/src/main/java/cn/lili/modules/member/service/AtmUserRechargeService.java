package cn.lili.modules.member.service;

import cn.lili.modules.member.entity.dos.AtmUserRecharge;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.dto.AtmUserRechargeDTO;
import cn.lili.modules.member.entity.vo.MemberWalletVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

public interface AtmUserRechargeService extends IService<AtmUserRecharge> {

    //page页面
    IPage<MemberWalletVO> pageByAtmUserRecharge(IPage<AtmUserRechargeDTO> page,  Wrapper<Member> queryWrapper);
}

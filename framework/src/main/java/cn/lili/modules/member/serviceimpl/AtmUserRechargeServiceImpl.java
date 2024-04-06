package cn.lili.modules.member.serviceimpl;

import cn.lili.modules.member.entity.dos.AtmUserRecharge;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.dto.AtmUserRechargeDTO;
import cn.lili.modules.member.entity.vo.MemberWalletVO;
import cn.lili.modules.member.mapper.AtmUserRechargeMapper;
import cn.lili.modules.member.service.AtmUserRechargeService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmUserRechargeServiceImpl extends ServiceImpl<AtmUserRechargeMapper, AtmUserRecharge> implements AtmUserRechargeService {

    @Autowired
    private AtmUserRechargeMapper atmUserRechargeMapper;

    @Override
    public IPage<MemberWalletVO> pageByAtmUserRecharge(IPage<AtmUserRechargeDTO> page, Wrapper<Member> queryWrapper) {

        return atmUserRechargeMapper.pageByAtmUserRecharge(page, queryWrapper);
    }
}

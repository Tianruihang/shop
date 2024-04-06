package cn.lili.controller.order;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.entity.dos.AtmUserRecharge;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.dto.AtmUserRechargeDTO;
import cn.lili.modules.member.entity.vo.MemberWalletVO;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.member.service.AtmUserRechargeService;
import cn.lili.modules.member.service.MemberService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Api(tags = "会员充值记录接口")
@RequestMapping("/manager/user/rechage")
public class AtmUserRechageController {

    @Autowired
    private AtmUserRechargeService atmUserRechargeService;
    @Autowired
    private AtmUserPointsService atmUserPointsService;
    @Autowired
    private MemberService memberService;

    //审核接口
    @PutMapping("audit")
    public ResultMessage<Object> audit(AtmUserRecharge atmUserRecharge) {
        atmUserRechargeService.updateById(atmUserRecharge);
        //审核通过后，将用户金额保存到用户表中
        if (atmUserRecharge.getStatus() == 1) {
            //根据用户id查找用户，将用户的钱包加上充值的钱
            AtmUserPoints atmUserPoints = atmUserPointsService.queryByUserId(atmUserRecharge.getUserId());
            atmUserPoints.setUserId(atmUserRecharge.getUserId());
            atmUserPoints.setWallet(atmUserRecharge.getNum());
            atmUserPointsService.updateById(atmUserPoints);
            //查找用户shareId并找到对应用户，将钱加到对应用户的钱包中
            Member member = memberService.findByShareId(atmUserPoints.getShareId());
            //根据member的id查找用户的钱包
            AtmUserPoints atmUserPoints1 = atmUserPointsService.queryByUserId(member.getId());
            //将用户充值的钱 * 20%提成 加到用户的钱包中
            atmUserPoints1.setWallet(atmUserPoints1.getWallet().add(atmUserRecharge.getNum().multiply(new BigDecimal(0.2))));
            atmUserPointsService.updateById(atmUserPoints1);
        }
        return ResultUtil.success();
    }

    //列表接口
    @GetMapping("list")
    public ResultMessage<IPage<MemberWalletVO>> list(PageVO page, AtmUserRechargeDTO atmUserRecharge) {
        return ResultUtil.data(atmUserRechargeService.pageByAtmUserRecharge(PageUtil.initPage(page), atmUserRecharge.queryWrapper()));
    }

}

package cn.lili.controller.member;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.entity.dos.AtmUserRecharge;
import cn.lili.modules.member.entity.dto.AtmUserRechargeDTO;
import cn.lili.modules.member.entity.vo.MemberWalletVO;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.member.service.AtmUserRechargeService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "会员充值记录接口")
@RequestMapping("/buyer/user/rechage")
public class AtmUserRechageController {

    @Autowired
    private AtmUserRechargeService atmUserRechargeService;
    @Autowired
    private AtmUserPointsService atmUserPointsService;

    //新增接口
    @PostMapping
    public ResultMessage<Object> save(AtmUserRecharge atmUserRecharge) {
        atmUserRecharge.setStatus(0);
        atmUserRechargeService.save(atmUserRecharge);
        return ResultUtil.success();
    }

    //提币接口
    @PostMapping("withdraw")
    public ResultMessage<Object> withdraw(AtmUserRecharge atmUserRecharge) {
        //提币前判断用户的余额是否足够
        AtmUserPoints atmUserPoints = atmUserPointsService.queryByUserId(atmUserRecharge.getUserId());
        if (atmUserPoints.getWallet().compareTo(atmUserRecharge.getNum()) < 0) {
            return ResultUtil.error(10050,"余额不足");
        }
        atmUserRecharge.setStatus(0);
        //充币为0 提币为1
        atmUserRecharge.setType(1);
        atmUserRechargeService.save(atmUserRecharge);
        return ResultUtil.success();
    }

    //列表接口
    @PostMapping("page")
    public ResultMessage<IPage<MemberWalletVO>> list(AtmUserRechargeDTO atmUserRecharge, PageVO page) {
        return ResultUtil.data(atmUserRechargeService.pageByAtmUserRecharge(PageUtil.initPage(page), atmUserRecharge.queryWrapper()));
    }

}

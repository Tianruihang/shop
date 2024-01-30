package cn.lili.controller.member;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.entity.dos.AtmUserRecharge;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.member.service.AtmUserRechargeService;
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
        atmUserRechargeService.save(atmUserRecharge);
        //将用户金额保存到用户表中
        AtmUserPoints atmUserPoints = new AtmUserPoints();
        atmUserPoints.setUserId(atmUserRecharge.getUserId());
        atmUserPoints.setWallet(atmUserRecharge.getNum());
        atmUserPointsService.save(atmUserPoints);
        return ResultUtil.success();
    }

}

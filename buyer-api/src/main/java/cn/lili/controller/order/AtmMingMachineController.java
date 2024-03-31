package cn.lili.controller.order;


import cn.hutool.core.convert.Convert;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.order.order.entity.dos.AtmMingMachine;
import cn.lili.modules.order.order.entity.dos.AtmUserGradeRule;
import cn.lili.modules.order.order.entity.dos.AtmUserMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import cn.lili.modules.order.order.entity.dto.AtmUserGradeDTO;
import cn.lili.modules.order.order.service.AtmMingMachineService;
import cn.lili.modules.order.order.service.AtmMingMachineUserService;
import cn.lili.modules.order.order.service.AtmUserGradeRuleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "买家端,订单接口")
@RequestMapping("/buyer/atm/machine")
public class AtmMingMachineController {

    @Autowired
    private AtmMingMachineUserService atmMingMachineUserService;
    @Autowired
    private AtmMingMachineService atmMingMachineService;
    @Autowired
    private AtmUserGradeRuleService atmUserGradeRuleService;
    @Autowired
    private AtmUserPointsService atmUserPointsService;

    //列表页面
    @GetMapping
    public ResultMessage<List<AtmMingMachineUserDTO>> queryListByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        //根据atmMingMachineUserDTO的条件查询用户的机器
        if(atmMingMachineUserDTO.getStatus() != 2){
            //查询用户id
            AuthUser currentUser = UserContext.getCurrentUser();
            atmMingMachineUserDTO.setUserId(currentUser.getId());
            //查询用户的机器
            return ResultUtil.data(atmMingMachineUserService.queryListByUserId(atmMingMachineUserDTO));
        }else
            return ResultUtil.data(atmMingMachineService.queryList(atmMingMachineUserDTO));
    }
    //详情页
    @GetMapping("user/{id}")
    public ResultMessage<AtmMingMachineUserDTO> queryByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        //获取用户id
        AuthUser currentUser = UserContext.getCurrentUser();
        atmMingMachineUserDTO.setUserId(currentUser.getId());
        return ResultUtil.data(atmMingMachineUserService.queryByUserId(atmMingMachineUserDTO));
    }
    //详情页
    @GetMapping("/{id}")
    public ResultMessage<AtmMingMachine> queryById(@PathVariable String id) {
        return ResultUtil.data(atmMingMachineService.getById(id));
    }
    //购买矿机
    @PutMapping
    public ResultMessage<Object> buyMachine(AtmMingMachineUserDTO atmMingMachineUserDTO){
        //查询用户积分表
        AuthUser currentUser = UserContext.getCurrentUser();
        AtmUserPoints atmUserPoints = atmUserPointsService.queryByUserId(currentUser.getId());
        //获取根据用户当前等级获取用户等级规则内对应的手续费
        AtmUserGradeDTO atmUserGradeDTO = new AtmUserGradeDTO();
        atmUserGradeDTO.setGrade(atmUserPoints.getGrade());
        AtmUserGradeRule atmUserGradeRule = atmUserGradeRuleService.queryByGradle(atmUserGradeDTO);
        //根据MachineId查询矿机
        AtmMingMachine atmMingMachine = atmMingMachineService.getById(atmMingMachineUserDTO.getMachineId());
        //用户余额减去购买矿机的价格减去手续费*购买矿机的价格
        atmUserPoints.setWallet(atmUserPoints.getWallet().subtract(atmMingMachine.getPrice().subtract(atmMingMachine.getPrice().multiply(atmUserGradeRule.getCharge().divide(Convert.toBigDecimal(100))))));
        //更新用户积分表
        atmUserPointsService.updateById(atmUserPoints);

        //根据数量生成对应矿机名称
        for (int i = 0; i < atmMingMachineUserDTO.getNum(); i++) {
            AtmUserMachine atmUserMachine = new AtmUserMachine();
            atmUserMachine.setUserId(currentUser.getId());
            atmUserMachine.setMachineId(atmMingMachineUserDTO.getMachineId());
            atmUserMachine.setCreateTime(new java.util.Date());
            //根据矿机类型获取矿机的结束时间
            Date endTime = new Date();
            //获取矿机时间atmMingMachine.getlimitHour()累加到当前时间
            endTime.setTime(endTime.getTime() + atmMingMachine.getLimitHours() * 60 * 60 * 1000);
            atmUserMachine.setEndTime(endTime);
            //保存
            atmMingMachineUserService.add(atmUserMachine);
        }
        return ResultUtil.success();
    }
}

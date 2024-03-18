package cn.lili.controller.order;


import cn.hutool.core.convert.Convert;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.order.order.entity.dos.AtmMingMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import cn.lili.modules.order.order.service.AtmMingMachineService;
import cn.lili.modules.order.order.service.AtmMingMachineUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "买家端,订单接口")
@RequestMapping("/buyer/atm/machine")
public class AtmMingMachineController {

    @Autowired
    private AtmMingMachineUserService atmMingMachineUserService;
    @Autowired
    private AtmMingMachineService atmMingMachineService;

    //列表页面
    @GetMapping
    public ResultMessage<List<AtmMingMachineUserDTO>> queryListByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        return ResultUtil.data(atmMingMachineUserService.queryListByUserId(atmMingMachineUserDTO));
    }
    //详情页
    @GetMapping("user/{id}")
    public ResultMessage<AtmMingMachineUserDTO> queryByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        return ResultUtil.data(atmMingMachineUserService.queryByUserId(atmMingMachineUserDTO));
    }
    //详情页
    @GetMapping("/{id}")
    public ResultMessage<AtmMingMachine> queryById(String id) {
        return ResultUtil.data(atmMingMachineService.getById(id));
    }
    //购买矿机
    @PutMapping
    public ResultMessage<Object> buyMachine(AtmMingMachineUserDTO atmMingMachineUserDTO){
        //保存
        atmMingMachineUserService.buyMachine(atmMingMachineUserDTO);
        return ResultUtil.success();
    }
}

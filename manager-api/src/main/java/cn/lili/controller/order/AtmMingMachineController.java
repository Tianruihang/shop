package cn.lili.controller.order;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.order.order.entity.dos.AtmMingMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineSearchParams;
import cn.lili.modules.order.order.service.AtmMingMachineService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager/atmMingMachine")
@Api(tags = "矿机接口")
public class AtmMingMachineController {

    @Autowired
    private AtmMingMachineService atmMingMachineService;

    //矿机列表 分页
    @GetMapping("getByPage")
    @ApiOperation(value = "矿机列表")
    public ResultMessage<IPage<AtmMingMachine>> queryList(AtmMingMachineSearchParams atmMingMachine) {
        return ResultUtil.data(atmMingMachineService.queryPage(atmMingMachine));
    }

    //矿机详情
    @GetMapping("detail/{id}")
    @ApiOperation(value = "矿机详情")
    public ResultMessage<AtmMingMachine> detail(@PathVariable String id) {
        return ResultUtil.data(atmMingMachineService.getById(id));
    }

    //新增矿机
    @PostMapping("insert")
    @ApiOperation(value = "新增矿机")
    public ResultMessage<Object> add(AtmMingMachine atmMingMachine) {
        atmMingMachineService.add(atmMingMachine);
        return ResultUtil.success();
    }

    //修改矿机
    @PutMapping("update")
    @ApiOperation(value = "修改矿机")
    public ResultMessage<Object> update(AtmMingMachine atmMingMachine) {
        atmMingMachineService.update(atmMingMachine);
        return ResultUtil.success();
    }
}

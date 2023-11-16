package cn.lili.modules.order.order.serviceimpl;

import cn.lili.modules.order.order.entity.dos.AtmMingMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineSearchParams;
import cn.lili.modules.order.order.mapper.AtmMingMachineMapper;
import cn.lili.modules.order.order.service.AtmMingMachineService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class AtmMingMachineServiceImpl extends ServiceImpl<AtmMingMachineMapper, AtmMingMachine> implements AtmMingMachineService {


    @Override
    public void add(AtmMingMachine atmMingMachine) {

    }

    @Override
    public void update(AtmMingMachine atmMingMachine) {

    }

    @Override
    public void delete(AtmMingMachine atmMingMachine) {

    }

    @Override
    public IPage<AtmMingMachine> queryPage(AtmMingMachineSearchParams atmMingMachineSearchParams) {
        return this.baseMapper.selectPage(atmMingMachineSearchParams.getPage(), atmMingMachineSearchParams.queryWrapper());
    }
}

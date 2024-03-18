package cn.lili.modules.order.order.serviceimpl;

import cn.lili.modules.order.order.entity.dos.AtmMingMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineSearchParams;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import cn.lili.modules.order.order.mapper.AtmMingMachineMapper;
import cn.lili.modules.order.order.service.AtmMingMachineService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AtmMingMachineServiceImpl extends ServiceImpl<AtmMingMachineMapper, AtmMingMachine> implements AtmMingMachineService {

    @Resource
    private AtmMingMachineMapper atmMingMachineMapper;

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

    @Override
    public AtmMingMachine queryById(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        return this.baseMapper.selectById(atmMingMachineUserDTO.getMachineId());
    }

}

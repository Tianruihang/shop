package cn.lili.modules.order.order.serviceimpl;

import cn.lili.modules.order.order.entity.dos.AtmUserMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import cn.lili.modules.order.order.mapper.AtmUserMachineMapper;
import cn.lili.modules.order.order.service.AtmMingMachineUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AtmMingMachineUserServiceImpl extends ServiceImpl<AtmUserMachineMapper, AtmUserMachine> implements AtmMingMachineUserService {

    @Resource
    private AtmUserMachineMapper atmUserMachineMapper;

    @Override
    public void add(AtmUserMachine atmUserMachine) {
        //添加
        atmUserMachineMapper.insert(atmUserMachine);
    }

    @Override
    public void update(AtmUserMachine atmUserMachine) {
        //修改
        atmUserMachineMapper.updateById(atmUserMachine);
    }

    @Override
    public void delete(AtmUserMachine atmUserMachine) {
        //删除
        atmUserMachineMapper.deleteById(atmUserMachine.getId());
    }

    @Override
    public AtmMingMachineUserDTO queryByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        return atmUserMachineMapper.selectMachineListByUserId(atmMingMachineUserDTO.queryWrapper());
    }

    @Override
    public List<AtmMingMachineUserDTO> queryListByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        return atmUserMachineMapper.queryByUserId(atmMingMachineUserDTO.queryWrapper());
    }

    @Override
    public void buyMachine(AtmMingMachineUserDTO atmMingMachineUserDTO) {
        //查询当前用户vip等级
    }
}

package cn.lili.modules.order.order.service;

import cn.lili.modules.order.order.entity.dos.AtmUserMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AtmMingMachineUserService extends IService<AtmUserMachine> {

        //添加
        void add(AtmUserMachine atmUserMachine);

        //修改
        void update(AtmUserMachine atmUserMachine);

        //删除
        void delete(AtmUserMachine atmUserMachine);

        //查询
        AtmMingMachineUserDTO queryByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO);
        //根据UserId查询用户的机器
        List<AtmMingMachineUserDTO> queryListByUserId(AtmMingMachineUserDTO atmMingMachineUserDTO);

        //购买机器
        void buyMachine(AtmMingMachineUserDTO atmMingMachineUserDTO);
}

package cn.lili.modules.order.order.service;

import cn.lili.modules.order.order.entity.dos.AtmMingMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineSearchParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AtmMingMachineService extends IService<AtmMingMachine> {

        //添加
        void add(AtmMingMachine atmMingMachine);

        //修改
        void update(AtmMingMachine atmMingMachine);

        //删除
        void delete(AtmMingMachine atmMingMachine);

        //查询
        IPage<AtmMingMachine> queryPage(AtmMingMachineSearchParams atmMingMachineSearchParams);
}

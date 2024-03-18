package cn.lili.modules.order.order.mapper;

import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dos.AtmUserMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AtmUserMachineMapper extends BaseMapper<AtmUserMachine> {

    //联合查询根据atm_user_machine表的user_id查询atm_ming_machine表的machine_id
    @Select("select * from atm_user_machine left join atm_ming_machine on atm_user_machine.machine_id = atm_ming_machine.id where ${ew.customSqlSegment}")
    List<AtmMingMachineUserDTO> queryByUserId(@Param(Constants.WRAPPER) Wrapper<AtmMingMachineUserDTO> queryWrapper);

    @Select("select * from atm_ming_machine where ${ew.customSqlSegment}")
    List<AtmMingMachineUserDTO> selectMachineList(@Param(Constants.WRAPPER) Wrapper<AtmMingMachineUserDTO> queryWrapper);

    @Select("select * from atm_ming_machine left join atm_user_machine on atm_ming_machine.id = atm_user_machine.machine_id where ${ew.customSqlSegment} limit 1")
    AtmMingMachineUserDTO selectMachineListByUserId(@Param(Constants.WRAPPER) Wrapper<AtmMingMachineUserDTO> queryWrapper);
}

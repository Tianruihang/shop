package cn.lili.modules.order.order.mapper;

import cn.lili.modules.order.order.entity.dos.AtmMingMachine;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AtmMingMachineMapper extends BaseMapper<AtmMingMachine> {


    @Select("select * from atm_mining_machine ${ew.customSqlSegment}")
    List<AtmMingMachineUserDTO> queryList(@Param(Constants.WRAPPER) Wrapper<AtmMingMachineUserDTO> wrapper);
}

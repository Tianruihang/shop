package cn.lili.modules.order.order.mapper;

import cn.lili.modules.order.order.entity.dos.AtmOrder;
import cn.lili.modules.order.order.entity.dto.AtmOrderDTO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper
public interface AtmOrderMapper extends BaseMapper<AtmOrder> {

    //atm_order表与li_member表联合查询
    @Select("SELECT a.*,b.face as face FROM atm_order a LEFT JOIN li_member b ON a.user_id = b.id ${ew.customSqlSegment}")
    List<AtmOrderDTO> queryList(@Param(Constants.WRAPPER) Wrapper<AtmOrder> queryWrapper);
}

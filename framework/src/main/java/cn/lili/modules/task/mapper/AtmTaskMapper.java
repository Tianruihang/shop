package cn.lili.modules.task.mapper;

import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.member.entity.vo.MemberVO;
import cn.lili.modules.task.entity.dos.AtmTask;
import cn.lili.modules.task.entity.vo.AtmTaskVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AtmTaskMapper extends BaseMapper<AtmTask> {

    //分页方法
    @Select("select * from atm_task ${ew.customSqlSegment}")
    IPage<AtmTaskVO> pageByAtmTaskVO(IPage<AtmTaskVO> page, @Param(Constants.WRAPPER) Wrapper<Member> queryWrapper);

}

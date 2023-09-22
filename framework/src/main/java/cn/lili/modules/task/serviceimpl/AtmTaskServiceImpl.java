package cn.lili.modules.task.serviceimpl;

import cn.hutool.core.text.CharSequenceUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.modules.member.entity.dos.Member;
import cn.lili.modules.task.entity.dos.AtmTask;
import cn.lili.modules.task.entity.dto.AtmTaskSearchParam;
import cn.lili.modules.task.entity.vo.AtmTaskVO;
import cn.lili.modules.task.mapper.AtmTaskMapper;
import cn.lili.modules.task.service.AtmTaskService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AtmTaskServiceImpl extends ServiceImpl<AtmTaskMapper, AtmTask> implements AtmTaskService {


    @Override
    public IPage<AtmTaskVO> getTaskList(AtmTaskVO atmTaskSearchParam, PageVO pageVO) {
        QueryWrapper<Member> queryWrapper = Wrappers.query();
        queryWrapper.like(CharSequenceUtil.isNotBlank(atmTaskSearchParam.getUserId()), "atu.user_id", atmTaskSearchParam.getUserId());
        queryWrapper.isNotNull("atu.status");
        queryWrapper.orderByDesc("create_time");
        return this.baseMapper.pageByAtmTaskVO(PageUtil.initPage(pageVO), queryWrapper);
    }
}

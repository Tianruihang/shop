package cn.lili.modules.task.serviceimpl;

import cn.lili.common.vo.PageVO;
import cn.lili.modules.task.entity.dos.AtmTask;
import cn.lili.modules.task.entity.dto.AtmTaskSearchParam;
import cn.lili.modules.task.mapper.AtmTaskMapper;
import cn.lili.modules.task.service.AtmTaskService;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AtmTaskServiceImpl extends ServiceImpl<AtmTaskMapper, AtmTask> implements AtmTaskService {


    @Override
    public IPage<AtmTask> getTaskList(AtmTaskSearchParam atmTaskSearchParam, PageVO pageVO) {
        return this.page(PageUtil.initPage(atmTaskSearchParam), atmTaskSearchParam.queryWrapper());
    }
}

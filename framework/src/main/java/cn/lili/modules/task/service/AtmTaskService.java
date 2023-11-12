package cn.lili.modules.task.service;

import cn.lili.common.vo.PageVO;
import cn.lili.modules.task.entity.dos.AtmTask;
import cn.lili.modules.task.entity.dto.AtmTaskSearchParam;
import cn.lili.modules.task.entity.vo.AtmTaskVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AtmTaskService extends IService<AtmTask> {

    //获取任务列表
    IPage<AtmTaskVO> getTaskList(AtmTaskVO atmTaskSearchParam, PageVO pageVO);


}

package cn.lili.modules.task.entity.dto;

import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class AtmTaskSearchParam extends PageVO {

    private static final long serialVersionUID = 2544015852728566887L;

    public <T> QueryWrapper<T> queryWrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();

        return queryWrapper;
    }
}

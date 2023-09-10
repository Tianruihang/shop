package cn.lili.modules.task.entity.dto;

import cn.lili.mybatis.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class AtmTaskDTP {

    private static final long serialVersionUID = 1L;

    private int type;

    private String name;

    private String content;

    private int stats;

    private long endTime;

}

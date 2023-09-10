package cn.lili.modules.task.entity.dos;

import cn.lili.mybatis.BaseEntity;
import lombok.Data;

@Data
public class AtmTask extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private int type;

    private String name;

    private String content;

    private int stats;


    private long endTime;
}

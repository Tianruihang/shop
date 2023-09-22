package cn.lili.modules.task.entity.dos;

import cn.lili.mybatis.BaseEntity;
import lombok.Data;

@Data
public class AtmTaskUser extends BaseEntity {


    private static final long serialVersionUID = 1L;

    private String userId;

    private String taskId;

    private int status;
}

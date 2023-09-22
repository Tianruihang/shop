package cn.lili.modules.task.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AtmTaskVO implements Serializable {

    @ApiModelProperty(value = "唯一标识", hidden = true)
    private String id;

    private int type;

    private String name;

    private String content;

    private int stats;

    private long createTime;

    private long endTime;

    private String userId;

    private String taskId;
}

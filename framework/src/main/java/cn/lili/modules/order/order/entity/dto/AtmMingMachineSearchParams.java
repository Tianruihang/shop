package cn.lili.modules.order.order.entity.dto;

import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AtmMingMachineSearchParams extends PageVO {

    private String name;
    private BigDecimal price;
    private int limitHours;
    private int type;
    private BigDecimal hourPoints;
    private BigDecimal sumPoints;
    private int power;
    private String payType;

    public <T> QueryWrapper<T> queryWrapper(){
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        return new QueryWrapper<>();
    }

    //获取page
    public <T> IPage<T> getPage(){
        return new Page<>(this.getPageNumber(), this.getPageSize());
    }

}

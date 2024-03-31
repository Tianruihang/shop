package cn.lili.modules.order.order.entity.dto;

import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AtmMingMachineUserDTO extends PageVO {
    private static final long serialVersionUID = -6380573339089959194L;
    private String id;
    private int num;// 购买数量
    private String name;
    private BigDecimal price;
    private int limitHours;
    private int limitNum;
    private int type = 0;
    private int status;
    private BigDecimal hourPoints;
    private BigDecimal sumPoints;
    private int power;
    private String payType;
    private String userId;
    private String machineId;
    private Date createTime;

    public <AtmMingMachineUser> QueryWrapper<AtmMingMachineUser> queryWrapper(){
        AuthUser currentUser = UserContext.getCurrentUser();
        QueryWrapper<AtmMingMachineUser> wrapper = new QueryWrapper<>();
        if (userId != null)
            wrapper.eq("user_id", userId);
        if (machineId != null)
            wrapper.eq("machine_id", machineId);
        if (id != null)
            wrapper.eq("atm_mining_machine.id", id);
//        wrapper.eq("user_id", currentUser.getId());
        if (type == 0){
            //根据创建时间小于当前时间为有效时间
            wrapper.le("atm_mining_machine.create_time", new Date());
        }else {
            //根据创建时间大于当前时间为有效时间
            wrapper.ge("atm_mining_machine.create_time", new Date());
        }

        //根据创建时间倒序
        wrapper.orderByDesc("atm_mining_machine.create_time");
        return wrapper;
    }

    //获取page
    public <T> IPage<T> getPage(){
        return new Page<>(this.getPageNumber(), this.getPageSize());
    }
}

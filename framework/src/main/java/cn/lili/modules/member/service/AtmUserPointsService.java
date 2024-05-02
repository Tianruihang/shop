package cn.lili.modules.member.service;

import cn.lili.modules.member.entity.dos.AtmUserPoints;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AtmUserPointsService extends IService<AtmUserPoints> {

    AtmUserPoints queryByUserId(String userId);

    //查询所有用户积分
    List<AtmUserPoints> queryAllUserPoints();
}

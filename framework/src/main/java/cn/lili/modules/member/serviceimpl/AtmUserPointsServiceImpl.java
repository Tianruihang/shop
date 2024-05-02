package cn.lili.modules.member.serviceimpl;

import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.mapper.AtmUserPointsMapper;
import cn.lili.modules.member.service.AtmUserPointsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtmUserPointsServiceImpl extends ServiceImpl<AtmUserPointsMapper, AtmUserPoints> implements AtmUserPointsService {

    //根据userId查询用户积分
    @Override
    public AtmUserPoints queryByUserId(String userId) {
        QueryWrapper<AtmUserPoints> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return this.baseMapper.selectOne(wrapper);
    }

    //查询所有用户积分
    @Override
    public List<AtmUserPoints> queryAllUserPoints() {
        return this.baseMapper.selectList(null);
    }
}

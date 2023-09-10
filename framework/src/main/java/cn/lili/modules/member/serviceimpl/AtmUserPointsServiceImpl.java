package cn.lili.modules.member.serviceimpl;

import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.mapper.AtmUserPointsMapper;
import cn.lili.modules.member.service.AtmUserPointsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AtmUserPointsServiceImpl extends ServiceImpl<AtmUserPointsMapper, AtmUserPoints> implements AtmUserPointsService {
}

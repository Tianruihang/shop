package cn.lili.modules.task.serviceimpl;

import cn.lili.modules.task.entity.dos.AtmTaskUser;
import cn.lili.modules.task.mapper.AtmTaskUserMapper;
import cn.lili.modules.task.service.AtmTaskUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AtmTaskUserServiceImpl extends ServiceImpl<AtmTaskUserMapper, AtmTaskUser> implements AtmTaskUserService {
}

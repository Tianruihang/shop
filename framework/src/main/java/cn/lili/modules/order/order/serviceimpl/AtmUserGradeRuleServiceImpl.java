package cn.lili.modules.order.order.serviceimpl;

import cn.lili.modules.order.order.entity.dos.AtmUserGradeRule;
import cn.lili.modules.order.order.entity.dto.AtmUserGradeDTO;
import cn.lili.modules.order.order.mapper.AtmUserGradeRuleMapper;
import cn.lili.modules.order.order.service.AtmUserGradeRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AtmUserGradeRuleServiceImpl extends ServiceImpl<AtmUserGradeRuleMapper, AtmUserGradeRule> implements AtmUserGradeRuleService {

    @Override
    public void add(AtmUserGradeRule atmUserGradeRule) {
        this.baseMapper.insert(atmUserGradeRule);
    }

    @Override
    public void update(AtmUserGradeRule atmUserGradeRule) {
        this.baseMapper.updateById(atmUserGradeRule);
    }

    @Override
    public void delete(AtmUserGradeRule atmUserGradeRule) {
        this.baseMapper.deleteById(atmUserGradeRule.getId());
    }

    @Override
    public AtmUserGradeRule queryByGradle(AtmUserGradeDTO atmUserGradeRule) {
        return this.baseMapper.selectOne(atmUserGradeRule.queryWrapper());
    }

    @Override
    public List<AtmUserGradeRule> queryList(AtmUserGradeDTO atmUserGradeRule) {
        return this.baseMapper.selectList(atmUserGradeRule.queryWrapper());
    }
}

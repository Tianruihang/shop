package cn.lili.modules.order.order.serviceimpl;

import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import cn.lili.modules.order.order.entity.dto.ExchangeRuleSearchParams;
import cn.lili.modules.order.order.mapper.ExchangeRuleMapper;
import cn.lili.modules.order.order.service.ExchangeRuleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class ExchangeRuleServiceImpl extends ServiceImpl<ExchangeRuleMapper, ExchangeRule> implements ExchangeRuleService {


    @Override
    public IPage<ExchangeRule> queryPage(ExchangeRuleSearchParams exchangeRule) {
        //
        return this.baseMapper.selectPage(exchangeRule.getPage(), exchangeRule.queryWrapper());
    }

    @Override
    public void add(ExchangeRule exchangeRule) {
        this.baseMapper.insert(exchangeRule);
    }

    @Override
    public void update(ExchangeRule exchangeRule) {
        //更新
        this.baseMapper.updateById(exchangeRule);
    }
}

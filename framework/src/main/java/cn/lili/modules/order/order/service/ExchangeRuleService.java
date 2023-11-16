package cn.lili.modules.order.order.service;

import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import cn.lili.modules.order.order.entity.dto.ExchangeRuleSearchParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ExchangeRuleService extends IService<ExchangeRule> {

    //列表查询
    IPage<ExchangeRule> queryPage(ExchangeRuleSearchParams exchangeRule);

    //添加
    void add(ExchangeRule exchangeRule);

    //修改
    void update(ExchangeRule exchangeRule);
}

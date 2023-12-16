package cn.lili.modules.order.order.service;

import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import cn.lili.modules.order.order.entity.dto.ExchangeRuleSearchParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ExchangeRuleService extends IService<ExchangeRule> {

    //列表查询
    IPage<ExchangeRule> queryPage(ExchangeRuleSearchParams exchangeRule);

    //添加
    void add(ExchangeRule exchangeRule);

    //修改
    void update(ExchangeRule exchangeRule);

    //查询最近一条记录
    ExchangeRule queryLast();

    //查询规则表中最高的价格列表
    List<ExchangeRule> queryMaxPriceList();
}

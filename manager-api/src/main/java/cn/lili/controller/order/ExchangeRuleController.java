package cn.lili.controller.order;

import cn.hutool.core.convert.Convert;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import cn.lili.modules.order.order.entity.dto.ExchangeRuleSearchParams;
import cn.lili.modules.order.order.service.ExchangeRuleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "买家端,订单接口")
@RequestMapping("manager/buyer/atm/rule")
public class ExchangeRuleController {

    //引入exchangeRuleService
    @Autowired
    private ExchangeRuleService exchangeRuleService;

    //新增规则
    @PostMapping
    public ResultMessage<Object> add(ExchangeRule exchangeRule) {
        exchangeRuleService.add(exchangeRule);
        return ResultUtil.success();
    }

    //修改
    @PutMapping
    public ResultMessage<Object> update(ExchangeRuleSearchParams exchangeRuleSearchParams) {
        //ExchangeRuleSearchParams转化为ExchangeRule
        ExchangeRule exchangeRule = Convert.convert(ExchangeRule.class, exchangeRuleSearchParams);
        exchangeRuleService.update(exchangeRule);
        return ResultUtil.success();
    }

    //删除
    @DeleteMapping("/{id}"  )
    public ResultMessage<Object> delete(String id) {
        exchangeRuleService.removeById(id);
        return ResultUtil.success();
    }

    @GetMapping("lastRule")
    public ResultMessage<ExchangeRule> lastRule(ExchangeRuleSearchParams exchangeRule) {
        return ResultUtil.data(exchangeRuleService.queryLast(exchangeRule));
    }

}

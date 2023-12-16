package cn.lili.modules.order.order.mapper;

import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExchangeRuleMapper extends BaseMapper<ExchangeRule> {

    //查询规则表max_usual_price字段最近四条记录
    @Select("select max_usual_price from atm_exchange_rule order by create_time desc limit 4")
    List<ExchangeRule> queryMaxPriceList();
}

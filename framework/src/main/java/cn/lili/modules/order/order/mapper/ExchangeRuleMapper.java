package cn.lili.modules.order.order.mapper;

import cn.lili.modules.order.order.entity.dos.ExchangeRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExchangeRuleMapper extends BaseMapper<ExchangeRule> {

    //查询规则表max_usual_price字段最近四条记录
    @Select("select * from atm_exchange_rule where type = #{type} order by max_usual_price desc limit 4")
    List<ExchangeRule> queryMaxPriceList(@Param("type") int type);
}

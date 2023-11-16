package cn.lili.modules.order.order.entity.dos;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("atm_exchange_rule")
@ApiModel(value = "交易规则")
public class ExchangeRule extends BaseEntity {
    private static final long serialVersionUID = 2233811628066468683L;

    //最小通常价格
    private BigDecimal minUsualPrice;
    //最大通常价格
    private BigDecimal maxUsualPrice;
    //最小结束价格
    private BigDecimal minOverPrice;
    //最大结束价格
    private BigDecimal maxOverPrice;
    //手续费
    private BigDecimal charge;
    //收款码相片
    private String collectImg;
    //收款码地址
    private String collectUrl;
    //收款吗名称
    private String collectName;
    //收币第一次返利
    private BigDecimal receiptRebateFir;
    //收币第二次返利
    private BigDecimal receiptRebateSec;
    //收币第三次返利
    private BigDecimal receiptRebateThi;
    //充币返利
    private BigDecimal payRebate;
    //提现返利
    private BigDecimal cashRebate;
    //返利
    private BigDecimal rebate;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
    //交易类型
    private String type;
}

package cn.lili.common.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class HbOrderVo {
    private String appKey;
    private String accessToken;
    private String tradeNo; //流水号
    private List<Map> sku; //商品id数组
    private BigDecimal price; //订单单价
    private String name;//收货人
    private String provinceId;//省份id
    private String cityId;//城市id
    private String countyId;//区域id
    private String townId;//乡镇id
    private String address;//详细地址
    private String phone;//手机号
    private String mobile;//固定电话
    private String email;//邮箱
    private String remark;//备注
    private Integer invoiceState;//是否开发票
    private Integer invoiceType;//发票类型
    private String companyName;//发票抬头
    private String invoiceContent;//发票内容
    private String amount;//订单金额
    private Integer freight;//运费
    private String payment;//支付方式【1：公务卡支付；2：账期支付】
}

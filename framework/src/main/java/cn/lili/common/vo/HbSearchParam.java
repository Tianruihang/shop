package cn.lili.common.vo;

import lombok.Data;

@Data
public class HbSearchParam {
    private String accessToken;
    private String appKey;
    private String appSecret;
    private String sku;
    private String num;
    private String provinceId;
    private String countyId;
    private String cityId;
}

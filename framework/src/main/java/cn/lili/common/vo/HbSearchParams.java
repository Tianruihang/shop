package cn.lili.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class HbSearchParams {
    private String accessToken;
    private String appKey;
    private String appSecret;
    private List<String> sku;
    private String cityId;
}

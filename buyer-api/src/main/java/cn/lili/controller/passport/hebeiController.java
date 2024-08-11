package cn.lili.controller.passport;

import cn.lili.common.enums.HbResultUtil;
import cn.lili.common.enums.ResultCode;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.*;
import cn.lili.modules.goods.entity.dos.Brand;
import cn.lili.modules.goods.entity.dos.Category;
import cn.lili.modules.goods.entity.dos.Goods;
import cn.lili.modules.goods.entity.dos.GoodsSku;
import cn.lili.modules.goods.entity.dto.GoodsSearchParams;
import cn.lili.modules.goods.entity.dto.HBCategoryDTO;
import cn.lili.modules.goods.service.BrandService;
import cn.lili.modules.goods.service.CategoryService;
import cn.lili.modules.goods.service.GoodsService;
import cn.lili.modules.goods.service.GoodsSkuService;
import cn.lili.modules.member.service.MemberService;
import cn.lili.modules.order.order.entity.dos.Trade;
import cn.lili.modules.order.order.service.TradeService;
import cn.lili.modules.system.entity.dos.Region;
import cn.lili.modules.system.service.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
//引入gosn
import com.google.gson.Gson;
import javax.json.Json;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ecservice")
public class hebeiController {

    @Autowired
    private MemberService memberService;
    /**
     * 商品分类
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 商品sku
     */
    @Autowired
    private GoodsSkuService goodsSkuService;
    /**
     * 商品
     */
    @Autowired
    private GoodsService goodsService;
    /**
     * 商品品牌
     */
    @Autowired
    private BrandService brandService;
    //配置店铺id
    @Value("${config.hebei.shopId:1809895136634081282}")
    private String shopId;
    @Value("${config.hebei.appKey:epoint}")
    private String appKey;
    @Value("${config.hebei.appSecret:7db8fbcc-a9c2-4010-bc79-e7c4bcd9ab04}")
    private String appSecret;

    @Autowired
    private RegionService regionService;
    @Autowired
    private TradeService tradeService;
    //登录获取token
    @PostMapping("getAccessToken")
    public Object getAccessToken(RequestWapperBody requestBody) {
        String requestBodyData = requestBody.getData();
        // 使用 Jackson 解析 data 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        HbSearchParams data = null;
        Map map = new HashMap();
        try {
            data = objectMapper.readValue(requestBodyData, HbSearchParams.class);
            //比对appKey和appSecret
            if (data.getAppKey().equals(appKey) && data.getAppSecret().equals(appSecret)) {
                //返回token

                map.put("accessToken",memberService.HeBeiToken().getAccessToken());
                map.put("returnMsg","商品分类信息");
                map.put("isSuccess",true);
                //返回json
                Gson gosn = new Gson();
                String json = gosn.toJson(map);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("returnMsg","描述");
        map.put("isSuccess",false);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    //获取品类(目录)接口
    @PostMapping("getProductCategory")
    public Object getProductCategory(RequestWapperBody requestBody) {
        //河北id为 1806944458974433281
        List<Category> categoryList = categoryService.dbList("1806944458974433281");
        List<HBCategoryDTO> categoryDTOList = categoryList.stream().map(category -> {
            HBCategoryDTO hbCategoryDTO = new HBCategoryDTO();
            hbCategoryDTO.setName(category.getName());
            hbCategoryDTO.setCategoryId(category.getThirdId());
            return hbCategoryDTO;
        }).collect(Collectors.toList());
        Map map = new HashMap();
        map.put("result",categoryDTOList);
        map.put("returnMsg","商品分类信息");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    //获取商品池接口
    @PostMapping("getProductPool")
    public Object getProductPool(RequestWapperBody requestBody){
        GoodsSearchParams searchParams = new GoodsSearchParams();
        searchParams.setStoreId(shopId);
        List<GoodsSku> goodsSku = goodsSkuService.getGoodsSkuByList(searchParams);
        //创建list<string>
        List<String> goodsSkuList = goodsSku.stream().map(GoodsSku::getSn).collect(Collectors.toList());
        //创建 键sku 值goodsSkuList的map
        Map map = new HashMap();
        map.put("sku",goodsSkuList);
        map.put("returnMsg","分类商品编码信息");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    //获取商品详情接口
    @PostMapping("getProductDetail")
    public Object getProductDetail(RequestWapperBody requestBody){
        HbSearchParam data = new HbSearchParam();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbSearchParam.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GoodsSku goodsSku = goodsSkuService.getGoodsSkuBySn(data.getSku());
        //根据goodsSku获取商品详情
        Goods goods = goodsService.getById(goodsSku.getGoodsId());
        Map map = new HashMap();
        //创建map
        /**
         * weight
         * image
         * state
         * brand
         * model
         * name
         * productArea
         * upc
         * saleUnit
         * category
         * introduction
         * param
         * isProtected
         * jieNengNo
         * jieNengDate
         * huanBaoNo
         * huanBaoDate
         * returnMsg
         * isSuccess
         */
        map.put("sku",data.getSku());
        map.put("weight",goodsSku.getWeight());
        map.put("image",goodsSku.getOriginal());
        map.put("state",goodsSku.getMarketEnable());
        //根据bandId获取品牌
        Brand brand = brandService.getById(goodsSku.getBrandId());
        map.put("brand",brand.getName());
        map.put("name",goodsSku.getGoodsName());
        map.put("category",goodsSku.getStoreCategoryPath());
        map.put("param",goodsSku.getSpecs());
        map.put("introduction",goods.getIntro());
        map.put("returnMsg","分类商品编码信息");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 获取商品图片接口
     */
    @PostMapping("getProductImage")
    public Object getProductImage(RequestWapperBody requestBody){
        HbSearchParams data = new HbSearchParams();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbSearchParams.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        List<Map> urls = new ArrayList<>();
        data.getSku().forEach(sku -> {
            GoodsSku goodsSku = goodsSkuService.getGoodsSkuBySn(sku);
            //根据goodsSku获取商品详情
            Goods goods = goodsService.getById(goodsSku.getGoodsId());
            Map url = new HashMap();
            url.put("path",goods.getThumbnail());
            url.put("primary",0);
            urls.add(url);
            Map url2 = new HashMap();
            url2.put("path",goods.getOriginal());
            url2.put("primary",1);
            urls.add(url2);
        });
        map.put("urls",urls);
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 商品上下架状态查询接口
     */
    @PostMapping("getProductOnShelvesInfo")
    public Object getProductOnShelvesInfo(RequestWapperBody requestBody){
        HbSearchParams data = new HbSearchParams();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbSearchParams.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        List<Map> list = new ArrayList<>();
        data.getSku().forEach(sku -> {
            GoodsSku goodsSku = goodsSkuService.getGoodsSkuBySn(sku);
            Map skuMap = new HashMap();
            skuMap.put("skuId",sku);
            skuMap.put("listState",goodsSku.getMarketEnable().equals("UPPER") ? 1 : 0);
            list.add(skuMap);
        });
        map.put("onShelvesList",list);
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }
    /**
     * 商品映射关系查询接口
     */
    @PostMapping("getProductInfoFromEC")
    public Object getProductInfoFromEC(RequestWapperBody requestBody){
        Map map = new HashMap();
        List<Map> list = new ArrayList<>();
        //查询所有商品Sku
        GoodsSearchParams searchParams = new GoodsSearchParams();
        searchParams.setStoreId(shopId);
        List<GoodsSku> goodsSku = goodsSkuService.getGoodsSkuByList(searchParams);
        goodsSku.forEach(goodsSku1 -> {
            Map skuMap = new HashMap();
            skuMap.put("productId",goodsSku1.getGoodsId());
            skuMap.put("skuId",goodsSku1.getSn());
            skuMap.put("productName",goodsSku1.getGoodsName());
            skuMap.put("productUrl",goodsSku1.getOriginal());
            skuMap.put("productNameEC",goodsSku1.getGoodsName());
            skuMap.put("productUrlEC",goodsSku1.getOriginal());
            list.add(skuMap);
        });
        map.put("productInfoList",list);
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 查询省信息接口
     */
    @PostMapping("getProvinceInfo")
    public Object getProvinceInfo(RequestWapperBody requestBody){
        Map map = new HashMap();
        List<Map> list = new ArrayList<>();
        Map province = new HashMap();
        province.put("code","130000");
        province.put("name","河北省");
        list.add(province);
        map.put("province",list);
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 查询市信息接口
     */
    @PostMapping("getCityInfo")
    public Object getCityInfo(RequestWapperBody requestBody){
        HbSearchParam data = new HbSearchParam();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbSearchParam.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        List<Map> list = new ArrayList<>();
        List<Region> regionList = regionService.getItem(data.getProvinceId());
        regionList.forEach(region -> {
            Map city = new HashMap();
            city.put("code",region.getId());
            city.put("name",region.getName());
            list.add(city);
        });
        map.put("city",list);
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 查询区信息接口
     */
    @PostMapping("getDistrictInfo")
    public Object getDistrictInfo(RequestWapperBody requestBody) {
        HbSearchParam data = new HbSearchParam();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbSearchParam.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        List<Map> list = new ArrayList<>();
        List<Region> regionList = regionService.getItem(data.getCityId());
        regionList.forEach(region -> {
            Map city = new HashMap();
            city.put("code", region.getId());
            city.put("name", region.getName());
            list.add(city);
        });
        map.put("district", list);
        map.put("returnMsg", "描述");
        map.put("isSuccess", true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 商品库存查询接口
     */
    @PostMapping("getProductInventory")
    public Object getProductInventory(RequestWapperBody requestBody){
        HbSearchParam data = new HbSearchParam();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbSearchParam.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("skuId",data.getSku());
        map.put("state","00");
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 获取商品折扣价格接口
     */
    @PostMapping("queryCountPrice")
    public Object queryCountPrice(RequestWapperBody requestBody){
        HbSearchParams data = new HbSearchParams();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbSearchParams.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        List<Map> list = new ArrayList<>();
        data.getSku().forEach(sku -> {
            GoodsSku goodsSku = goodsSkuService.getGoodsSkuBySn(sku);
            Map skuMap = new HashMap();
            skuMap.put("skuId",sku);
            skuMap.put("price",goodsSku.getPrice());
            skuMap.put("discount",goodsSku.getPromotionPrice());
            list.add(skuMap);
        });
        map.put("priceList",list);
        map.put("cityId",data.getCityId());
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 循环遍历增加订单Sku
     */
//    @PostMapping("getOrderSku")
    public Object getOrderSku(RequestWapperBody requestBody){
        Map map = new HashMap();
        //创建list
        List<GoodsSku> list = new ArrayList<>();
        //查询指定sku
        GoodsSku goodsSku = goodsSkuService.getGoodsSkuBySn("5437578347535");
        //查询指定sku
        for (int i = 0; i < 1000; i++) {
            //创建新的sku
            GoodsSku newGoodsSku = new GoodsSku();
            //生成随机id 20位的bigint
            newGoodsSku = goodsSku;
            newGoodsSku.setId(null);
            newGoodsSku.setSn("130" + i);
            newGoodsSku.setStoreId("1809895136634081282");
            goodsSkuService.save(newGoodsSku);
        }
        //保存sku
//        goodsSkuService.saveBatch(list);
        map.put("sku",list);
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }

    /**
     * 创建订单
     */
    @PostMapping("createOrder")
    public Object createOrder(RequestWapperBody requestBody){
        HbOrderVo data = new HbOrderVo();
        try {
            // 使用 Jackson 解析 data 字符串
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(requestBody.getData(), HbOrderVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //随机生成订单字符串
        String orderID = String.valueOf(System.currentTimeMillis());
        //遍历sku
        HbOrderVo finalData = data;
        data.getSku().forEach(sku -> {
            GoodsSku goodsSku = goodsSkuService.getGoodsSkuBySn(sku.get("skuId").toString());
            //根据goodsSku获取商品详情
            Goods goods = goodsService.getById(goodsSku.getGoodsId());
            //创建订单
            Trade trade = new Trade();
            trade.setSn(finalData.getTradeNo());
            trade.setMemberName(finalData.getName());
            trade.setMobile(finalData.getMobile());
            trade.setConsigneeAddressPath(finalData.getProvinceId() + "," + finalData.getCityId() + "," + finalData.getCountyId() + "," + finalData.getTownId());
            trade.setConsigneeAddressIdPath(finalData.getProvinceId() + "," + finalData.getCityId() + "," + finalData.getCountyId() + "," + finalData.getTownId());
            trade.setInvoiceState(finalData.getInvoiceState());
            trade.setInvoiceType(finalData.getInvoiceType());
            trade.setCompanyName(finalData.getCompanyName());
            trade.setInvoiceContent(finalData.getInvoiceContent());
            trade.setEmail(finalData.getEmail());
            trade.setNum(Integer.valueOf(sku.get("num").toString()));
            trade.setPrice(finalData.getPrice());
            trade.setRemark(finalData.getRemark());
            trade.setPaymentMethod(finalData.getPayment());
            trade.setPayStatus("UNPAID");
            trade.setGoodsPrice(goodsSku.getPrice());
            trade.setFreightPrice(finalData.getFreight().doubleValue());
            trade.setMemberId(orderID);
            trade.setConsigneeMobile(finalData.getPhone());
            tradeService.save(trade);
        });
        Map map = new HashMap();

        map.put("orderId",orderID);
        map.put("sku",data.getSku());
        //当前时间延后一个月
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);
        Date arriveData = calendar.getTime();
        //转格式为yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Format the date
        String formattedDate = sdf.format(arriveData);
        map.put("arriveData",formattedDate);
        map.put("amount",data.getAmount());
        map.put("freight",data.getFreight());
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        Gson gosn = new Gson();
        String json = gosn.toJson(map);
        return json;
    }
}

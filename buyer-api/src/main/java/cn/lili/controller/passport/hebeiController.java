package cn.lili.controller.passport;

import cn.lili.common.enums.HbResultUtil;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.HbResultMessage;
import cn.lili.common.vo.HbSearchParam;
import cn.lili.common.vo.HbSearchParams;
import cn.lili.common.vo.ResultMessage;
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
import cn.lili.modules.system.entity.dos.Region;
import cn.lili.modules.system.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Value("${config.hebei.shopId}")
    private String shopId;

    @Autowired
    private RegionService regionService;
    //登录获取token
    @GetMapping("getAccessToken")
    public HbResultMessage<Object> getAccessToken(@RequestBody HbSearchParams data) {
        return HbResultUtil.data(this.memberService.usernameLogin(data.getAppKey(), data.getAppSecret()));
    }

    //获取品类(目录)接口
    @GetMapping("getProductCategory")
    public Object getProductCategory(@RequestBody HbSearchParams data) {
        //河北id为 1806944458974433281
        List<Category> categoryList = categoryService.dbList("1806944458974433281");
        List<HBCategoryDTO> categoryDTOList = categoryList.stream().map(category -> {
            HBCategoryDTO hbCategoryDTO = new HBCategoryDTO();
            hbCategoryDTO.setName(category.getName());
            hbCategoryDTO.setCategoryId(category.getId());
            return hbCategoryDTO;
        }).collect(Collectors.toList());
        Map map = new HashMap();
        map.put("result",categoryDTOList);
        map.put("returnMsg","分类商品编码信息");
        map.put("isSuccess",true);
        return map;
    }

    //获取商品池接口
    @PostMapping("getProductPool")
    public Object getProductPool(@RequestBody HbSearchParams data){
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
        return map;
    }

    //获取商品详情接口
    @PostMapping("getProductDetail")
    public Object getProductDetail(@RequestBody HbSearchParam data){
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
        return map;
    }

    /**
     * 获取商品图片接口
     */
    @PostMapping("getProductImage")
    public Object getProductImage(@RequestBody HbSearchParams data){
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
        return map;
    }

    /**
     * 商品上下架状态查询接口
     */
    @PostMapping("getProductOnShelvesInfo")
    public Object getProductOnShelvesInfo(@RequestBody HbSearchParams data){
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
        return map;
    }
    /**
     * 商品映射关系查询接口
     */
    @PostMapping("getProductInfoFromEC")
    public Object getProductInfoFromEC(@RequestBody HbSearchParams data){
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
        return map;
    }

    /**
     * 查询省信息接口
     */
    @PostMapping("getProvinceInfo")
    public Object getProvinceInfo(@RequestBody HbSearchParams data){
        Map map = new HashMap();
        List<Map> list = new ArrayList<>();
        Map province = new HashMap();
        province.put("code","130000");
        province.put("name","河北省");
        list.add(province);
        map.put("province",list);
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        return map;
    }

    /**
     * 查询市信息接口
     */
    @PostMapping("getCityInfo")
    public Object getCityInfo(@RequestBody HbSearchParam data){
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
        return map;
    }

    /**
     * 查询区信息接口
     */
    @PostMapping("getDistrictInfo")
    public Object getDistrictInfo(@RequestBody HbSearchParam data) {
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
        return map;
    }

    /**
     * 商品库存查询接口
     */
    @PostMapping("getProductInventory")
    public Object getProductInventory(@RequestBody HbSearchParam data){
        Map map = new HashMap();
        map.put("skuId",data.getSku());
        map.put("state","00");
        map.put("returnMsg","描述");
        map.put("isSuccess",true);
        return map;
    }

    /**
     * 获取商品折扣价格接口
     */
    @PostMapping("queryCountPrice")
    public Object queryCountPrice(@RequestBody HbSearchParams data){
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
        return map;
    }
}

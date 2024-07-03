package cn.lili.controller.passport;

import cn.lili.common.enums.HbResultUtil;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.HbResultMessage;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.goods.entity.dos.Brand;
import cn.lili.modules.goods.entity.dos.Category;
import cn.lili.modules.goods.entity.dos.GoodsSku;
import cn.lili.modules.goods.entity.dto.GoodsSearchParams;
import cn.lili.modules.goods.entity.dto.HBCategoryDTO;
import cn.lili.modules.goods.service.BrandService;
import cn.lili.modules.goods.service.CategoryService;
import cn.lili.modules.goods.service.GoodsSkuService;
import cn.lili.modules.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.validation.constraints.NotNull;
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
     * 商品品牌
     */
    @Autowired
    private BrandService brandService;
    //配置店铺id
    @Value("${config.hebei.shopId}")
    private String shopId;

    //登录获取token
    @GetMapping("getAccessToken")
    public HbResultMessage<Object> getAccessToken(@NotNull(message = "用户名不能为空") @RequestParam String appKey,
                                                  @NotNull(message = "密码不能为空") @RequestParam String appSecret) {
        return HbResultUtil.data(this.memberService.usernameLogin(appKey, appSecret));
    }

    //获取品类(目录)接口
    @GetMapping("getProductCategory")
    public Object getProductCategory(@NotNull(message = "用户名不能为空") @RequestParam String appKey) {
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
    @GetMapping("getProductPool")
    public Object getProductPool(@NotNull(message = "用户名不能为空") @RequestParam String appKey,
                                                  @NotNull(message = "商品池Id不能为空") @RequestParam String categoryId){
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
    @GetMapping("getProductDetail")
    public Object getProductDetail(@NotNull(message = "用户名不能为空") @RequestParam String appKey,
                                   @NotNull(message = "商品编码不能为空") @RequestParam String sku){
        GoodsSku goodsSku = goodsSkuService.getGoodsSkuBySn(sku);
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
        map.put("sku",sku);
        map.put("weight",goodsSku.getWeight());
        map.put("image",goodsSku.getOriginal());
        map.put("state",goodsSku.getMarketEnable());
        //根据bandId获取品牌
        Brand brand = brandService.getById(goodsSku.getBrandId());
        map.put("brand",brand.getName());
        map.put("result",goodsSku);
        map.put("returnMsg","分类商品编码信息");
        map.put("isSuccess",true);
        return map;
    }

}

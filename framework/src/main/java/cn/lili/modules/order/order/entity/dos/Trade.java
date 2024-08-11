package cn.lili.modules.order.order.entity.dos;

import cn.lili.common.utils.BeanUtil;
import cn.lili.modules.order.cart.entity.dto.TradeDTO;
import cn.lili.modules.order.cart.entity.enums.DeliveryMethodEnum;
import cn.lili.modules.order.order.entity.enums.PayStatusEnum;
import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * 交易
 *
 * @author Chopper
 * @since 2020/11/17 7:34 下午
 */
@Data
@TableName("li_trade")
@ApiModel(value = "交易")
@NoArgsConstructor
public class Trade extends BaseEntity {

    private static final long serialVersionUID = 5177608752643561827L;

    @ApiModelProperty(value = "交易编号")
    private String sn;

    @ApiModelProperty(value = "买家id")
    private String memberId;

    @ApiModelProperty(value = "买家用户名")
    private String memberName;

    /**
     * @see PayStatusEnum
     */
    @ApiModelProperty(value = "支付方式 支付方式【1：公务卡支付；2：账期支付】")
    private String paymentMethod;

    /**
     * @see cn.lili.modules.order.order.entity.enums.PayStatusEnum
     */
    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "总价格")
    private Double flowPrice;

    @ApiModelProperty(value = "原价")
    private Double goodsPrice;

    @ApiModelProperty(value = "运费")
    private Double freightPrice;

    @ApiModelProperty(value = "优惠的金额")
    private Double discountPrice;

    /**
     * @see DeliveryMethodEnum
     */
    @ApiModelProperty(value = "配送方式")
    private String deliveryMethod;

    @ApiModelProperty(value = "收货人姓名")
    private String consigneeName;

    @ApiModelProperty(value = "收件人手机")
    private String consigneeMobile;

    @ApiModelProperty(value = "地址名称， '，'分割")
    private String consigneeAddressPath;

    @ApiModelProperty(value = "地址id，'，'分割 ")
    private String consigneeAddressIdPath;

    @ApiModelProperty(value = "是否开发票【1=开，0=不开】")
    private Integer invoiceState;

    @ApiModelProperty(value = "发票类型【1=增值发票 2=普通发票】")
    private Integer invoiceType;

    @ApiModelProperty(value = "发票抬头")
    private String companyName;

    @ApiModelProperty(value = "发票内容")
    private String invoiceContent;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "数量")
    private Integer num;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;

    @ApiModelProperty(value = "备注")
    private String remark;



    public Trade(TradeDTO tradeDTO) {
        String originId = this.getId();
        if (tradeDTO.getMemberAddress() != null) {
            BeanUtil.copyProperties(tradeDTO.getMemberAddress(), this);
            this.setConsigneeMobile(tradeDTO.getMemberAddress().getMobile());
            this.setConsigneeName(tradeDTO.getMemberAddress().getName());
        }
        BeanUtil.copyProperties(tradeDTO, this);
        BeanUtil.copyProperties(tradeDTO.getPriceDetailDTO(), this);
        this.setPayStatus(PayStatusEnum.UNPAID.name());
        this.setId(originId);
    }
}
package cn.lili.controller.member;

import cn.lili.common.enums.ResultUtil;
import cn.lili.common.security.OperationalJudgment;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.entity.dos.MemberAddress;
import cn.lili.modules.member.entity.dto.MemberPointMessage;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.member.service.MemberAddressService;
import cn.lili.modules.member.service.MemberService;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import cn.lili.modules.order.order.service.AtmMingMachineUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


/**
 * 买家端,会员地址接口
 *
 * @author Bulbasaur
 * @since 2020/11/16 10:07 下午
 */
@RestController
@Api(tags = "买家端,会员地址接口")
@RequestMapping("/buyer/member/address")
public class MemberAddressBuyerController {
    //设置静态值keyAddName
    private static final String KEY_AUTH_NAME = "AUTH_DAILY_";
    /**
     * 会员收件地址
     */
    @Autowired
    private MemberAddressService memberAddressService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private AtmUserPointsService atmUserPointsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AtmMingMachineUserService atmMingMachineUserService;


    @ApiOperation(value = "获取会员收件地址分页列表")
    @GetMapping
    public ResultMessage<IPage<MemberAddress>> page(PageVO page) {
        return ResultUtil.data(memberAddressService.getAddressByMember(page, UserContext.getCurrentUser().getId()));
    }

    @ApiOperation(value = "根据ID获取会员收件地址")
    @ApiImplicitParam(name = "id", value = "会员地址ID", dataType = "String", paramType = "path")
    @GetMapping(value = "/get/{id}")
    public ResultMessage<MemberAddress> getShippingAddress(@PathVariable String id) {
        return ResultUtil.data(memberAddressService.getMemberAddress(id));
    }

    @ApiOperation(value = "获取当前会员默认收件地址")
    @GetMapping(value = "/get/default")
    public ResultMessage<MemberAddress> getDefaultShippingAddress() {
        return ResultUtil.data(memberAddressService.getDefaultMemberAddress());
    }

    @ApiOperation(value = "新增会员收件地址")
    @PostMapping
    public ResultMessage<MemberAddress> addShippingAddress(@Valid MemberAddress shippingAddress) {
        //添加会员地址
        shippingAddress.setMemberId(Objects.requireNonNull(UserContext.getCurrentUser()).getId());
        if(shippingAddress.getIsDefault()==null){
            shippingAddress.setIsDefault(false);
        }
        return ResultUtil.data(memberAddressService.saveMemberAddress(shippingAddress));
    }

    @ApiOperation(value = "修改会员收件地址")
    @PutMapping
    public ResultMessage<MemberAddress> editShippingAddress(@Valid MemberAddress shippingAddress) {
        OperationalJudgment.judgment(memberAddressService.getById(shippingAddress.getId()));
        shippingAddress.setMemberId(Objects.requireNonNull(UserContext.getCurrentUser()).getId());
        return ResultUtil.data(memberAddressService.updateMemberAddress(shippingAddress));
    }

    @ApiOperation(value = "删除会员收件地址")
    @ApiImplicitParam(name = "id", value = "会员地址ID", dataType = "String", paramType = "path")
    @DeleteMapping(value = "/delById/{id}")
    public ResultMessage<Object> delShippingAddressById(@PathVariable String id) {
        OperationalJudgment.judgment(memberAddressService.getById(id));
        memberAddressService.removeMemberAddress(id);
        return ResultUtil.success();
    }

    //获取用户积分列表
    @ApiOperation(value = "获取用户积分列表")
    @GetMapping(value = "/pointList/{id}")
    public ResultMessage<List<MemberPointMessage>> pointList(@PathVariable String id){
        return ResultUtil.data(memberService.pointList(id));
    }

    //收取积分
    @ApiOperation(value = "收取积分")
    @PostMapping(value = "point")
    public ResultMessage<Object> point(){
        //获取当前用户
        String userId = UserContext.getCurrentUser().getId();
        //获取用户积分
        AtmUserPoints atmUserPoints1 = atmUserPointsService.queryByUserId(userId);
        //生成累加积分
        List<BigDecimal> point = new java.util.ArrayList<>();
        //获取用户拥有矿机
        AtmMingMachineUserDTO atmMingMachineUserDTO = new AtmMingMachineUserDTO();
        atmMingMachineUserDTO.setUserId(atmUserPoints1.getUserId());
        List<AtmMingMachineUserDTO> atmMingMachineUserDTOS = atmMingMachineUserService.queryListByUserId(atmMingMachineUserDTO);
        //查询用户积分
        //24小时分成24个类型查询对应数值
        String keyName = KEY_AUTH_NAME + atmUserPoints1.getUserId() + "_";
        atmMingMachineUserDTOS.stream().forEach(atmMingMachineUserDTO1 -> {
            //查询矿机是否过期
            int limit = atmMingMachineUserDTO1.getLimitHours();
            //根据创建时间和限制时间判断是否过期
            if (!atmMingMachineUserDTO1.getMachineId().equals("1")&& atmMingMachineUserDTO1.getCreateTime().getTime() + limit * 60 * 60 * 1000 < System.currentTimeMillis()) {
                return;
            }
            //获取当前系统时间的小时数
            int hour = 8;
            //生成24小时的积分
            for (int i = 0; i < 24; i++) {
                //判断是否已有key 有则删掉
                if (stringRedisTemplate.hasKey(keyName + atmMingMachineUserDTO1.getName() + i)) {
                    //将积分加入到用户wallet中
                    point.add(atmMingMachineUserDTO1.getHourPoints());
                    stringRedisTemplate.delete(keyName + atmMingMachineUserDTO1.getName() + i);
                }
            }
        });
        //累加积分
        for (BigDecimal bigDecimal : point) {
            atmUserPoints1.setWallet(atmUserPoints1.getWallet().add(bigDecimal));
        }
        //更新用户积分
        atmUserPointsService.updateById(atmUserPoints1);
        return ResultUtil.success();
    }

}

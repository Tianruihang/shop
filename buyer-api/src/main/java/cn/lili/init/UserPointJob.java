package cn.lili.init;

import cn.lili.modules.member.entity.dos.AtmUserPoints;
import cn.lili.modules.member.entity.dto.MemberPointMessage;
import cn.lili.modules.member.service.AtmUserPointsService;
import cn.lili.modules.order.order.entity.dto.AtmMingMachineUserDTO;
import cn.lili.modules.order.order.service.AtmMingMachineUserService;
import cn.lili.modules.system.entity.dto.PointSetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserPointJob {

    //设置静态值keyAddName
    private static final String KEY_AUTH_NAME = "AUTH_DAILY_";
    @Autowired
    private AtmUserPointsService atmUserPointsService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AtmMingMachineUserService atmMingMachineUserService;

    //每小时生产积分
    @Scheduled(cron = "0 0 * * * ?")
    public void userPointJob() {
        log.info("用户日积分定时更新");
        //获取所有实名认证用户
        List<AtmUserPoints> atmUserPoints = atmUserPointsService.queryAllUserPoints();
        atmUserPoints.forEach( atmUserPoints1 -> {
            //24小时分成24个类型查询对应数值
            String keyName = KEY_AUTH_NAME + atmUserPoints1.getUserId() + "_";
            //获取用户拥有矿机
            AtmMingMachineUserDTO atmMingMachineUserDTO = new AtmMingMachineUserDTO();
            atmMingMachineUserDTO.setUserId(atmUserPoints1.getUserId());
            List<AtmMingMachineUserDTO> atmMingMachineUserDTOS = atmMingMachineUserService.queryListByUserId(atmMingMachineUserDTO);
            //查询用户积分
            atmMingMachineUserDTOS.stream().forEach(atmMingMachineUserDTO1 -> {
                //查询矿机是否过期
                int limit = atmMingMachineUserDTO1.getLimitHours();
                //根据创建时间和限制时间判断是否过期
                if (!atmMingMachineUserDTO1.getMachineId().equals("1")&& atmMingMachineUserDTO1.getCreateTime().getTime() + limit * 60 * 60 * 1000 < System.currentTimeMillis()) {
                    return;
                }
                //获取当前系统时间的小时数
//                int hour = new java.util.Date().getHours();
                int hour = 8;
                //生成24小时的积分
                for (int i = 0; i < 24; i++) {
                    //判断是否已有key 有则删掉
                    if (stringRedisTemplate.hasKey(keyName + atmMingMachineUserDTO1.getName() + i)) {
                        stringRedisTemplate.delete(keyName + atmMingMachineUserDTO1.getName() + i);
                    }
                    //24个类型
                    stringRedisTemplate.opsForValue().set(keyName + atmMingMachineUserDTO1.getName() + i,atmMingMachineUserDTO1.getHourPoints().toString(), 24 * 60 * 60);

                }

            });
        });
    }
}

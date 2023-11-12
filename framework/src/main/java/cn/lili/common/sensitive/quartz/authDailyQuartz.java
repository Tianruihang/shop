package cn.lili.common.sensitive.quartz;

import cn.lili.cache.Cache;
import cn.lili.cache.CachePrefix;
import cn.lili.common.sensitive.SensitiveWordsFilter;
import cn.lili.modules.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 间隔更新敏感词
 *
 * @author Chopper
 * @version v1.0
 * 2021-11-23 16:31
 */
@Slf4j
public class authDailyQuartz extends QuartzJobBean {

    @Resource
    private MemberService memberService;

    /**
     * 定时更新敏感词信息
     *
     * @param jobExecutionContext
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("用户日积分定时更新");
        //获取当前小时
        int hour = LocalDateTime.now().getHour();
        //查询用户是否是已实名认证的
        memberService.authDailyPoints(hour);
    }
}
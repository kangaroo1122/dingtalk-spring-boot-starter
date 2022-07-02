package com.coctrl.dingtalk.configuration;

import com.coctrl.dingtalk.constant.DingTalkConstant;
import com.coctrl.dingtalk.multiple.service.DefaultMultipleRobot;
import com.coctrl.dingtalk.multiple.service.IMultipleRobot;
import com.coctrl.dingtalk.service.DingTalkSender;
import com.coctrl.dingtalk.service.IDingTalkSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类 DingTalkAutoConfiguration 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 15:53
 */
@Configuration
@EnableConfigurationProperties(DingTalkProperties.class)
@ConditionalOnClass(DingTalkSender.class)
@ConditionalOnProperty(prefix = DingTalkConstant.PREFIX, value = "enabled", matchIfMissing = true)
public class DingTalkAutoConfiguration {

    private final DingTalkProperties properties;

    public DingTalkAutoConfiguration(DingTalkProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(IDingTalkSender.class)
    public IDingTalkSender dingTalkSender() {
        return new DingTalkSender(properties);
    }

    @Bean
    @ConditionalOnMissingBean(IMultipleRobot.class)
    public IMultipleRobot multipleRobot() {
        return new DefaultMultipleRobot(properties);
    }
}

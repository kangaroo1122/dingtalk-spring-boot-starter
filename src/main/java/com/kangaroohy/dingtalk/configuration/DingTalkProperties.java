package com.kangaroohy.dingtalk.configuration;

import com.kangaroohy.dingtalk.constant.DingTalkConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 类 DingTalkProperties 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 15:55
 */
@Component
@Primary
@ConfigurationProperties(prefix = DingTalkConstant.PREFIX)
public class DingTalkProperties {

    /**
     * 默认机器人accessToken
     */
    private String accessToken;

    /**
     * 默认机器人secret
     */
    private String secret;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

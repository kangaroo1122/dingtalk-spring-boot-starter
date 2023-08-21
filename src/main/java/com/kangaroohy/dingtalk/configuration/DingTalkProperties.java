package com.kangaroohy.dingtalk.configuration;

import com.kangaroohy.dingtalk.constant.DingTalkConstant;
import com.kangaroohy.dingtalk.multiple.algorithm.AlgorithmHandler;
import com.kangaroohy.dingtalk.multiple.algorithm.DingTalkHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<String, GroupProperties> groups = new HashMap<>();

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

    public Map<String, GroupProperties> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, GroupProperties> groups) {
        this.groups = groups;
    }

    public static class GroupProperties {
        private Class<? extends AlgorithmHandler> algorithmHandler = DingTalkHandler.class;

        private List<RobotProperties> robots = new ArrayList<>();

        public Class<? extends AlgorithmHandler> getAlgorithmHandler() {
            return algorithmHandler;
        }

        public void setAlgorithmHandler(Class<? extends AlgorithmHandler> algorithmHandler) {
            this.algorithmHandler = algorithmHandler;
        }

        public List<RobotProperties> getRobots() {
            return robots;
        }

        public void setRobots(List<RobotProperties> robots) {
            this.robots = robots;
        }
    }

    public static class RobotProperties {
        /**
         * 机器人accessToken
         */
        private String accessToken;

        /**
         * 机器人secret
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
}

package com.kangaroohy.dingtalk.service;

import com.kangaroohy.dingtalk.configuration.DingTalkProperties;
import com.kangaroohy.dingtalk.entity.args.base.BaseArgs;
import com.kangaroohy.dingtalk.exception.DingTalkException;
import com.dingtalk.api.response.OapiRobotSendResponse;
import org.springframework.stereotype.Service;

/**
 * 类 AbstractDingTalkSender 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 16:16
 */
@Service
public class DingTalkSender extends AbstractDingTalkSender {

    public DingTalkSender(DingTalkProperties properties) {
        if (properties.getAccessToken() == null) {
            throw new DingTalkException("默认机器人accessToken必须配置");
        }
        setProperties(properties);
    }

    @Override
    public OapiRobotSendResponse send(BaseArgs args) throws DingTalkException {
        return sendMsg(args);
    }

    @Override
    public OapiRobotSendResponse send(BaseArgs args, String groupId) throws DingTalkException {
        return sendMsg(args, groupId);
    }

    @Override
    public OapiRobotSendResponse send(BaseArgs args, String groupId, boolean nullToDefault) throws DingTalkException {
        return sendMsg(args, groupId, nullToDefault);
    }

}

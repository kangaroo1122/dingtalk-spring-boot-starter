package com.coctrl.dingtalk.service;

import com.coctrl.dingtalk.entity.args.base.BaseArgs;
import com.coctrl.dingtalk.exception.DingTalkException;
import com.dingtalk.api.response.OapiRobotSendResponse;

/**
 * 类 DingTalkSender 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 18:47
 */
public interface IDingTalkSender {

    /**
     * 发送
     *
     * @param args 消息体参数
     * @return {@link OapiRobotSendResponse}
     * @throws DingTalkException ex
     */
    OapiRobotSendResponse send(BaseArgs args) throws DingTalkException;

    /**
     * 发送
     *
     * @param args    消息体参数
     * @param groupId 组id
     * @return {@link OapiRobotSendResponse}
     * @throws DingTalkException ex
     */
    OapiRobotSendResponse send(BaseArgs args, String groupId) throws DingTalkException;

    /**
     * 发送
     *
     * @param args          消息体参数
     * @param groupId       组id
     * @param nullToDefault 群组无效，则采用默认机器人推送，默认不使用
     * @return {@link OapiRobotSendResponse}
     * @throws DingTalkException ex
     */
    OapiRobotSendResponse send(BaseArgs args, String groupId, boolean nullToDefault) throws DingTalkException;

}

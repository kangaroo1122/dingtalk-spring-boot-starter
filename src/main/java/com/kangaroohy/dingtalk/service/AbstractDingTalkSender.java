package com.kangaroohy.dingtalk.service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.kangaroohy.dingtalk.configuration.DingTalkProperties;
import com.kangaroohy.dingtalk.constant.DingTalkConstant;
import com.kangaroohy.dingtalk.entity.args.*;
import com.kangaroohy.dingtalk.entity.args.base.AtArgs;
import com.kangaroohy.dingtalk.entity.args.base.BaseArgs;
import com.kangaroohy.dingtalk.exception.DingTalkException;
import com.kangaroohy.dingtalk.multiple.algorithm.AlgorithmHandler;
import com.kangaroohy.dingtalk.multiple.algorithm.DefaultHandler;
import com.kangaroohy.dingtalk.multiple.entity.DingTalkArgs;
import com.kangaroohy.dingtalk.multiple.entity.GroupArgs;
import com.kangaroohy.dingtalk.multiple.service.IMultipleRobot;
import com.kangaroohy.dingtalk.utils.DingTalkUtils;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 类 DingTalkSender 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 16:15
 */
public abstract class AbstractDingTalkSender implements IDingTalkSender {

    @Autowired
    private IMultipleRobot multipleRobot;

    protected DingTalkProperties properties;

    public void setProperties(DingTalkProperties properties) {
        this.properties = properties;
    }

    public DingTalkProperties getProperties() {
        return properties;
    }

    /**
     * 发送消息推送
     *
     * @param args    推送参数
     * @param groupId 群组标识
     * @return {@link OapiRobotSendResponse}
     * @throws DingTalkException ex
     */
    protected OapiRobotSendResponse sendMsg(BaseArgs args, String groupId) throws DingTalkException {
        return sendMsg(args, groupId, false);
    }

    /**
     * 发送消息推送
     *
     * @param args          推送参数
     * @param groupId       群组标识
     * @param nullToDefault 群组无效，则采用默认机器人推送，默认不使用
     * @return {@link OapiRobotSendResponse}
     * @throws DingTalkException ex
     */
    protected OapiRobotSendResponse sendMsg(BaseArgs args, String groupId, boolean nullToDefault) throws DingTalkException {
        Map<String, GroupArgs> groupArgsMap = multipleRobot.groupRobotMap();
        GroupArgs groupArgs = groupArgsMap.get(groupId);
        AlgorithmHandler algorithmHandler;
        if (groupArgs == null) {
            if (!nullToDefault) {
                throw new DingTalkException("无指定群组信息");
            }
            groupArgs = groupArgsMap.get(DingTalkConstant.DEFAULT_ROBOT_GROUP_ID);
            algorithmHandler = new DefaultHandler();
        } else {
            algorithmHandler = groupArgs.getAlgorithmHandler();
        }
        DingTalkArgs handler = algorithmHandler.handler(groupArgs.getDingTalkArgs(),
                groupArgsMap.get(DingTalkConstant.DEFAULT_ROBOT_GROUP_ID).getDingTalkArgs().get(0));
        args.setAccessToken(handler.getAccessToken());
        args.setSecret(handler.getSecret() == null ? null : handler.getSecret());
        return sendMsg(args);
    }

    /**
     * 发送消息推送
     *
     * @param args 推送参数
     * @return {@link OapiRobotSendResponse}
     * @throws DingTalkException ex
     */
    protected OapiRobotSendResponse sendMsg(BaseArgs args) throws DingTalkException {
        checkArgs(args);

        String url = DingTalkUtils.getUrl(args.accessToken(), args.secret() == null ? null : args.secret());
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest sendRequest = new OapiRobotSendRequest();
        sendRequest.setMsgtype(args.msgType().getCode());

        atArgs((AtArgs) args, sendRequest);

        if (args instanceof TextArgs) {
            textArgs((TextArgs) args, sendRequest);
        } else if (args instanceof MarkdownArgs) {
            markdownArgs((MarkdownArgs) args, sendRequest);
        } else if (args instanceof LinkArgs) {
            linkArgs((LinkArgs) args, sendRequest);
        } else if (args instanceof ActionCardWholeArgs) {
            wholeArgs((ActionCardWholeArgs) args, sendRequest);
        } else if (args instanceof ActionCardAloneArgs) {
            aloneArgs((ActionCardAloneArgs) args, sendRequest);
        } else if (args instanceof FeedCardArgs) {
            feedCardArgs((FeedCardArgs) args, sendRequest);
        } else {
            throw new DingTalkException("暂不支持的消息类型");
        }

        try {
            return client.execute(sendRequest);
        } catch (ApiException e) {
            throw new DingTalkException(e);
        }
    }

    /**
     * 检查参数
     *
     * @param args 参数
     */
    private void checkArgs(BaseArgs args) {
        if (args.accessToken() == null) {
            args.setAccessToken(properties.getAccessToken());
            args.setSecret(properties.getSecret() == null ? null : properties.getSecret());
        }
    }

    /**
     * 设置 @ 参数
     *
     * @param atArgs      参数
     * @param sendRequest 发送请求
     */
    private void atArgs(AtArgs atArgs, OapiRobotSendRequest sendRequest) {
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();

        if (atArgs.isAtAll().equals(Boolean.TRUE)) {
            at.setIsAtAll(true);
        } else {
            if (!atArgs.atMobiles().isEmpty()) {
                at.setAtMobiles(atArgs.atMobiles());
            }
            if (!atArgs.atUserIds().isEmpty()) {
                at.setAtUserIds(atArgs.atUserIds());
            }
            at.setIsAtAll(false);
        }
        sendRequest.setAt(at);
    }

    /**
     * 文本
     *
     * @param textArgs    args参数
     * @param sendRequest 发送请求
     */
    private void textArgs(TextArgs textArgs, OapiRobotSendRequest sendRequest) {
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(textArgs.content());
        sendRequest.setText(text);
    }

    /**
     * markdown
     *
     * @param markdownArgs args参数
     * @param sendRequest  发送请求
     */
    private void markdownArgs(MarkdownArgs markdownArgs, OapiRobotSendRequest sendRequest) {
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(markdownArgs.title());
        markdown.setText(markdownArgs.text());
        sendRequest.setMarkdown(markdown);
    }

    /**
     * link
     *
     * @param linkArgs    连接参数
     * @param sendRequest 发送请求
     */
    private void linkArgs(LinkArgs linkArgs, OapiRobotSendRequest sendRequest) {
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setTitle(linkArgs.title());
        link.setText(linkArgs.text());
        link.setPicUrl(linkArgs.picUrl());
        link.setMessageUrl(linkArgs.messageUrl());
        sendRequest.setLink(link);
    }

    /**
     * 整体跳转的ActionCard类型
     *
     * @param wholeArgs   参数
     * @param sendRequest 发送请求
     */
    private void wholeArgs(ActionCardWholeArgs wholeArgs, OapiRobotSendRequest sendRequest) {
        OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
        actionCard.setTitle(wholeArgs.title());
        actionCard.setText(wholeArgs.text());
        actionCard.setBtnOrientation(wholeArgs.btnOrientation().getCode());
        actionCard.setSingleTitle(wholeArgs.singleTitle());
        actionCard.setSingleURL(wholeArgs.singleUrl());
        sendRequest.setActionCard(actionCard);
    }

    /**
     * 独立跳转ActionCard类型
     *
     * @param aloneArgs   参数
     * @param sendRequest 发送请求
     */
    private void aloneArgs(ActionCardAloneArgs aloneArgs, OapiRobotSendRequest sendRequest) {
        OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
        actionCard.setTitle(aloneArgs.title());
        actionCard.setText(aloneArgs.text());
        actionCard.setBtnOrientation(aloneArgs.btnOrientation().getCode());
        actionCard.setBtns(aloneArgs.dingBtns());
        sendRequest.setActionCard(actionCard);
    }

    /**
     * FeedCard类型
     *
     * @param args        参数
     * @param sendRequest 发送请求
     */
    private void feedCardArgs(FeedCardArgs feedCardArgs, OapiRobotSendRequest sendRequest) {
        OapiRobotSendRequest.Feedcard feedCard = new OapiRobotSendRequest.Feedcard();
        feedCard.setLinks(feedCardArgs.dingLinks());
        sendRequest.setFeedCard(feedCard);
    }
}

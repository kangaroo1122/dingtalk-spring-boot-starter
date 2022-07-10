package com.kangaroohy.dingtalk.entity.enums;

/**
 * 类 MsgType 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:38
 */
public enum MsgType {
    /**
     * 消息类型
     */
    TEXT("text"),
    MARKDOWN("markdown"),
    LINK("link"),
    ACTION_CARD("actionCard"),
    FEED_CARD("feedCard");

    private final String code;

    MsgType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

package com.kangaroohy.dingtalk.constant;

/**
 * 类 DingTalkConstant 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 15:48
 */
public class DingTalkConstant {
    private DingTalkConstant() {
    }

    /**
     * 钉钉消息发送地址
     */
    public static final String URL = "https://oapi.dingtalk.com/robot/send";

    /**
     * 配置前缀
     */
    public static final String PREFIX = "kangaroohy.ding-talk";

    /**
     * 链接打开方式前缀
     */
    public static final String PC_SLIDE_PREFIX = "dingtalk://dingtalkclient/page/link?url=";

    /**
     * 链接打开方式
     */
    public static final String PC_SLIDE = "&pc_slide=true";

    /**
     * 默认机器人所属群组id
     */
    public static final String DEFAULT_ROBOT_GROUP_ID = "default-robot-group-id";

}

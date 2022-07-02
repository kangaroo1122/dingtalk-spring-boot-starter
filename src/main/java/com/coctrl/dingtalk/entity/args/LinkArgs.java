package com.coctrl.dingtalk.entity.args;

import com.coctrl.dingtalk.constant.DingTalkConstant;
import com.coctrl.dingtalk.entity.args.base.PcSlideArgs;
import com.coctrl.dingtalk.entity.enums.MsgType;

import java.util.Objects;

/**
 * 类 LinkArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public class LinkArgs extends PcSlideArgs {

    private static final long serialVersionUID = -4674136998835804347L;

    /**
     * 图片URL。
     */
    private String picUrl;

    /**
     * 点击消息跳转的URL。
     */
    private String messageUrl;

    public String picUrl() {
        return picUrl;
    }

    public String messageUrl() {
        if (pcSlide().equals(Boolean.TRUE)) {
            return DingTalkConstant.PC_SLIDE_PREFIX + messageUrl + DingTalkConstant.PC_SLIDE;
        }
        return messageUrl;
    }

    @Override
    public MsgType msgType() {
        return MsgType.LINK;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends PcSlideArgs.Builder<Builder, LinkArgs> {
        @Override
        protected void validate(LinkArgs args) {
            super.validate(args);
            validateNotEmptyString(args.messageUrl, "messageUrl");
        }

        public Builder picUrl(String picUrl) {
            operations.add(args -> args.picUrl = picUrl);
            return this;
        }

        public Builder messageUrl(String messageUrl) {
            operations.add(args -> args.messageUrl = messageUrl);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkArgs)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        LinkArgs that = (LinkArgs) o;
        return Objects.equals(picUrl, that.picUrl) && Objects.equals(messageUrl, that.messageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), picUrl, messageUrl);
    }
}

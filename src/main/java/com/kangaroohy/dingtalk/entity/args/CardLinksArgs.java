package com.kangaroohy.dingtalk.entity.args;

import com.kangaroohy.dingtalk.constant.DingTalkConstant;
import com.kangaroohy.dingtalk.entity.args.base.SupperArgs;

import java.util.Objects;

/**
 * 类 CardLinksArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 00:10
 */
public class CardLinksArgs extends SupperArgs {

    private static final long serialVersionUID = 2946701356298269633L;

    /**
     * 按钮标题。
     */
    private String title;

    /**
     * 点击单条信息到跳转链接。
     */
    private String messageUrl;

    /**
     * 单条信息后面图片的URL。
     */
    private String picUrl;

    /**
     * true：表示在PC客户端侧边栏打开 <br/>
     * false：表示在浏览器打开 <br/>
     * https://open.dingtalk.com/document/orgapp-server/message-link-description
     */
    private Boolean pcSlide = false;

    public String title() {
        return title;
    }

    public String messageUrl() {
        if (pcSlide().equals(Boolean.TRUE)) {
            return DingTalkConstant.PC_SLIDE_PREFIX + messageUrl + DingTalkConstant.PC_SLIDE;
        }
        return messageUrl;
    }

    public String picUrl() {
        return picUrl;
    }

    public Boolean pcSlide() {
        return pcSlide;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends SupperArgs.Builder<Builder, CardLinksArgs> {

        public Builder title(String title) {
            operations.add(args -> args.title = title);
            return this;
        }

        public Builder messageUrl(String messageUrl) {
            operations.add(args -> args.messageUrl = messageUrl);
            return this;
        }

        public Builder picUrl(String picUrl) {
            operations.add(args -> args.picUrl = picUrl);
            return this;
        }

        public Builder pcSlide(Boolean pcSlide) {
            operations.add(args -> args.pcSlide = pcSlide);
            return this;
        }

        @Override
        protected void validate(CardLinksArgs args) {
            validateNotEmptyString(args.title, "title");
            validateNotEmptyString(args.messageUrl, "messageUrl");
            validateNotEmptyString(args.picUrl, "picUrl");
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(title, messageUrl, picUrl, pcSlide);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CardLinksArgs)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        CardLinksArgs buttonArgs = (CardLinksArgs) obj;
        return Objects.equals(title, buttonArgs.title)
                && Objects.equals(messageUrl, buttonArgs.messageUrl)
                && Objects.equals(picUrl, buttonArgs.picUrl)
                && Objects.equals(pcSlide, buttonArgs.pcSlide);
    }
}

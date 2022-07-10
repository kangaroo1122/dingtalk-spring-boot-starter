package com.kangaroohy.dingtalk.entity.args;

import com.kangaroohy.dingtalk.constant.DingTalkConstant;
import com.kangaroohy.dingtalk.entity.args.base.SupperArgs;

import java.util.Objects;

/**
 * 类 CardButtonArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 00:10
 */
public class CardButtonArgs extends SupperArgs {

    private static final long serialVersionUID = -413784322893756816L;

    /**
     * 按钮标题。
     */
    private String title;

    /**
     * 点击按钮触发的URL
     */
    private String actionUrl;

    /**
     * true：表示在PC客户端侧边栏打开 <br/>
     * false：表示在浏览器打开 <br/>
     * https://open.dingtalk.com/document/orgapp-server/message-link-description
     */
    private Boolean pcSlide;

    public String title() {
        return title;
    }

    public String actionUrl() {
        if (pcSlide().equals(Boolean.TRUE)) {
            return DingTalkConstant.PC_SLIDE_PREFIX + actionUrl + DingTalkConstant.PC_SLIDE;
        }
        return actionUrl;
    }

    public Boolean pcSlide() {
        return pcSlide;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends SupperArgs.Builder<Builder, CardButtonArgs> {

        public Builder title(String title) {
            operations.add(args -> args.title = title);
            return this;
        }

        public Builder actionUrl(String actionUrl) {
            operations.add(args -> args.actionUrl = actionUrl);
            return this;
        }

        public Builder pcSlide(Boolean pcSlide) {
            operations.add(args -> args.pcSlide = pcSlide);
            return this;
        }

        @Override
        protected void validate(CardButtonArgs args) {
            validateNotEmptyString(args.title, "title");
            validateNotEmptyString(args.actionUrl, "actionUrl");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, actionUrl, pcSlide);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CardButtonArgs)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        CardButtonArgs buttonArgs = (CardButtonArgs) obj;
        return Objects.equals(title, buttonArgs.title)
                && Objects.equals(actionUrl, buttonArgs.actionUrl)
                && Objects.equals(pcSlide, buttonArgs.pcSlide);
    }
}

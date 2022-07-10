package com.kangaroohy.dingtalk.entity.args;

import com.kangaroohy.dingtalk.constant.DingTalkConstant;
import com.kangaroohy.dingtalk.entity.args.base.ActionCardArgs;
import com.kangaroohy.dingtalk.entity.enums.MsgType;

import java.util.Objects;

/**
 * 类 ActionCardWholeArgs 功能描述：<br/>
 * 整体跳转的ActionCard类型
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public class ActionCardWholeArgs extends ActionCardArgs {

    private static final long serialVersionUID = 5109177796435201179L;

    /**
     * 单个按钮的标题。
     */
    private String singleTitle;

    /**
     * 单个按钮的跳转链接。
     */
    private String singleUrl;

    public String singleTitle() {
        return singleTitle;
    }

    public String singleUrl() {
        if (pcSlide().equals(Boolean.TRUE)) {
            return DingTalkConstant.PC_SLIDE_PREFIX + singleUrl + DingTalkConstant.PC_SLIDE;
        }
        return singleUrl;
    }

    @Override
    public MsgType msgType() {
        return MsgType.ACTION_CARD;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ActionCardArgs.Builder<Builder, ActionCardWholeArgs> {

        @Override
        protected void validate(ActionCardWholeArgs args) {
            super.validate(args);
            validateNotEmptyString(args.singleTitle, "singleTitle");
            validateNotEmptyString(args.singleUrl, "singleUrl");
        }

        public Builder singleTitle(String singleTitle) {
            operations.add(args -> args.singleTitle = singleTitle);
            return this;
        }

        public Builder singleUrl(String singleUrl) {
            operations.add(args -> args.singleUrl = singleUrl);
            return this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionCardWholeArgs)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ActionCardWholeArgs that = (ActionCardWholeArgs) o;
        return Objects.equals(singleTitle, that.singleTitle)
                && Objects.equals(singleUrl, that.singleUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), singleTitle, singleUrl);
    }
}

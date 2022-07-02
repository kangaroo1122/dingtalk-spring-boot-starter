package com.coctrl.dingtalk.entity.args;

import com.coctrl.dingtalk.entity.args.base.AtArgs;
import com.coctrl.dingtalk.entity.enums.MsgType;

import java.util.Objects;

/**
 * 类 TextArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public class TextArgs extends AtArgs {

    private static final long serialVersionUID = -6231676542407182044L;

    /**
     * 消息文本。
     */
    private String content;

    public String content() {
        return content;
    }

    @Override
    public MsgType msgType() {
        return MsgType.TEXT;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AtArgs.Builder<Builder, TextArgs> {

        @Override
        protected void validate(TextArgs args) {
            validateNotEmptyString(args.content, "content");
        }

        public Builder content(String content) {
            operations.add(args -> args.content = content);
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TextArgs)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TextArgs that = (TextArgs) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content);
    }
}

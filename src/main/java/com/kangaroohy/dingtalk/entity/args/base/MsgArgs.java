package com.kangaroohy.dingtalk.entity.args.base;

import java.util.Objects;

/**
 * 类 MsgArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:10
 */
public abstract class MsgArgs extends AtArgs {

    private static final long serialVersionUID = 7876927921988750445L;

    /**
     * link：消息标题。<br/>
     * markdown/actionCard：首屏会话透出的展示内容。<br/>
     */
    protected String title;

    /**
     * link：消息内容。如果太长只会部分展示。<br/>
     * markdown/actionCard：Markdown格式的消息内容。<br/>
     */
    protected String text;

    public String title() {
        return title;
    }

    public String text() {
        return text;
    }

    public abstract static class Builder<B extends Builder<B, A>, A extends MsgArgs> extends AtArgs.Builder<B, A> {
        @Override
        protected void validate(A args) {
            super.validate(args);
            validateNotEmptyString(args.title, "title");
            validateNotEmptyString(args.text, "text");
        }

        @SuppressWarnings("unchecked")
        public B title(String title) {
            operations.add(args -> args.title = title);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B text(String text) {
            operations.add(args -> args.text = text);
            return (B) this;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, text);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MsgArgs)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        MsgArgs that = (MsgArgs) obj;
        return Objects.equals(title, that.title) && Objects.equals(text, that.text);
    }
}

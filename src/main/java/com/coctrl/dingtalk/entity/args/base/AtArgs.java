package com.coctrl.dingtalk.entity.args.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类 AtArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:10
 */
public abstract class AtArgs extends BaseArgs {

    private static final long serialVersionUID = 6446984514562293929L;

    /**
     * 被@人的手机号。<br/>
     * 说明 消息内容content中要带上"@手机号"，跟atMobiles参数结合使用，才有@效果
     */
    protected List<String> atMobiles = new ArrayList<>();

    /**
     * 被@人的用户userid。
     */
    protected List<String> atUserIds = new ArrayList<>();

    /**
     * 需要 @所有人是true，否则为false。
     */
    protected Boolean isAtAll = false;

    public List<String> atMobiles() {
        return atMobiles;
    }

    public List<String> atUserIds() {
        return atUserIds;
    }

    public Boolean isAtAll() {
        return isAtAll;
    }

    public abstract static class Builder<B extends Builder<B, A>, A extends AtArgs> extends BaseArgs.Builder<B, A> {

        @Override
        protected void validate(A args) {
            if (!args.atMobiles.isEmpty() || !args.atUserIds.isEmpty()) {
                args.isAtAll = false;
            }
        }

        @SuppressWarnings("unchecked")
        public B atMobiles(List<String> atMobiles) {
            operations.add(args -> args.atMobiles = atMobiles);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B atUserIds(List<String> atUserIds) {
            operations.add(args -> args.atUserIds = atUserIds);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B isAtAll(Boolean isAtAll) {
            operations.add(args -> args.isAtAll = isAtAll);
            return (B) this;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), atMobiles, atUserIds, isAtAll);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AtArgs)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        AtArgs that = (AtArgs) obj;
        return Objects.equals(atMobiles, that.atMobiles) && Objects.equals(atUserIds, that.atUserIds) && Objects.equals(isAtAll, that.isAtAll);
    }
}

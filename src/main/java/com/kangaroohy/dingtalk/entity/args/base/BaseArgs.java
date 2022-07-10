package com.kangaroohy.dingtalk.entity.args.base;

import com.kangaroohy.dingtalk.entity.enums.MsgType;

import java.util.Objects;

/**
 * 类 BaseArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 11:49
 */
public abstract class BaseArgs extends SupperArgs {

    private static final long serialVersionUID = -973379467925936603L;

    /**
     * 机器人accessToken
     */
    protected String accessToken;

    /**
     * 机器人secret
     */
    protected String secret;

    public String accessToken() {
        return accessToken;
    }

    public String secret() {
        return secret;
    }

    /**
     * msg类型
     *
     * @return {@link MsgType}
     */
    public abstract MsgType msgType();

    public String setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this.accessToken;
    }

    public String setSecret(String secret) {
        this.secret = secret;
        return this.secret;
    }

    public abstract static class Builder<B extends Builder<B, A>, A extends BaseArgs> extends SupperArgs.Builder<B, A> {

        @SuppressWarnings("unchecked")
        public B accessToken(String accessToken) {
            operations.add(args -> args.accessToken = accessToken);
            return (B) this;
        }

        @SuppressWarnings("unchecked")
        public B secret(String secret) {
            operations.add(args -> args.secret = secret);
            return (B) this;
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, secret, msgType());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseArgs)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        BaseArgs baseArgs = (BaseArgs) obj;
        return Objects.equals(accessToken, baseArgs.accessToken)
                && Objects.equals(secret, baseArgs.secret)
                && Objects.equals(msgType(), baseArgs.msgType());
    }
}

package com.coctrl.dingtalk.multiple.entity;

import com.coctrl.dingtalk.entity.args.base.SupperArgs;

import java.util.Objects;

/**
 * 类 DingTalkArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 15:55
 */
public class DingTalkArgs extends SupperArgs {

    private static final long serialVersionUID = -7559401288597936699L;

    /**
     * 机器人accessToken
     */
    private String accessToken;

    /**
     * 机器人secret
     */
    private String secret;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends SupperArgs.Builder<Builder, DingTalkArgs> {

        public Builder accessToken(String accessToken) {
            operations.add(args -> args.accessToken = accessToken);
            return this;
        }

        public Builder secret(String secret) {
            operations.add(args -> args.secret = secret);
            return this;
        }

        @Override
        protected void validate(DingTalkArgs args) {
            validateNotEmptyString(args.accessToken, "accessToken");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, secret);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DingTalkArgs)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        DingTalkArgs dingTalkArgs = (DingTalkArgs) obj;
        return Objects.equals(accessToken, dingTalkArgs.accessToken)
                && Objects.equals(secret, dingTalkArgs.secret);
    }
}

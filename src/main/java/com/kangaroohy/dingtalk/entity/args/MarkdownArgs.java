package com.kangaroohy.dingtalk.entity.args;

import com.kangaroohy.dingtalk.entity.args.base.MsgArgs;
import com.kangaroohy.dingtalk.entity.enums.MsgType;

/**
 * 类 MarkdownArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public class MarkdownArgs extends MsgArgs {

    private static final long serialVersionUID = 3843256006616483055L;

    @Override
    public MsgType msgType() {
        return MsgType.MARKDOWN;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends MsgArgs.Builder<Builder, MarkdownArgs> { }
}

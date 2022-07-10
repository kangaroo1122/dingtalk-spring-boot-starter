package com.kangaroohy.dingtalk.entity.args.base;

import java.util.Objects;

/**
 * 类 PcSlideArgs 功能描述：<br/>
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public abstract class PcSlideArgs extends MsgArgs {

    private static final long serialVersionUID = -5126912512068770487L;

    /**
     * true：表示在PC客户端侧边栏打开 <br/>
     * false：表示在浏览器打开 <br/>
     * https://open.dingtalk.com/document/orgapp-server/message-link-description
     */
    protected Boolean pcSlide = false;

    public Boolean pcSlide() {
        return pcSlide;
    }

    public abstract static class Builder<B extends Builder<B, A>, A extends PcSlideArgs> extends MsgArgs.Builder<B, A> {

        @SuppressWarnings("unchecked")
        public B pcSlide(Boolean pcSlide) {
            operations.add(args -> args.pcSlide = pcSlide);
            return (B) this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PcSlideArgs)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PcSlideArgs that = (PcSlideArgs) o;
        return Objects.equals(pcSlide, that.pcSlide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pcSlide);
    }
}

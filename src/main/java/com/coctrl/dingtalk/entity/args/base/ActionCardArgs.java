package com.coctrl.dingtalk.entity.args.base;

import com.coctrl.dingtalk.entity.enums.BtnOrientation;

import java.util.Objects;

/**
 * 类 ActionCardArgs 功能描述：<br/>
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public abstract class ActionCardArgs extends PcSlideArgs {

    private static final long serialVersionUID = 1468499489239170824L;

    /**
     * 按钮布局
     */
    protected BtnOrientation btnOrientation = BtnOrientation.VERTICAL;

    public BtnOrientation btnOrientation() {
        return btnOrientation;
    }

    public abstract static class Builder<B extends Builder<B, A>, A extends ActionCardArgs> extends PcSlideArgs.Builder<B, A> {

        @SuppressWarnings("unchecked")
        public B btnOrientation(BtnOrientation btnOrientation) {
            operations.add(args -> args.btnOrientation = btnOrientation);
            return (B) this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionCardArgs)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ActionCardArgs that = (ActionCardArgs) o;
        return Objects.equals(btnOrientation, that.btnOrientation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), btnOrientation);
    }
}

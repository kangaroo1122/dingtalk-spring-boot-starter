package com.kangaroohy.dingtalk.entity.args;

import com.kangaroohy.dingtalk.entity.args.base.ActionCardArgs;
import com.kangaroohy.dingtalk.entity.enums.MsgType;
import com.kangaroohy.dingtalk.exception.DingTalkException;
import com.dingtalk.api.request.OapiRobotSendRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类 ActionCardAloneArgs 功能描述：<br/>
 * 独立跳转ActionCard类型
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public class ActionCardAloneArgs extends ActionCardArgs {

    private static final long serialVersionUID = -9054653617987500866L;

    /**
     * 单个按钮的标题。
     */
    private List<CardButtonArgs> btns = new ArrayList<>();

    public List<CardButtonArgs> btns() {
        return btns;
    }

    public List<OapiRobotSendRequest.Btns> dingBtns() {
        List<OapiRobotSendRequest.Btns> btnsList = new ArrayList<>();
        for (CardButtonArgs args : btns()) {
            OapiRobotSendRequest.Btns bt = new OapiRobotSendRequest.Btns();
            bt.setTitle(args.title());
            bt.setActionURL(args.actionUrl());
            btnsList.add(bt);
        }
        return btnsList;
    }

    @Override
    public MsgType msgType() {
        return MsgType.ACTION_CARD;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends ActionCardArgs.Builder<Builder, ActionCardAloneArgs> {

        @Override
        protected void validate(ActionCardAloneArgs args) {
            super.validate(args);
            if (args.btns().isEmpty()) {
                throw new DingTalkException("请填写btns参数");
            }
        }

        public Builder btns(List<CardButtonArgs> btns) {
            operations.add(args -> args.btns = btns);
            return this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionCardAloneArgs)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ActionCardAloneArgs that = (ActionCardAloneArgs) o;
        return Objects.equals(btns, that.btns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), btns);
    }

}

package com.kangaroohy.dingtalk.entity.args;

import com.kangaroohy.dingtalk.entity.args.base.AtArgs;
import com.kangaroohy.dingtalk.entity.enums.MsgType;
import com.kangaroohy.dingtalk.exception.DingTalkException;
import com.dingtalk.api.request.OapiRobotSendRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类 TextArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:26
 */
public class FeedCardArgs extends AtArgs {

    private static final long serialVersionUID = -2344056521897735622L;

    /**
     * 消息文本。
     */
    private List<CardLinksArgs> links = new ArrayList<>();

    public List<CardLinksArgs> links() {
        return links;
    }

    public List<OapiRobotSendRequest.Links> dingLinks() {
        List<OapiRobotSendRequest.Links> linksList = new ArrayList<>();
        for (CardLinksArgs args : links()) {
            OapiRobotSendRequest.Links link = new OapiRobotSendRequest.Links();
            link.setTitle(args.title());
            link.setMessageURL(args.messageUrl());
            link.setPicURL(args.picUrl());
            linksList.add(link);
        }
        return linksList;
    }

    @Override
    public MsgType msgType() {
        return MsgType.FEED_CARD;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AtArgs.Builder<Builder, FeedCardArgs> {

        public Builder links(List<CardLinksArgs> links) {
            operations.add(args -> args.links = links);
            return this;
        }

        @Override
        protected void validate(FeedCardArgs args) {
            if (args.links.isEmpty()) {
                throw new DingTalkException("请填写links参数");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeedCardArgs)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        FeedCardArgs that = (FeedCardArgs) o;
        return Objects.equals(links, that.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), links);
    }
}

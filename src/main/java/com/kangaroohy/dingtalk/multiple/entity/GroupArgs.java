package com.kangaroohy.dingtalk.multiple.entity;

import com.kangaroohy.dingtalk.entity.args.base.SupperArgs;
import com.kangaroohy.dingtalk.exception.DingTalkException;
import com.kangaroohy.dingtalk.multiple.algorithm.AlgorithmHandler;
import com.kangaroohy.dingtalk.multiple.algorithm.DingTalkHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 类 GroupArgs 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/07/01 16:18
 */
public class GroupArgs extends SupperArgs {

    private static final long serialVersionUID = 1824011346911447770L;

    /**
     * 分组ID，唯一标识
     */
    private String groupId;

    /**
     * 消息发送算法
     */
    private AlgorithmHandler algorithmHandler = new DingTalkHandler();

    /**
     * 当前组机器人集合
     */
    private List<DingTalkArgs> dingTalkArgs = new ArrayList<>();

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setAlgorithmHandler(AlgorithmHandler algorithmHandler) {
        this.algorithmHandler = algorithmHandler;
    }

    public AlgorithmHandler getAlgorithmHandler() {
        return algorithmHandler;
    }

    public void setDingTalkArgs(List<DingTalkArgs> dingTalkArgs) {
        this.dingTalkArgs = dingTalkArgs;
    }

    public List<DingTalkArgs> getDingTalkArgs() {
        return dingTalkArgs;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends SupperArgs.Builder<Builder, GroupArgs> {

        public Builder groupId(String groupId) {
            operations.add(args -> args.groupId = groupId);
            return this;
        }

        public Builder algorithmHandler(AlgorithmHandler algorithmHandler) {
            operations.add(args -> args.algorithmHandler = algorithmHandler);
            return this;
        }

        public Builder dingTalkArgs(List<DingTalkArgs> dingTalkArgs) {
            operations.add(args -> args.dingTalkArgs = dingTalkArgs);
            return this;
        }

        @Override
        protected void validate(GroupArgs args) {
            validateNotEmptyString(args.groupId, "groupId");

            if (args.dingTalkArgs.isEmpty()) {
                throw new DingTalkException("请配置机器人信息");
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, algorithmHandler, dingTalkArgs);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GroupArgs)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        GroupArgs groupArgs = (GroupArgs) obj;
        return Objects.equals(groupId, groupArgs.groupId)
                && Objects.equals(algorithmHandler, groupArgs.algorithmHandler)
                && Objects.equals(dingTalkArgs, groupArgs.dingTalkArgs);
    }
}

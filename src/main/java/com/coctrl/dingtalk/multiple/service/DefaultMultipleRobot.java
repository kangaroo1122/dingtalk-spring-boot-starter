package com.coctrl.dingtalk.multiple.service;

import com.coctrl.dingtalk.configuration.DingTalkProperties;
import com.coctrl.dingtalk.constant.DingTalkConstant;
import com.coctrl.dingtalk.exception.DingTalkException;
import com.coctrl.dingtalk.multiple.entity.DingTalkArgs;
import com.coctrl.dingtalk.multiple.entity.GroupArgs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 类 DefaultMultipleRobot 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/07/01 17:29
 */
public class DefaultMultipleRobot implements IMultipleRobot {

    private final Map<String, GroupArgs> groupArgsMap = new HashMap<>();

    public DefaultMultipleRobot(DingTalkProperties properties) {
        GroupArgs groupArgs = GroupArgs.builder()
                .groupId(DingTalkConstant.DEFAULT_ROBOT_GROUP_ID)
                .dingTalkArgs(Collections.singletonList(DingTalkArgs.builder().accessToken(properties.getAccessToken()).secret(properties.getSecret() == null ? null : properties.getSecret()).build()))
                .build();
        groupArgsMap.put(DingTalkConstant.DEFAULT_ROBOT_GROUP_ID, groupArgs);
    }


    @Override
    public Map<String, GroupArgs> groupRobotMap() {
        return groupArgsMap;
    }

    @Override
    public void addGroupRobot(GroupArgs groupArgs) {
        if (groupArgs.getGroupId().equals(DingTalkConstant.DEFAULT_ROBOT_GROUP_ID)) {
            throw new DingTalkException("群组ID不可与默认组相同");
        }
        groupRobotMap().put(groupArgs.getGroupId(), groupArgs);
    }

    @Override
    public void addGroupRobot(List<GroupArgs> groupArgs) {
        if (groupArgs.isEmpty()) {
            throw new DingTalkException("群组信息无效");
        }
        List<GroupArgs> collect = groupArgs.stream().filter(item -> item.getGroupId().equals(DingTalkConstant.DEFAULT_ROBOT_GROUP_ID)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            throw new DingTalkException("群组ID不可与默认组相同");
        }
        Set<String> groupIdSet = groupArgs.stream().map(GroupArgs::getGroupId).collect(Collectors.toSet());
        if (groupIdSet.size() != groupArgs.size()) {
            throw new DingTalkException("群组ID不可重复");
        }
        groupArgs.forEach(item -> groupRobotMap().put(item.getGroupId(), item));
    }
}

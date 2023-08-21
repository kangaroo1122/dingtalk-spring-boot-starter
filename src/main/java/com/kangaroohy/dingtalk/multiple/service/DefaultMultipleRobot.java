package com.kangaroohy.dingtalk.multiple.service;

import com.kangaroohy.dingtalk.configuration.DingTalkProperties;
import com.kangaroohy.dingtalk.constant.DingTalkConstant;
import com.kangaroohy.dingtalk.exception.DingTalkException;
import com.kangaroohy.dingtalk.multiple.entity.DingTalkArgs;
import com.kangaroohy.dingtalk.multiple.entity.GroupArgs;
import org.springframework.util.StringUtils;

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

        Map<String, DingTalkProperties.GroupProperties> groups = properties.getGroups();
        List<DingTalkProperties.RobotProperties> collect = groups.values().stream().map(DingTalkProperties.GroupProperties::getRobots)
                .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);
        int size = (int) collect.stream().map(item -> StringUtils.hasText(item.getAccessToken())).count();
        if (size != collect.size()) {
            throw new DingTalkException("机器人accessToken必须配置");
        }
        if (!groups.isEmpty()) {
            groups.forEach((groupId, robots) -> {
                List<DingTalkArgs> dingTalkArgs = new ArrayList<>();
                robots.getRobots().forEach(robot -> dingTalkArgs.add(DingTalkArgs.builder().accessToken(robot.getAccessToken()).secret(robot.getSecret()).build()));
                try {
                    groupArgsMap.put(groupId, GroupArgs.builder().groupId(groupId).dingTalkArgs(dingTalkArgs).algorithmHandler(robots.getAlgorithmHandler().newInstance()).build());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new DingTalkException(e);
                }
            });
        }
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

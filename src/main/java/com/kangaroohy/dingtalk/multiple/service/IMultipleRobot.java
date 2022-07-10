package com.kangaroohy.dingtalk.multiple.service;

import com.kangaroohy.dingtalk.multiple.entity.GroupArgs;

import java.util.List;
import java.util.Map;

/**
 * 类 IMultipleRobotService 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/07/01 17:28
 */
public interface IMultipleRobot {

    /**
     * 群机器人列表
     *
     * @return {@link Map}<{@link String}, {@link GroupArgs}>
     */
    Map<String, GroupArgs> groupRobotMap();

    /**
     * 添加组机器人
     *
     * @param groupArgs 组参数
     */
    void addGroupRobot(GroupArgs groupArgs);

    /**
     * 添加组机器人
     *
     * @param groupArgs 组参数
     */
    void addGroupRobot(List<GroupArgs> groupArgs);
}

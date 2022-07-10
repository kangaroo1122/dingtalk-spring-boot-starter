package com.kangaroohy.dingtalk.multiple.algorithm;

import com.kangaroohy.dingtalk.configuration.DingTalkProperties;
import com.kangaroohy.dingtalk.multiple.entity.DingTalkArgs;

import java.io.Serializable;
import java.util.List;

/**
 * 类 AlgorithmHandler 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 16:27
 */
public interface AlgorithmHandler extends Serializable {
    /** 默认索引号从0开始 */
    int DEFAULT_INDEX = 0;

    /**
     * 具体算法处理逻辑
     *
     * @param dingTalkArgs        多钉钉机器人配置集
     * @param defaultDingTalkArgs 默认钉钉机器人配置
     * @return {@link DingTalkArgs} 返回当前应该使用的钉钉机器人配置
     */
    DingTalkArgs handler(List<DingTalkArgs> dingTalkArgs, DingTalkArgs defaultDingTalkArgs);

    /**
     * dingTalkConfig
     *
     * @param dingTalkArgs        多钉钉机器人配置集
     * @param defaultDingTalkArgs 默认钉钉机器人配置
     * @return {@link DingTalkProperties} 返回当前应该使用的钉钉机器人配置
     */
    default DingTalkArgs dingTalkConfig(List<DingTalkArgs> dingTalkArgs, DingTalkArgs defaultDingTalkArgs) {
        if (dingTalkArgs == null || dingTalkArgs.isEmpty()) {
            return defaultDingTalkArgs;
        }

        if (dingTalkArgs.size() == 1) {
            return dingTalkArgs.get(0);
        }

        return handler(dingTalkArgs, defaultDingTalkArgs);
    }

    /**
     * 算法ID
     *
     * @return algorithmId
     */
    default String algorithmId() {
        return this.getClass().getSimpleName();
    }
}

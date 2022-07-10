package com.kangaroohy.dingtalk.multiple.algorithm;

import com.kangaroohy.dingtalk.multiple.entity.DingTalkArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 类 RoundRobinHandler 功能描述：<br/>
 * 轮询算法
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 16:44
 */
public class RoundRobinHandler implements AlgorithmHandler{
    private static final long serialVersionUID = -4395009071017032774L;

    private static final Logger log = LoggerFactory.getLogger(RoundRobinHandler.class);

    /** 索引值 */
    private volatile int index = DEFAULT_INDEX;

    @Override
    public DingTalkArgs handler(List<DingTalkArgs> dingTalkArgs, DingTalkArgs defaultDingTalkArgs) {
        int size = dingTalkArgs.size();
        int idx = index;
        synchronized (this) {
            index++;
            index = index >= size ? DEFAULT_INDEX : index;

            if (log.isDebugEnabled()) {
                log.debug("#{}# 当前使用第{}个机器人", algorithmId(), idx);
            }
        }
        return dingTalkArgs.get(idx);
    }
}

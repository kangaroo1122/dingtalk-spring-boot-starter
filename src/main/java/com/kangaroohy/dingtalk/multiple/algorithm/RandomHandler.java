package com.kangaroohy.dingtalk.multiple.algorithm;

import com.kangaroohy.dingtalk.multiple.entity.DingTalkArgs;
import com.kangaroohy.dingtalk.utils.DingTalkUtils;

import java.util.List;

/**
 * 类 RandomHandler 功能描述：<br/>
 * 随机选择算法
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 16:41
 */
public class RandomHandler implements AlgorithmHandler {

    private static final long serialVersionUID = 8956541398313500651L;

    @Override
    public DingTalkArgs handler(List<DingTalkArgs> dingTalkArgs, DingTalkArgs defaultDingTalkArgs) {
        int size = dingTalkArgs.size();
        int index = DingTalkUtils.JVM_RANDOM.nextInt(size);
        return dingTalkArgs.get(index);
    }
}

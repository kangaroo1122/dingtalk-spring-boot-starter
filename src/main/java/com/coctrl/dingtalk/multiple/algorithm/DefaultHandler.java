package com.coctrl.dingtalk.multiple.algorithm;

import com.coctrl.dingtalk.multiple.entity.DingTalkArgs;

import java.util.List;

/**
 * 类 DefaultHandler 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 16:34
 */
public class DefaultHandler implements AlgorithmHandler{
    private static final long serialVersionUID = 4427532396759207354L;

    @Override
    public DingTalkArgs handler(List<DingTalkArgs> dingTalkArgs, DingTalkArgs defaultDingTalkArgs) {
        return defaultDingTalkArgs;
    }
}

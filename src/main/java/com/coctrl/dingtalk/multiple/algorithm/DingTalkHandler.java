package com.coctrl.dingtalk.multiple.algorithm;

import com.coctrl.dingtalk.multiple.entity.DingTalkArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类 DdingTalkHandler 功能描述：<br>
 * Dinger钉钉机器人算法 <br>
 *
 * <blockquote>
 *     VM options: {@code -Dmulti.dinger.minute.limit.count=2}
 * </blockquote>
 *
 * <blockquote>
 *     机器人发送消息频率限制
 *     <pre>
 *         https://ding-doc.dingtalk.com/doc#/serverapi3/pghqkk
 *     </pre>
 * </blockquote>
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 16:35
 */
public class DingTalkHandler implements AlgorithmHandler {
    private static final long serialVersionUID = 6906127521926386034L;

    private static final Logger log = LoggerFactory.getLogger(DingTalkHandler.class);
    public static final String DINGTALK_MULTI_DINGER_COUNT = "multi.dingtalk.minute.limit.count";
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern("yyMMddHHmm");
    private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    /** 每分钟内限制发送 COUNT 条 */
    private static final int COUNT_THRESHOLD;

    static {
        COUNT_THRESHOLD = System.getProperty(DINGTALK_MULTI_DINGER_COUNT) == null ?
                20 : Integer.parseInt(
                System.getProperty(DINGTALK_MULTI_DINGER_COUNT)
        );
    }

    /** 索引号 */
    private volatile int index = DEFAULT_INDEX;
    /** 当前分钟 */
    private String currentMinite = null;
    /** 计数器 */
    private AtomicInteger counter = new AtomicInteger(DEFAULT_INDEX);

    @Override
    public DingTalkArgs handler(List<DingTalkArgs> dingTalkArgs, DingTalkArgs defaultDingTalkArgs) {
        int size = dingTalkArgs.size();

        synchronized (this) {
            if (currentMinite == null) {
                currentMinite = LocalDateTime.now(ZONE_ID).format(DATETIME_FMT);
            }

            int count = counter.getAndIncrement();

            boolean countBool = count >= COUNT_THRESHOLD;
            String now = LocalDateTime.now(ZONE_ID).format(DATETIME_FMT);
            boolean inMinute = now.equals(currentMinite);

            if (countBool) {
                if (inMinute) {
                    index++;
                    index = index >= size ? DEFAULT_INDEX : index;
                }
                currentMinite = now;
                counter.set(1);

                if (log.isDebugEnabled()) {
                    log.debug("#{}-{}# 在{}分内发送了{}次, 当前分钟={}, 下一个机器人={}.",
                            algorithmId(), COUNT_THRESHOLD, currentMinite, count, now, index);
                }
            } else if (!inMinute) {
                currentMinite = now;
                counter.set(1);

                if (log.isDebugEnabled()) {
                    log.debug("#{}-{}# 在{}分内发送了{}次, 当前分钟={}, 当前机器人={}.",
                            algorithmId(), COUNT_THRESHOLD, currentMinite, count, now, index);
                }
            }
        }

        return dingTalkArgs.get(index);
    }
}

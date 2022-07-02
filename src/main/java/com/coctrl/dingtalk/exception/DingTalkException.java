package com.coctrl.dingtalk.exception;

/**
 * 类 DingTalkException 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/30 15:39
 */
public class DingTalkException extends RuntimeException {

    private static final long serialVersionUID = 3224597542922528096L;

    public DingTalkException() {
        super();
    }

    public DingTalkException(String message) {
        super(message);
    }

    public DingTalkException(String message, Throwable cause) {
        super(message, cause);
    }

    public DingTalkException(Throwable cause) {
        super(cause);
    }

    protected DingTalkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

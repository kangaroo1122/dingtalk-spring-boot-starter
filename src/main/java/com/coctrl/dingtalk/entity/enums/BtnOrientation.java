package com.coctrl.dingtalk.entity.enums;

/**
 * 类 BtnOrientation 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/29 14:38
 */
public enum BtnOrientation {
    /**
     * 按钮布局
     */
    VERTICAL("0", "竖向"),
    HORIZONTAL("1", "横向");

    private final String code;

    private final String desc;

    BtnOrientation(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

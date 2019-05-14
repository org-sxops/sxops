package com.sxops.www.linfen.common.util.log;

/**
 *
 */
public class LogKV {

    private String key;
    private String value;

    /**
     * 此处有修改,填加了默认值
     * 并提供一个String类型的.否则最后输出的时候还是要转String,而且.toString()不安全.
     */
    public LogKV (String value) {
       this.key = "_msg";
        if (value == null) {
            value = "";
        }
        this.value = value;
    }

    public LogKV(String key, Object value) {
        this.key = key;
        if (value == null) {
            value = "";
        }
        this.value = value.toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

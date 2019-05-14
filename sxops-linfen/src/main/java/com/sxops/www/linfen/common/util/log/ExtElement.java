package com.sxops.www.linfen.common.util.log;

/**
 *
 */
public class ExtElement {

    private String traceId = "";
    private String spanId = "";

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    /**
     * 源码迁移
     * @return spanId
     */
    public String getSpanId() {
        if (spanId == null || spanId.equals("")) {
            spanId = SpanIdGenerator.getSpanId();
        }
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

}

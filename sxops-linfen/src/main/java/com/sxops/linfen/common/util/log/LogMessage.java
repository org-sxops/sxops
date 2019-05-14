package com.sxops.linfen.common.util.log;

/**
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * log信息类
 */
public class LogMessage  {

    /**
     *
     * @param ex ex
     * @return this
     */
    public LogMessage add(Exception ex){
        StringBuffer stringBuffer = new StringBuffer(ex.toString() + "\n");
        StackTraceElement[] messages = ex.getStackTrace();
        int length = messages.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append("\t"+messages[i].toString()+"\n");
        }
        this.add("_eMsg",ex.getMessage());
        this.add("_eTrace",stringBuffer.toString());

        return this;
    }

    public static String HEADER_TRACEID = "didi-header-rid";
    public static String HEADER_SPANID = "didi-header-spanid";
    public static String serviceName = "";
    private static String DEFAULT_DLTAG = "_undef";
    private static ThreadLocal<ExtElement> extElements = new InheritableThreadLocal<ExtElement>() {
        protected ExtElement initialValue() {
            return new ExtElement();
        }
    };
    private String dltag = "";
    private String cspanId = "";
    private List<LogKV> logElements = new ArrayList();

    public LogMessage() {
    }

    public static LogMessage getNew() {
        return new LogMessage();
    }

    public static String getTraceId() {
        return ((ExtElement)extElements.get()).getTraceId();
    }

    public static void setTraceId(String traceId) {
        ((ExtElement)extElements.get()).setTraceId(traceId);
    }

    public static String getSpanId() {
        return ((ExtElement)extElements.get()).getSpanId();
    }

    public static void setSpanId(String spanId) {
        ((ExtElement)extElements.get()).setSpanId(spanId);
    }

    public static void setServiceName(String name) {
        serviceName = name;
    }

    public static String generatorNewSpanid() {
        String newSpanid = SpanIdGenerator.getSpanId();
        return newSpanid;
    }

    public static String genertorNewTraceid() {
        String traceid = SpanIdGenerator.getTraceId();
        return traceid;
    }

    public static void remove() {
        extElements.remove();
    }

    public LogMessage add(String valule) {
        LogKV logKV = new LogKV(valule);
        this.logElements.add(logKV);
        return this;
    }

    public LogMessage add(String key, Object value) {
        LogKV logKV = new LogKV(key, value);
        this.logElements.add(logKV);
        return this;
    }

    public List<LogKV> getLogElements() {
        return this.logElements;
    }

    public void setLogElements(List<LogKV> logElements) {
        this.logElements = logElements;
    }

    public String getDltag() {
        return this.dltag != null && !this.dltag.equals("") ? this.dltag : DEFAULT_DLTAG;
    }

    public LogMessage setDltag(String dltag) {
        this.dltag = dltag;
        return this;
    }

    public String getCspanId() {
        return this.cspanId;
    }

    public LogMessage setCspanId(String cspanId) {
        this.cspanId = cspanId;
        return this;
    }

    public String toString() {
        ExtElement extElement = (ExtElement)extElements.get();
        StringBuffer sb = new StringBuffer();
        sb.append(this.getDltag());
        sb.append("||traceid=");
        sb.append(extElement.getTraceId());
        sb.append("||spanid=");
        sb.append(extElement.getSpanId());
        sb.append("||cspanid=");
        sb.append(this.getCspanId());
        sb.append("||").append("serviceName=").append(serviceName);
        if (this.logElements.size() > 0) {
            Iterator var3 = this.logElements.iterator();

            while(var3.hasNext()) {
                LogKV param = (LogKV)var3.next();
                sb.append("||");
                sb.append(param.getKey());
                sb.append("=");
                sb.append(param.getValue());
            }
        }

        return sb.toString();
    }

}

package com.camelotchina.www.common.util.log;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public class SpanIdGenerator {

    private static final long IPFACTOR = getIpFactor();
    private static AtomicLong COUNTER = new AtomicLong();

    static {
        COUNTER.set(0);
    }

    /**
     *
     * @param ip ip
     * @return ret
     */
    private static long ip2long(String ip) {
        int ret = 0;
        String[] ipGroups = ip.split("\\.");
        Integer[] ipInt = new Integer[4];
        for (int i = 0; i < ipGroups.length; i++) {
            ipInt[i] = Integer.valueOf(ipGroups[i]);
        }
        ret = ipInt[0] << 24 | ipInt[1] << 16 | ipInt[2] << 8 | ipInt[3];
        return ret;
    }

    /**
     *
     * @return msec
     */
    private static long timeFactor() {
        Date data = new Date();
        long msec = data.getTime();
        return msec;
    }

    /**
     *
     * @return ret
     */
    private static long timeFactorInSecond() {
        return timeFactor() / 1000;
    }

    /**
     *
     * @return ret
     */
    private static long randFactor() {
        long ret = (long) (Math.random() * Math.pow(2, 32));
        return ret;
    }

    /**
     *
     * @return ipList
     */
    private static List<String> getLocalIpList() {
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            String ip;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
                        ip = inetAddress.getHostAddress();
                        ipList.add(ip);
                    }
                }
            }
        } catch (SocketException e) {
        }
        return ipList;
    }

    /**
     *
     * @return ip2long(ipAddr)
     */
    private static long getIpFactor() {
        List<String> ips = getLocalIpList();
        String ipAddr = "127.0.0.1";
        if (ips == null || ips.size() == 0) {
            return ip2long(ipAddr);
        }
        for (String iptmp : ips) {
            if (iptmp.equals("127.0.0.1")) {
                continue;
            } else {
                ipAddr = iptmp;
                break;
            }
        }
        return ip2long(ipAddr);
    }

    /**
     *
     * @return format
     */
    static String getSpanId() {
        long timeFactor = timeFactor();
        long randFactor = randFactor();
        long spanId = ((IPFACTOR & timeFactor) << 32) | (randFactor & 0xffffffff);
        return handleFormat(Long.toHexString(spanId), 16);
    }

    /**
     *
     * @return format
     */
    // 不建议自动生成traceid 建议使用ng生成的traceid
    static String getTraceId() {
        long timeFactor = timeFactorInSecond();
        long randFactor1 = randFactor();
        long randFactor2 = COUNTER.incrementAndGet();
        // ip随机种子+时间随机种子
        long traceIdpart1 = IPFACTOR << 32 | (timeFactor & 0xffffffff);
        // 随机种子+自增序列+86特殊标识
        long traceIdpart2 = randFactor1 << 32 | (randFactor2 & 0xffffff) << 8 | 0x86;
        return handleFormat(Long.toHexString(traceIdpart1), 16) + handleFormat(Long.toHexString(traceIdpart2), 16);
    }

    /**
     *
     * @param value value
     * @param digit value
     * @return value
     */
    private static String handleFormat(String value, int digit) {
        if (value == null) {
            return "00000000";
        }
        while (value.length() < digit) {
            value = "0" + value;
        }
        return value;
    }

}

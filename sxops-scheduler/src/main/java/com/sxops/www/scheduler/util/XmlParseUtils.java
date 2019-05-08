package com.sxops.www.scheduler.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

/**
 * <p>Description: [XML解析工具]</p>
 * Created on: 2017/7/11
 *
 * @author: <a href="mailto: sunyang@sxops.com">sunyang<br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2016年 山西省壹加柒网络技术有限公司 交付部
 */
public class XmlParseUtils {

    /** 日志 */
    private static final org.slf4j.Logger LOGGER = LoggerFactory
            .getLogger(XmlParseUtils.class);

    /**
     * <p>Description:[解析XML文件]</p>
     * @param xmlFile xml文件
     * @return 返回XML内容结构。格式：{name: xx, children: [{name: xx, value: xx}]}
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    public static Map<String, Object> parse(InputStream xmlFile) {
        Map<String, Object> result = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(xmlFile);
            Element root = document.getRootElement();
            wrapChildren(root, result);
        } catch (DocumentException e) {
            e.printStackTrace();
            LOGGER.error("方法:XmlParseUtils异常",e);
        }
        return result;
    }

    /**
     * <p>Description:[解析元素下的所有子孙元素]</p>
     * @param element 要解析的元素
     * @return 子孙元素集合
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    private static List<Map<String, Object>> getChildren(Element element) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Iterator<Element> it = element.elementIterator(); it.hasNext(); ) {
            Element ele = it.next();
            Map<String, Object> info = new HashMap<>();
            wrapChildren(ele, info);
            result.add(info);
        }
        return result;
    }

    /**
     * <p>Description:[解析元素下的所有子孙元素]</p>
     * @param element 要解析的元素
     * @return 子孙元素Map
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    private static Map<String, Object> getMapChildren(Element element) {
        Map<String, Object> result = new HashMap<>();
        for (Iterator<Element> it = element.elementIterator(); it.hasNext(); ) {
            Element ele = it.next();
            wrapChildren(ele, result);
        }
        return result;
    }

    /**
     * <p>Description:[获取并包装子孙节点]</p>
     * @param ele 要获取子孙节点的元素
     * @param result 子孙节点集合。格式：{tagName: List/Map}
     *
     * Created on 2017/11/10
     * @author: 尹归晋
     */
    private static void wrapChildren(Element ele, Map<String, Object> result) {
        if (ele.isTextOnly()) {
            result.put(ele.getName(), ele.getData());
        } else {
            if (ele.attribute("hasChildren") != null && "true".equals(ele.attribute("hasChildren").getValue())) {
                List<Map<String, Object>> children = getChildren(ele);
                result.put(ele.getName(), children);
            } else {
                Map<String, Object> children = getMapChildren(ele);
                result.put(ele.getName(), children);
            }
        }
    }

}

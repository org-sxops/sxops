<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
</#if>

<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
		<#list table.fields as field>
            <#if field.keyFlag >
            <id column="${field.name}" property="${field.propertyName}" />
            <#else>
            <result column="${field.name}" property="${field.propertyName}" />
            </#if>
		</#list>
    </resultMap>
</#if>

    <!--基本字段列表-->
    <sql id="Base_Column_List">
        <#list table.fields as field>
        ${field.name}<#sep>,
        </#list>

    </sql>



</mapper>

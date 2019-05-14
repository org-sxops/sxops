package ${package.Entity};


<#list table.importPackages as pkg>
import ${pkg};
</#list>
import javax.persistence.*;
import java.io.Serializable;
/**
 * <p>Description: [${table.comment}实体类]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on ${date}
 * @author  <a href="mailto: ${cfg.email}">${author}</a>
 * @version 1.0
 */
<#if tableAnnotation??&&tableAnnotation >
@Table(name = "${table.name}")
</#if>
<#if superEntityClass?? >
public class ${entity} extends ${superEntityClass}<#if activeRecord ><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#else>
public class ${entity} {
</#if>

<#list table.fields as field>
	/**
	 * <#if field.comment??&& field.comment!= "">${field.comment}</#if>
	 **/
	<#if field.keyFlag >
		<#assign  keyPropertyName=field.propertyName/>
	@Id
	</#if>
	<#if field.convert >
	@Column(name = "${field.name}")
	</#if>
	private ${field.propertyType} ${field.propertyName};
	<#if cfg.rangeDateFileds?seq_contains(field.propertyName)>
    /**
    * <p>${field.comment}范围查询-开始</p>
    */
	private String ${field.propertyName}Start;

    /**
    * <p>${field.comment}范围查询-结束</p>
    */
	private String ${field.propertyName}End;
	</#if>

</#list>


<#list table.fields as field>
		<#assign getprefix="get"/>
	/**
	 * <p>Description:[获取${field.comment}]</p>
	 * Created on ${date}
	 * @return ${field.propertyType} ${field.comment}
	 * @author [${author}]
	 */	
	public ${field.propertyType} ${getprefix}${field.capitalName}() {
		return ${field.propertyName};
	}


  	<#if entityBuliderModel >
	/**
	 * <p>Discription:[设置${field.comment}]</p>
	 * Created on ${date}
	 * @param ${field.propertyType} ${field.propertyName}
	 * @return ${entity} ${field.comment}
	 * @author [${author}]
	 */
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
	<#else>
	/**
	 * <p>Discription:[设置${field.comment}]</p>
	 * Created on ${date}
	 * @param ${field.propertyName} ${field.comment}
	 * @author [${author}]
	 */		
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
	</#if>
		this.${field.propertyName} = ${field.propertyName};
	<#if entityBuliderModel >
   		 return this;
	</#if>
	}

	<#if "Date"?contains(field.propertyType) && cfg.rangeDateFileds?seq_contains(field.propertyName)>
    /**
    * <p>Discription:[获取${field.comment}范围查询-开始]</p>
    * Created on ${date}
    * @return String ${field.comment}范围查询-开始
    * @author [${author}]
    */
    public String ${getprefix}${field.capitalName}Start() {
    	return ${field.propertyName}Start;
    }

    /**
    * <p>Discription:[获取${field.comment}范围查询-结束]</p>
    * Created on ${date}
    * @return String ${field.comment}范围查询-结束
    * @author [${author}]
    */
    public String ${getprefix}${field.capitalName}End() {
    	return ${field.propertyName}End;
    }

    /**
    * <p>Discription:[设置${field.comment}范围查询-开始]</p>
    * Created on ${date}
    * @param ${field.propertyName}Start ${field.comment}范围查询-开始
    * @author [${author}]
    */
    public void set${field.capitalName}Start(String ${field.propertyName}Start) {
    	this.${field.propertyName}Start = ${field.propertyName}Start;
    }

    /**
    * <p>Discription:[设置${field.comment}范围查询-结束]</p>
    * Created on ${date}
    * @param ${field.propertyName}End ${field.comment}范围查询-结束
    * @author [${author}]
    */
    public void set${field.capitalName}End(String ${field.propertyName}End) {
    	this.${field.propertyName}End = ${field.propertyName}End;
    }
	</#if>
	
</#list>

<#if entityColumnConstant>
	<#list table.fields as field>
    public static final String ${field.name.toUpperCase()} = "${field.name}";

	</#list>
</#if>
<#if activeRecord >
	@Override
	protected Serializable pkVal() {
	<#if keyPropertyName?? >
    	return this.${keyPropertyName};
	<#else>
    	return this.id;
	</#if>
	}

</#if>
}

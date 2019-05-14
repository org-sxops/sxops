package ${package.Controller};

import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
<#if superControllerClassPackage?? >
import ${superControllerClassPackage};
</#if>

/** 
 * <p>Description: [${table.comment}控制器]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on ${date}
 * @author  <a href="mailto: ${cfg.email}">${author}</a>
 * @version 1.0 
 */ 
@Controller
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if><#if package.SubModuleName??>/${package.SubModuleName}</#if>/${table.viewName}")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    /**
     * <p>Discription:[${table.comment}服务]</p>
     */
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
	 * <p>Discription:[跳转到${table.comment}主页]</p>
	 * Created on ${date}
	 * @return String ${table.comment}主页
	 * @author:[${author}]
	 */	     
    @RequestMapping("toIndex")
    @RequiresPermissions("<#if package.SubModuleName??>${package.SubModuleName}:</#if>${table.viewName}:toIndex")
    public String toIndex() {
        return "<#if package.SubModuleName??>${package.SubModuleName}/</#if>${table.viewName}/${table.viewName}_index";
    }

    /**
	 * <p>Discription:[跳转到${table.comment}添加页面]</p>
	 * Created on ${date}
	 * @return String ${table.comment}添加界面
	 * @author:[${author}]
	 */    
    @RequestMapping("toAdd")
    @RequiresPermissions("<#if package.SubModuleName??>${package.SubModuleName}:</#if>${table.viewName}:toAdd")
    public String toAdd() {
        return "<#if package.SubModuleName??>${package.SubModuleName}/</#if>${table.viewName}/${table.viewName}_add";
    }

    /**
    * <p>Discription:[跳转到${table.comment}查看页面]</p>
    * Created on ${date}
    * @return String ${table.comment}查看页面
    * @author:[${author}]
    */
    @RequestMapping("toView/{id}")
    @RequiresPermissions("<#if package.SubModuleName??>${package.SubModuleName}:</#if>${table.viewName}:toView")
    public String toView(@PathVariable Integer id,Model model){
        ${entity} ${table.viewName} = ${table.serviceName?uncap_first}.selectById(id);
        model.addAttribute("${table.viewName}", ${table.viewName});
        return "<#if package.SubModuleName??>${package.SubModuleName}/</#if>${table.viewName}/${table.viewName}_view";
    }

    /**
    * <p>Discription:[跳转到${table.comment}更新页面]</p>
    * Created on ${date}
    * @return String ${table.comment}更新页面
    * @author:[${author}]
    */
    @RequestMapping("toUpdate/{id}")
    @RequiresPermissions("<#if package.SubModuleName??>${package.SubModuleName}:</#if>${table.viewName}:toUpdate")
        public String toUpdate(@PathVariable Integer id,Model model){
        ${entity} ${table.viewName} = ${table.serviceName?uncap_first}.selectById(id);
        model.addAttribute("${table.viewName}", ${table.viewName});
        return "<#if package.SubModuleName??>${package.SubModuleName}/</#if>${table.viewName}/${table.viewName}_update";
    }


    /**
	 * <p>Discription:[${table.comment}列表页分页，可进行条件查询]</p>
	 * Created on ${date}
     * @param pager 存储分页信息，用于分页
     * @param ${table.viewName} 存储查询条件
     * @return AjaxInfo 成功：返回封装了Page<${entity}>的AjaxInfo
	 * @author:[${author}]
	 */      
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public AjaxInfo selectPage(Pager<${entity}> pager, ${entity} ${table.viewName}) {
        try {
            Pager<${entity}> ${table.viewName}Pager = ${table.serviceName?uncap_first}.selectPage(pager, ${table.viewName});
            return AjaxInfo.requestSuccess().setData(${table.viewName}Pager);
        } catch (Exception e) {
            logger.error("查询${table.comment}列表失败：", e);
        }
        return AjaxInfo.requestFail();
    }

    /**
	 * <p>Discription:[通过主键查询${table.comment}]</p>
	 * Created on ${date}
     * @param id ${table.comment}主键
     * @return AjaxInfo 成功：返回封装了${entity}的AjaxInfo
	 * @author:[${author}]
	 */     
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public AjaxInfo selectById(@PathVariable Long id) {
        try {
            ${entity} ${table.viewName} = ${table.serviceName?uncap_first}.selectById(id);
            return AjaxInfo.requestSuccess().setData(${table.viewName});
        } catch (Exception e) {
            logger.error("根据id查询${table.comment}失败：", e);
        }
        return AjaxInfo.requestFail();
    }

    /**
	 * <p>Discription:[保存${table.comment}]</p>
	 * Created on ${date}
	 * @param ${table.viewName} ${table.comment}
	 * @return AjaxInfo 保存结果
	 * @author:[${author}]
	 */      
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public AjaxInfo insert(${entity} ${table.viewName}) {
        try {
            boolean insertFlag = ${table.serviceName?uncap_first}.insert(${table.viewName});
            if (insertFlag) {
                return AjaxInfo.requestSuccess();
            }
        } catch (Exception e) {
            logger.error("保存${table.comment}失败：", e);
        }
        return AjaxInfo.requestFail();
    }

    /**
	 * <p>Discription:[修改${table.comment}]</p>
	 * Created on ${date}
	 * @param ${table.viewName} ${table.comment}
	 * @return AjaxInfo 修改结果
	 * @author:[${author}]
	 */      
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT,value="{id}")
    public AjaxInfo updateById(${entity} ${table.viewName},@PathVariable Long id) {
        try {
            boolean updateFlag = ${table.serviceName?uncap_first}.updateById(${table.viewName});
            if (updateFlag) {
                return AjaxInfo.requestSuccess();
            }
        } catch (Exception e) {
            logger.error("修改${table.comment}失败：", e);
        }
        return AjaxInfo.requestFail();
    }

    /**
	 * <p>Discription:[通过主键删除${table.comment}]</p>
	 * Created on ${date}
	 * @param id  ${table.comment}主键
	 * @return AjaxInfo 删除结果
	 * @author:[${author}]
	 */      
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    @RequiresPermissions("<#if package.SubModuleName??>${package.SubModuleName}:</#if>${table.viewName}:delete")
    public AjaxInfo deleteById(@PathVariable Long id) {
        try {
            ${entity} ${table.viewName} = new ${entity}();
            ${table.viewName}.setId(id);
            ${table.viewName}.setDelFlag(Constants.DELFLAG_TRUE);
            boolean delFlag = ${table.serviceName?uncap_first}.updateById(${table.viewName});
            if (delFlag) {
                return AjaxInfo.requestSuccess();
            }
        } catch (Exception e) {
            logger.error("删除${table.comment}失败：", e);
        }
        return AjaxInfo.requestFail();
    }

    /**
    * <p>Discription:[批量删除${table.comment}]</p>
    * Created on ${date}
    * @param ids  ${table.comment}id集合
    * @return AjaxInfo 删除结果
    * @author:[${author}]
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "deleteByIds")
    @RequiresPermissions("<#if package.SubModuleName??>${package.SubModuleName}:</#if>${table.viewName}:delete")
    public AjaxInfo deleteByIds(Long [] ids) {
        try {
            if (VerifyUtil.isNotEmpty(ids)) {
                ${entity} ${table.viewName};
                for (int i = 0; i < ids.length ; i++) {
                    ${table.viewName} = new ${entity}();
                    ${table.viewName}.setDelFlag(Constants.DELFLAG_TRUE);
                    ${table.viewName}.setId(ids[i]);
                    ${table.serviceName?uncap_first}.updateById(${table.viewName});
                }
                return AjaxInfo.requestSuccess();
             }
        } catch (Exception e) {
            logger.error("删除${table.comment}失败：", e);
        }
        return AjaxInfo.requestFail();
    }


}


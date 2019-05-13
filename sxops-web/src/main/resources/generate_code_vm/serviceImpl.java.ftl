package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>Description: [${table.comment}服务实现]</p>
 * Copyright (c) 2017 山西省壹加柒网络技术有限公司
 * Created on: ${date}
 * @author  <a href="mailto: ${cfg.email}">${author}</a>
 * @version 1.0 
 */
@Service("${table.serviceName?uncap_first}")
public class ${table.serviceImplName} extends ${superServiceImplClass}<${entity},${table.mapperName}> implements ${table.serviceName} {


}

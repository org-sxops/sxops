//package com.test.ep.ip.dao.util;
//
//
//import com.camelot.core.generator.CamelotAutoGenerator;
//import com.camelot.core.generator.InjectionConfig;
//import com.camelot.core.generator.config.*;
//import com.camelot.core.generator.config.rules.DbType;
//import com.camelot.core.generator.config.rules.NamingStrategy;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <p>
// * 代码生成器演示
// * </p>
// */
//
//public class Generator {
//
//    /**
//     * <p>Discription:[要生成的表]</p>
//     **/
//
//    private static String[] tebles = new String[]{"partner"};
//
//    /**
//     * <p>Discription:[在判断的时候不添加!=''的字段]</p>
//     **/
//
//    private static String[] notAppendApostropheTypes = new String[]{"Boolean", "Date"};
//
//    /**
//     * <p>Discription:[需要范围查询的时间字段]</p>
//     **/
//
//    private static String[] rangeDateFileds = new String[]{""};
//
//
//    /**
//     * <p>
//     * MySQL 生成演示
//     * </p>
//     */
//
//    public static void main(String[] args) {
//        generateCode();
//    }
//
//    /**
//     * <p>Discription: [代码生成] </p>
//     * Created on: 2017/5/17 14:08
//     *
//     * @param
//     * @author <a href="mailto: liruifeng@camelotchina.com">尹归晋</a>
//     */
//
//    private static void generateCode() {
//        CamelotAutoGenerator mpg = new CamelotAutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        gc.setOutputDir("D://template");
//        gc.setFileOverride(true);
//        gc.setActiveRecord(false);
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(false);// XML columList
//        gc.setTableAndFieldAnnotations(true);
//        gc.setCustomAnnotations(true);
//        gc.setAuthor("尹归晋");
//
//        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        gc.setServiceName("%sService");
//        //gc.setEntityName("%sBean");
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setDriverName("com.mysql.jdbc.Driver");
//        dsc.setUsername("didi_meeting");
//        dsc.setPassword("aoZ3B6bcBM");
//        dsc.setUrl("jdbc:mysql://10.95.121.181:3306/didi_meeting_dev");
//        mpg.setDataSource(dsc);
//
//        // 策略配置
//        // 字段名生成策略
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude(tebles);
//        strategy.setFieldNaming(NamingStrategy.underline_to_camel);
//        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
//        //strategy.setDbColumnUnderline(true);
//        mpg.setStrategy(strategy);
//        strategy.setSuperControllerClass("com.camelot.bcp.common.BaseController");
//        strategy.setSuperMapperClass("MyBaseMapper");
//        strategy.setSuperServiceImplClass("com.camelot.bcp.common.BaseServiceImpl");
//        strategy.setSuperServiceClass("com.camelot.bcp.common.BaseService");
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.test.ep.ip");
//        pc.setMapper("mapper");
//        pc.setController("controller");
//        pc.setEntity("dao.model");
//        //pc.setSubModuleName("product");
//        mpg.setPackageInfo(pc);
//
//        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("email", "liruifeng@camelotchina.com");
//                //配置生成范围查询
//                map.put("rangeDateFileds", rangeDateFileds);
//                map.put("notAppendApostropheTypes", notAppendApostropheTypes);
//                this.setMap(map);
//            }
//        };
//        mpg.setCfg(cfg);
//
//        //自定义模板
//        TemplateConfig tc = new TemplateConfig();
//        tc.setTemplatePath("/generate_code_vm");
//        tc.setEntity("entity.java.ftl");
//        tc.setMapper("mapper.java.ftl");
//        tc.setXml("mapper.xml.ftl");
//        tc.setService("service.java.ftl");
//        tc.setServiceImpl("serviceImpl.java.ftl");
//        tc.setController("controller.java.ftl");
//        mpg.setTemplate(tc);
//
//        // 执行生成
//        mpg.execute();
//        // 打印注入设置【可无】
//        //System.err.println(mpg.getCfg().getMap().get("time"));
//    }
//
//
//}

package com.haozai.mybatisplusgenerator;/**
 * @author SH
 * @date 2019/12/29 0:00
 * @version 1.0
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mybatis-plus-generator
 *
 * @description:
 *
 * @author: Mr.Sun
 *
 * @create: 2019-12-29 00:00
 **/

public class OracelGenerator { /*************************************ORACLE代码生成  Start *************************************/

// 全局配置
private final static String OUTPUT_XML_DIR = "/src/main/resources";// 生成xml文件的输出目录
    private final static String AUTHOR = "gblfy";// 开发人员
    // 数据源配置
    private final static String ORACLE_DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";// ORACLE数据库驱动
    private final static String DATABASE_IP = "xx.x.x.x";// 数据库ip
    private final static String ORACLE_DATABASE_NAME = "h";// 数据库名称
    private final static String ORACLE_DB_USERNAME = "root";// 数据库用户
    private final static String ORACLE_DB_PASSWORD = "root";// 数据库口令
    private final static String ORACLE_DB_PORT = "1521";// ORACLE数据库端口
    // 包和数据库表配置
    private final static String MODULE_NAME = "acc";// 父包模块名
    private final static String PARENT_PACKAGE = "com.gblfy.mybatisplus.generator";// 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
    private final static String[] TABLE_NAME_LIST = {"USER"};// 父包模块名

    // 自定义基类
    private final static String SuperEntity = PARENT_PACKAGE + ".common.BaseEntity";
    private final static String SuperController = PARENT_PACKAGE + ".common.BaseController";


    /**
     * 运行main方法即可
     * 1.全局配置
     * 2.数据源配置
     * 3.包配置策略
     * 4.策略配置
     * 5.整合配置
     */
    public static void main(String[] args) {

        //获取项目本地磁盘路径
        String projectPath = System.getProperty("user.dir");
/********************************** 全局配置**********************************/
        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //生成java文件的存放位置
        gc.setOutputDir(projectPath + "/src/main/java")
                .setAuthor(AUTHOR)//作者署名
                .setFileOverride(true)//是否文件覆盖
                .setIdType(IdType.ID_WORKER)//主键策略
                .setBaseResultMap(true)
                .setBaseColumnList(true)//生成sql片段
                //.setServiceName("%sService")//设置生成service接口是否首字母是I
                //是否打开输出目录
                .setOpen(false);
/********************************** 数据源配置**********************************/
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.ORACLE)//设置数据库类型
                .setUrl("jdbc:oracle:thin:@" + DATABASE_IP + ":" + ORACLE_DB_PORT + ":" + ORACLE_DATABASE_NAME)
                .setDriverName(ORACLE_DRIVER_NAME)
                .setUsername(ORACLE_DB_USERNAME)
                .setPassword(ORACLE_DB_PASSWORD);
/********************************** 包名配置**********************************/
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE)
                .setModuleName(MODULE_NAME)
                .setController("controller")
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper");

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称 单模块场景
                return projectPath + OUTPUT_XML_DIR + "/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
/********************************** 策略配置**********************************/

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true)//全局大小写命名
                .setColumnNaming(NamingStrategy.underline_to_camel)//数据库字段是否下划线转驼峰
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                .setSuperEntityClass(SuperEntity)
                .setEntityLombokModel(true)
                .setSuperControllerClass(SuperController)
                .setInclude(TABLE_NAME_LIST)
                .setSuperEntityColumns("id")
                .setControllerMappingHyphenStyle(true)
                .setTablePrefix(pc.getModuleName() + "_");

        // 配置整合 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //配置信息添加至 全局配置容器
        mpg.setGlobalConfig(gc)
                .setStrategy(strategy)
                .setDataSource(dsc)
                .setPackageInfo(pc)
                .setCfg(cfg)
                .setTemplate(new TemplateConfig().setXml(null))
                .setTemplateEngine(new FreemarkerTemplateEngine())// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
                .execute();//执行
    }
    /*************************************MYSQL代码生成  End *************************************/

}
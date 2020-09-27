package com.haozai.mybatisplusgenerator;
/**
 * @author SH
 * @date 2019/12/28 23:52
 * @version 1.0
 */

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @program: mybatis-plus-generator
 * @description:
 * @author: Mr.Sun
 * @create: 2019-12-28 23:52
 **/

public class MysqlGenerator {
    /**
     * 读取控制台内容
     * 启动main方法只会需要在控制台输入模块名以及数据库表名
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {
        /**
         * 代码生成器
         * 所有的配置都需要set进去
         * mybatisPlusGenerator
         */
        AutoGenerator mybatisPlusGenerator = new AutoGenerator();

        /**
         * 全局配置
         */
        GlobalConfig globalConfig = new GlobalConfig();
        /**
         * 生成文件的输出目录
         * 下面两行无需改动
         */
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");

        //Author设置作者
        globalConfig.setAuthor("HaoZai");

        //是否覆盖文件
        globalConfig.setFileOverride(true);

        //生成后打开文件
        globalConfig.setOpen(false);
        mybatisPlusGenerator.setGlobalConfig(globalConfig);

        /**
         * 数据源配置
         */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 数据库类型,默认MYSQL
        dataSourceConfig.setDbType(DbType.MYSQL);
        //自定义数据类型转换
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis-plus-generator?characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        //set进去代码生成器对象mybatisPlusGenerator中
        mybatisPlusGenerator.setDataSource(dataSourceConfig);

        /**
         * 包配置
         */
        PackageConfig pc = new PackageConfig();
        //这里的模块名需要在控制台输入的，即生成的代码在哪个包下
        pc.setModuleName(scanner("模块名"));
        //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent("com.haozai.mybatisplusgenerator");
        //set进去代码生成器对象mybatisPlusGenerator中
        mybatisPlusGenerator.setPackageInfo(pc);



        /**
         * 自定义配置
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        /**
         * 自定义输出配置
         * 模板
         */
        //如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        /**
         * 自定义输出配置
         */
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        //将配置set到我们上面自定义的配置中
        cfg.setFileOutConfigList(focList);
        //set进去代码生成器对象mybatisPlusGenerator中
        mybatisPlusGenerator.setCfg(cfg);


        /**
         * 配置模板
         */
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mybatisPlusGenerator.setTemplate(templateConfig);

        /**
         * 策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        //设置命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(scanner("表名,多个英文逗号分割").split(","));
        //实体是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);// 数据库字段下划线转驼峰命令策略
        strategy.setTablePrefix("tbl_") ;// 设置表前缀
        //设置自定义继承的Entity类全称，带包名
//        strategy.setSuperEntityClass("com.haozai.mybatisplusgenerator.BaseEntity");
        //设置自定义继承的Controller类全称，带包名
        //strategy.setSuperControllerClass("com.haozai.mybatisplusgenerator.BaseController");
        //设置自定义基础的Entity类，公共字段
        strategy.setSuperEntityColumns("id");
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表名前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mybatisPlusGenerator.setStrategy(strategy);
        mybatisPlusGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        mybatisPlusGenerator.execute();


    }
}

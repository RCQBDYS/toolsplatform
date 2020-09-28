package com.lyentech.toolsplatform.utils.Generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 672025
 * @date 16:56 2020/9/19
 * @description 代码生成器
 */

public class GeneratorCode {
    public static void main(String[] args){
        /*代码生成器，主要是根据数据库中的表格进行实体类、mapper、接口的自动生成。，目前了解的方式有两种
        一种是从控制台中输入表格名称进行生成，这是官方中提供的方法。
        另一种方法是在代码中进行表格名称明确的确定，这里使用的就是这种方法
        */

//        根据MySQL中的表格生成
        AutoGenerator autoGenerator = new AutoGenerator();
//        选择freemarker引擎，否则默认velocity
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
//        全局配置
        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setAuthor("672025");

//        获取项目路径
        String projectPath = System.getProperty("user.dir");
//        设置生成代码输出路径
        globalConfig.setOutputDir(projectPath+"/src/main/java");
//        设置开发者名称
        globalConfig.setAuthor("672025");
//        设置是否覆盖同名文件
        globalConfig.setFileOverride(true);
//        是否开启activeRecord特性
        globalConfig.setActiveRecord(true);
//        XML二级缓存
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(false);
//        完成之后不打开文件夹
        globalConfig.setOpen(false);
        autoGenerator.setGlobalConfig(globalConfig);

//        数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
//        自定义数据库表字段类型转换
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
//        数据库连接驱动设置
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
//        指定数据库名称
        dataSourceConfig.setSchemaName("toolsplatform");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("672025");
        dataSourceConfig.setUrl("jdbc:mysql://172.28.5.152/toolsplatform?characterEncoding=UTF-8&useSSL=true");
        autoGenerator.setDataSource(dataSourceConfig);

//        策略配置,通过指定可以选择性的生成那些表，数据库表的配置
        StrategyConfig strategyConfig = new StrategyConfig();
//        oracle中全局需要大写
//        strategyConfig.setCapitalMode(true);
//        表名生成的策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        指定数据库中需要生成的表,除了指定生成的表之外需要排除掉不想生成的表
//        否则将全部生成，并且覆盖掉自己所写的代码
        strategyConfig.setExclude("user");
        strategyConfig.setInclude("test");
//        生成Lombok形式的实体类
        strategyConfig.setEntityLombokModel(true);

//        包的配置
        PackageConfig packageConfig = new PackageConfig();
//        创建entity、mapper、service、serviceImpl、xml、controller包名，其中路径名称也是可以配置
        //packageConfig.setEntity()
        packageConfig.setParent("com.lyentech.toolsplatform");
//        包名称的配置，大多不设置选择默认的包名,设置模块名称
        packageConfig.setModuleName("Analysis");
        /*packageConfig.setEntity("Entity");
        packageConfig.setController("Controller");
        packageConfig.setMapper("Mapper");
        packageConfig.setService("Service");
        packageConfig.setServiceImpl("ServiceImpl");
        packageConfig.setXml("");*/

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！

                //根据自己的位置修改
                return projectPath + "/src/main/resources/com/lyentech/toolsplatform/"+packageConfig.getModuleName()+"/mapper/"+tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                //return projectPath + "/src/main/resources/com/lyentech/toolsplatform/asdf/mapper/"+tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        autoGenerator.setPackageInfo(packageConfig);
        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //此处设置为null，就不会再java下创建xml的文件夹了
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);


        //执行所有配置
        autoGenerator.execute();




    }
}

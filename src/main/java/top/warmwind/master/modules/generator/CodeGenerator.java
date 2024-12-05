package top.warmwind.master.modules.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import top.warmwind.master.core.basic.BaseController;
import top.warmwind.master.modules.generator.engine.MyBeetlTemplateEngine;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具
 *
 * @author EleAdmin
 * @since 2021-09-05 00:31:14
 */

/**
 * 代码生成工具
 *
 * @author warmwind
 * @since 2024-12-05 上午9:42
 */
public class CodeGenerator {
    // 输出位置
    private static final String OUTPUT_LOCATION = "E:";
    // 输出目录
    // private static final String OUTPUT_DIR = "/src/main/java";
    private static final String OUTPUT_DIR = "/";
    // 作者名称
    private static final String AUTHOR = "warmwind";
    // 是否在xml中添加二级缓存配置
    private static final boolean ENABLE_CACHE = false;
    // 数据库连接配置
    private static final String DB_URL = "jdbc:mysql://47.97.80.123:3306/wisdom-campus?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "warmwind666centos";
    // 包名
    private static final String PACKAGE_NAME = "top.warmwind.master";
    // 模块名
    private static final String MODULE_NAME = "log";
    // 需要生成的表
    private static final String[] TABLE_NAMES = new String[]{
            "sys_log"
    };
    // 需要去除的表前缀
    private static final String[] TABLE_PREFIX = new String[]{
            "sys_",
            "tb_"
    };
    // 需要填充的字段
    private static final String[] TABLE_FILL = new String[] {
            ""
    };

    // 不需要作为查询参数的字段
    private static final String[] PARAM_EXCLUDE_FIELDS = new String[]{
            "tenant_id",
            "create_by",
            "create_time",
            "update_time"
    };
    // 查询参数使用String的类型
    private static final String[] PARAM_TO_STRING_TYPE = new String[]{
            "Date"
    };
    // 查询参数使用EQ的类型
    private static final String[] PARAM_EQ_TYPE = new String[]{
            "Integer",
            "Boolean",
            "BigDecimal"
    };
    // 是否添加权限注解
    private static final boolean AUTH_ANNOTATION = true;
    // 是否添加日志注解
    private static final boolean LOG_ANNOTATION = true;
    // controller的mapping前缀
    private static final String CONTROLLER_MAPPING_PREFIX = "/api";
    // 模板所在位置
    private static final String TEMPLATES_DIR = "/src/mian/java/top/warmwind/master/modules/generator/templates";

    public static void main(String[] args) {

        Map<String, Object> injectionConfigMap = new HashMap<>();
        injectionConfigMap.put("packageName", PACKAGE_NAME);
        injectionConfigMap.put("paramExcludeFields", PARAM_EXCLUDE_FIELDS);
        injectionConfigMap.put("paramToStringType", PARAM_TO_STRING_TYPE);
        injectionConfigMap.put("paramEqType", PARAM_EQ_TYPE);
        injectionConfigMap.put("authAnnotation", AUTH_ANNOTATION);
        injectionConfigMap.put("logAnnotation", LOG_ANNOTATION);
        injectionConfigMap.put("controllerMappingPrefix", CONTROLLER_MAPPING_PREFIX);


        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> builder
                        .author(AUTHOR)
                        .outputDir(OUTPUT_LOCATION + OUTPUT_DIR)
                        .enableSpringdoc()
                        .dateType(DateType.TIME_PACK)
                )
                .packageConfig(builder -> builder
                        .parent(PACKAGE_NAME)
                        .moduleName(MODULE_NAME)
                )
                .strategyConfig(builder -> builder
                        .addInclude(TABLE_NAMES)
                        .addTablePrefix(TABLE_PREFIX)
                        .controllerBuilder().enableRestStyle().enableHyphenStyle().superClass(BaseController.class)
                        .entityBuilder().enableLombok().logicDeletePropertyName("isDeleted").naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel)
                        .build()
                )
                .templateConfig(builder -> builder
                        .entity(TEMPLATES_DIR + "/entity.java")
                        .mapper(TEMPLATES_DIR + "/mapper.java")
                        .xml(TEMPLATES_DIR + "/mapper.xml")
                        .service(TEMPLATES_DIR + "/service.java")
                        .serviceImpl(TEMPLATES_DIR + "/serviceImpl.java")
                        .controller(TEMPLATES_DIR + "/controller.java")
                )
                .injectionConfig(builder -> builder
                        .customMap(injectionConfigMap))
                .templateEngine(new MyBeetlTemplateEngine())
                .execute();

        // // 模板配置
        // TemplateConfig templateConfig = new TemplateConfig();
        // templateConfig.setController(TEMPLATES_DIR + "/controller.java");
        // templateConfig.setEntity(TEMPLATES_DIR + "/entity.java");
        // templateConfig.setMapper(TEMPLATES_DIR + "/mapper.java");
        // templateConfig.setXml(TEMPLATES_DIR + "/mapper.xml");
        // templateConfig.setService(TEMPLATES_DIR + "/service.java");
        // templateConfig.setServiceImpl(TEMPLATES_DIR + "/serviceImpl.java");
        // mpg.setTemplate(templateConfig);
        // mpg.setTemplateEngine(new BeetlTemplateEnginePlus());
        //
        // String templatePath = TEMPLATES_DIR + "/param.java.btl";
        // List<FileOutConfig> focList = new ArrayList<>();
        // focList.add(new FileOutConfig(templatePath) {
        //     @Override
        //     public String outputFile(TableInfo tableInfo) {
        //         return OUTPUT_LOCATION + OUTPUT_DIR + "/"
        //                 + PACKAGE_NAME.replace(".", "/")
        //                 + "/" + pc.getModuleName() + "/param/"
        //                 + tableInfo.getEntityName() + "Param" + StringPool.DOT_JAVA;
        //     }
        // });
        // /*新增页面模板*/
        // focList.add(new FileOutConfig(TEMPLATES_DIR + "/index.vue.btl") {
        //     @Override
        //     public String outputFile(TableInfo tableInfo) {
        //         return OUTPUT_LOCATION + OUTPUT_DIR + "/"
        //                 + PACKAGE_NAME.replace(".", "/")
        //                 + "/" + pc.getModuleName() + "/vue/" + tableInfo.getEntityName().toLowerCase()
        //                 +  "/index.vue";
        //     }
        // });
        // /*新增页面模板*/
        // focList.add(new FileOutConfig(TEMPLATES_DIR + "/form.vue.btl") {
        //     @Override
        //     public String outputFile(TableInfo tableInfo) {
        //         return OUTPUT_LOCATION + OUTPUT_DIR + "/"
        //                 + PACKAGE_NAME.replace(".", "/")
        //                 + "/" + pc.getModuleName() + "/vue/" + tableInfo.getEntityName().toLowerCase()
        //                 +  "/form.vue";
        //     }
        // });
        // cfg.setFileOutConfigList(focList);
        // mpg.setCfg(cfg);
        //
        // mpg.execute();
    }

}

package com.chengqiang.code.generate.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateBaseConfig {
    /**
     * 基础包名
     */
    protected String basePackageName;
    /**
     * controller 包名
     */
    protected String controllerPackageShortName = "controller";
    /**
     * service 包名
     */
    protected String bizPackageShortName = "biz";
    /**
     * dao 包名
     */
    protected String repositoryPackageShortName = "repository";
    /**
     * model 包名
     */
    protected String modelPackageShortName = "model";
    /**
     * xml mapper 位置
     */
    protected String xmlMapperLocation = "mappers";
    /**
     * dao名称后缀
     */
    protected String javaRepositorySuffix = "Mapper";
    /**
     * 模板后缀
     */
    private String templateFileSuffix = ".ftl";
    /**
     * dao模板文件名
     */
    protected String repositoryTemplateFileName = repositoryPackageShortName + templateFileSuffix;
    /**
     * service模板文件名
     */
    protected String bizTemplateFileName = bizPackageShortName + templateFileSuffix;
    /**
     * controller 模板文件名
     */
    protected String controllerTemplateFileName = controllerPackageShortName + templateFileSuffix;
    /**
     * model模板文件名
     */
    protected String modelTemplateFileName = modelPackageShortName + templateFileSuffix;

    /**
     * 用户模板路径 classpath目录下
     */
    protected String templateLocation = "generate-ftl";

    public GenerateBaseConfig(String basePackageName) {
        this.basePackageName = basePackageName;
    }

}

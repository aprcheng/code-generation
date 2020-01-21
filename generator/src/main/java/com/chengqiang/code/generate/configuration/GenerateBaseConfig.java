package com.chengqiang.code.generate.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateBaseConfig {
    private String basePackage;
    private String controllerPackageName = "controller";
    private String bizPackageName = "biz";
    private String repositoryPackageName = "mapper";
    private String modelPackageName = "model";
    private String xmlMapperLocation = "mappers";
    private String javaRepositorySuffix = "Mapper";

    public GenerateBaseConfig(String basePackage) {
        this.basePackage = basePackage;
    }

}

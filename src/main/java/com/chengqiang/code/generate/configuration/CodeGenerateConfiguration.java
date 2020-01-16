package com.chengqiang.code.generate.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ComponentScan(basePackages = {"com.chengqiang.code.generate.web"})
public class CodeGenerateConfiguration {

}

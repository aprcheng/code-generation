package com.chengqiang.code.generate.configuration;

import com.chengqiang.code.generate.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
@ConditionalOnWebApplication
@ComponentScan(basePackages = {"com.chengqiang.code.generate"})
@Slf4j
public class CodeGenerateConfiguration {

    @Autowired
    private ContextUtils contextUtils;

    @Configuration
    public static class WebConfig implements WebMvcConfigurer {
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/generate-ui/");
            log.info("初始化代码生成UI");
        }
    }

    @ConditionalOnMissingBean
    @Bean("defaultGenerateBaseConfig")
    public GenerateBaseConfig defaultGenerateBaseConfig() {
        return new GenerateBaseConfig(contextUtils.getSpringbootApplicationPackage());
    }

    @Bean
    public freemarker.template.Configuration freemarkerConfiguration() throws IOException {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        ClassPathResource classPathResource = new ClassPathResource("/generate-ftl");
        configuration.setDirectoryForTemplateLoading(classPathResource.getFile());
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }
}

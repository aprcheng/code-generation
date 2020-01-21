package com.chengqiang.code.generate.configuration;

import com.chengqiang.code.generate.mapper.ColumnMapper;
import com.chengqiang.code.generate.mapper.impl.MysqlColumnMapper;
import com.chengqiang.code.generate.sql.DataBaseSources;
import com.chengqiang.code.generate.sql.impl.MySqlSources;
import com.chengqiang.code.generate.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@ConditionalOnWebApplication
@ComponentScan(basePackages = {"com.chengqiang.code.generate"})
@Slf4j
public class CodeGenerateConfiguration {

    @Autowired
    private ContextUtils contextUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Configuration
    public static class WebConfig implements WebMvcConfigurer {
        @Override
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
    public freemarker.template.Configuration freemarkerConfiguration(GenerateBaseConfig generateBaseConfig) throws IOException {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        ClassPathResource classPathResource = new ClassPathResource("generate-ftl");
        if (StringUtils.hasText(generateBaseConfig.getTemplateLocation())) {
            classPathResource = new ClassPathResource(generateBaseConfig.getTemplateLocation());
        }
        configuration.setDirectoryForTemplateLoading(classPathResource.getFile());
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return configuration;
    }

    @ConditionalOnClass(value = {com.mysql.jdbc.Driver.class})
    @Bean
    public DataBaseSources mySqlSources() {
        return new MySqlSources(jdbcTemplate);
    }

    @ConditionalOnClass(value = {com.mysql.jdbc.Driver.class})
    @Bean
    public ColumnMapper columnMapper() {
        return new MysqlColumnMapper();
    }
}

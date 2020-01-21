package com.chengqiang.code.generate.utils;

import com.chengqiang.code.generate.annotation.EnableCodeGenerate;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ContextUtils {

    final private ApplicationContext applicationContext;

    public ContextUtils(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

//    /**
//     * 获取基础的包名
//     *
//     * @return String
//     */
//    public String getBasePackage() {
//        String springbootApplicationPackage = getSpringbootApplicationPackage();
//        EnableCodeGenerate enableCodeGenerate = getEnableCodeGenerateAnnotation();
//        String annotationPackage = enableCodeGenerate.basePackage();
//        return StringUtils.hasText(annotationPackage) ? annotationPackage : springbootApplicationPackage;
//    }

    /**
     * 获取第一个被SpringBootApplication注解的包
     *
     * @return String
     */
    public Package getSpringbootApplicationPackage() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(SpringBootApplication.class);
        String firstBeanName = map.keySet().iterator().next();
        Class<?> application = applicationContext.getBean(firstBeanName).getClass();
        return application.getPackage();
    }

    /**
     * 获取第一个EnableCodeGenerate注解
     *
     * @return EnableCodeGenerate
     */
    public EnableCodeGenerate getEnableCodeGenerateAnnotation() {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(EnableCodeGenerate.class);
        String firstBeanName = map.keySet().iterator().next();
        return applicationContext.findAnnotationOnBean(firstBeanName, EnableCodeGenerate.class);
    }

}

package com.chengqiang.code.generate.configuration;

import com.chengqiang.code.generate.test.BaseModel;
import com.chengqiang.code.generate.utils.ContextUtils;
import com.chengqiang.code.generate.utils.SpringContextUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DefaultModelConfig {

    public DefaultModelConfig() {
        this.basePackageName = SpringContextUtils.getBean(ContextUtils.class).getSpringbootApplicationPackage().getName() + ".model";
        enableLombok = true;
        interfaceClassSet.add(Serializable.class);
        interfaceClassSet.add(Cloneable.class);
        annotationClassSet.add(Getter.class);
        annotationClassSet.add(Setter.class);
        annotationClassSet.add(ToString.class);
        parentClass = BaseModel.class;
    }

    /**
     * 包名
     */
    private String basePackageName;

    /**
     * 需要继承的类
     */
    private Class parentClass;

    /**
     * 启用lombok注解
     */
    private boolean enableLombok;

    /**
     * 实现的接口
     */
    private Set<Class> interfaceClassSet = new HashSet<>();

    /**
     * 注解类
     */
    private Set<Class> annotationClassSet = new HashSet<>();
}

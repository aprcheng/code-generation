package com.chengqiang.code.generate.vo;

import com.chengqiang.code.generate.configuration.DefaultModelConfig;
import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.mapper.ColumnMapper;
import com.chengqiang.code.generate.utils.GenerateUtils;
import com.chengqiang.code.generate.utils.SpringContextUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class TableVo {

    private String packageName;
    private String className;
    private String tableComment;
    private List<ColumnVo> columns;
    private List<String> importSet;
    private String interfaceStr;
    private TreeSet<String> annotationSet;
    private String parentName;
    private static final String IMPORT = "import ";
    private static final String JAVA_END = "; ";

    @Getter
    @Setter
    @ToString
    public static class ColumnVo {
        private String columnName;
        private String columnComment;
        private String dataType;

        public static ColumnVo convert(ColumnEntity entity) {
            ColumnVo vo = new ColumnVo();
            vo.setColumnName(GenerateUtils.lineToHump(entity.getColumnName()));
            vo.setColumnComment(StringUtils.hasText(entity.getColumnComment()) ? entity.getColumnComment() : "");
            ColumnMapper columnMapper = SpringContextUtils.getBean(ColumnMapper.class);
            Class clazz = columnMapper.getMapper().get(entity.getDataType());
            Assert.notNull(clazz, entity.getDataType() + "类型未知");
            vo.setDataType(clazz.getSimpleName());
            return vo;
        }
    }


    public static TableVo convert(DefaultModelConfig config, List<ColumnEntity> entities) {
        Assert.notEmpty(entities, "字段集合不能为空");
        ColumnEntity firstEntity = entities.get(0);
        List<ColumnVo> columnVos = entities.stream().map(ColumnVo::convert).collect(Collectors.toList());
        ColumnMapper columnMapper = SpringContextUtils.getBean(ColumnMapper.class);
        List<String> importPackage = entities.stream()
                .filter(entity -> !columnMapper.getMapper().get(entity.getDataType()).getPackage().equals(String.class.getPackage()))
                .map(entity -> IMPORT + columnMapper.getMapper().get(entity.getDataType()).getName() + JAVA_END)
                .collect(Collectors.toCollection(LinkedList::new));
        if (config.getParentClass() != null) {
            importPackage.add(IMPORT + config.getParentClass().getName() + JAVA_END);
        }

        importPackage.addAll(config.getAnnotationClassSet().stream().map(c -> IMPORT + c.getName() + JAVA_END).collect(Collectors.toSet()));
        importPackage.addAll(config.getInterfaceClassSet().stream().map(c -> IMPORT + c.getName() + JAVA_END).collect(Collectors.toSet()));

        TreeSet<String> annotationSet = config.getAnnotationClassSet()
                .stream().map(c -> "@" + c.getSimpleName())
                .collect(Collectors.toCollection(TreeSet::new));

        TreeSet<String> interfaceSet = config.getInterfaceClassSet().stream()
                .map(Class::getSimpleName).collect(Collectors.toCollection(TreeSet::new));


        TableVo vo = new TableVo();
        vo.setTableComment(firstEntity.getTableComment());
        vo.setClassName(GenerateUtils.lineToHump(firstEntity.getTableName()));
        vo.setColumns(columnVos);
        vo.setPackageName(config.getBasePackageName());
        vo.setImportSet(optimizationImport(importPackage));
        vo.setAnnotationSet(annotationSet);
        vo.setInterfaceStr(String.join(", ", interfaceSet));

        if (config.getParentClass() != null) {
            vo.setParentName(config.getParentClass().getSimpleName());
        }
        return vo;
    }

    /**
     * 优化导包
     *
     * @param linkedList
     * @return
     */
    public static LinkedList<String> optimizationImport(List<String> linkedList) {
        linkedList = linkedList.stream()
                .filter(e -> !e.startsWith(IMPORT + String.class.getPackage().getName()))
                .collect(Collectors.toList());
        TreeSet<String> imports = new TreeSet<>(linkedList);
        LinkedList<String> javaSet = linkedList.stream().filter(p -> p.startsWith(IMPORT + "java.")).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<String> javaxSet = linkedList.stream().filter(p -> p.startsWith(IMPORT + "javax.")).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<String> importPackage = new LinkedList<>();
        if ((javaSet.size() + javaxSet.size()) > 0) {
            imports.removeAll(javaSet);
            imports.removeAll(javaxSet);
            importPackage.addAll(imports);
            importPackage.add("");
            importPackage.addAll(javaxSet);
            importPackage.addAll(javaSet);
        }
        return importPackage;
    }

}

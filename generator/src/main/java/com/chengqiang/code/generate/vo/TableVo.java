package com.chengqiang.code.generate.vo;

import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.mapper.ColumnMapper;
import com.chengqiang.code.generate.mapper.impl.MysqlColumnMapper;
import com.chengqiang.code.generate.utils.GenerateUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class TableVo {

    private String packageName;
    private String className;
    private String methodName;
    private String tableComment;
    private List<ColumnVo> columnVos;
    private Set<String> needImports = new LinkedHashSet<>();

    @Getter
    @Setter
    @ToString
    public static class ColumnVo {
        private String propertyName;
        private String columnComment;
        private String dataType;

        public static ColumnVo convert(ColumnEntity entity) {
            ColumnVo vo = new ColumnVo();
            vo.setPropertyName(GenerateUtils.firstLowerCase(GenerateUtils.lineToHump(entity.getColumnName())));
            vo.setColumnComment(StringUtils.hasText(entity.getColumnComment()) ? entity.getColumnComment() : "");
            ColumnMapper columnMapper = new MysqlColumnMapper();
            Class clazz = columnMapper.getMapper().get(entity.getDataType());
            Assert.notNull(clazz, entity.getDataType() + "类型未知");
            vo.setDataType(clazz.getSimpleName());
            return vo;
        }
    }


    public static TableVo convert(String packageName, List<ColumnEntity> entities) {
        Assert.notEmpty(entities, "字段集合不能为空");
        TableVo vo = new TableVo();
        ColumnEntity firstEntity = entities.get(0);
        vo.setTableComment(firstEntity.getTableComment());
        vo.setClassName(GenerateUtils.lineToHump(firstEntity.getTableName()));
        vo.setMethodName(GenerateUtils.firstLowerCase(vo.getClassName()));
        List<ColumnVo> columnVos = entities.stream().map(ColumnVo::convert).collect(Collectors.toList());
        vo.setColumnVos(columnVos);
        vo.setPackageName(packageName);
        vo.setNeedImports(entities.stream()
                .filter(entity -> !new MysqlColumnMapper().getMapper().get(entity.getDataType()).getName().startsWith("java.lang"))
                .map(entity -> new MysqlColumnMapper().getMapper().get(entity.getDataType()).getName())
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        return vo;
    }


}

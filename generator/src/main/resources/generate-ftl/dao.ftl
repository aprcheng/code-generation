package ${context.config.basePackage}.${context.config.modelPackageName};

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class ${context.table.tableName} {

<#list context.columnList as column>
    <#if column.columnComment??>
        /**
        * ${column.columnComment}
        */
    <#else>
    </#if>
    private String ${column.columnName}

</#list>
}


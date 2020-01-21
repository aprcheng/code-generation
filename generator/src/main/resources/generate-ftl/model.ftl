package ${table.packageName}
import java.io.Serializable;
<#if importDdate?? && importDdate>
import java.util.Date;
</#if>

/**
*
*  Created by vv on '${.now?string('yyyy-MM-dd HH:mm:ss')}'.
*/
public class ${context.table.tableName} implements Serializable {
    private static final long serialVersionUID = 42L;

<#if context.columnList?exists && context.columnList?size gt 0>
<#list context.columnList as column >
    /**
    * ${column.columnComment}
    */
    private ${column.dataType} ${column.columnName};

</#list>
</#if>

<#if context.columnList?exists && context.columnList?size gt 0>
<#list context.columnList as column>
    public ${column.dataType} get${column.columnName?cap_first}() {
        return ${column.columnName};
    }

    public void set${column.columnName?cap_first}(${column.dataType} ${column.columnName}) {
        this.${column.columnName} = ${column.columnName};
    }

</#list>
</#if>
}

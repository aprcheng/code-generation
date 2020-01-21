package ${table.packageName};

<#list table.importSet as import>
    ${import}
</#list>

<#list table.annotationSet as import>
    ${import}
</#list>
public class ${table.className} <#if table.parentName??>extends ${table.parentName}</#if> <#if table.interfaceStr??>implements ${table.interfaceStr}</#if>{

<#list table.columns as column>
    <#if column.columnComment??>
        /**
        * ${column.columnComment}
        */
    </#if>
    private ${column.dataType} ${column.columnName?uncap_first};

</#list>
}

package com.chengqiang.code.generate.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ColumnEntity {
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 排序
     */
    private Integer ordinalPosition;
    /**
     * 是否为空
     */
    private String isNullable;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字符最大长度
     */
    private Integer characterMaximumLength;
    /**
     * 数字最大位数
     */
    private Integer numericPrecision;
    /**
     * 小数位数
     */
    private Integer numericScale;
    /**
     * 时间位数
     */
    private Integer datetimePrecision;
    /**
     * 键类型
     */
    private String columnKey;
    /**
     * 注释
     */
    private String columnComment;

}

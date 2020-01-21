package com.chengqiang.code.generate.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

import static com.chengqiang.code.generate.constant.SysConstant.DATE_FORMAT;

@Getter
@Setter
@ToString
public class TableEntity {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 创建时间
     */
    @JSONField(format = DATE_FORMAT)
    private Date createTime;
    /**
     * 修改时间
     */
    @JSONField(format = DATE_FORMAT)
    private Date updateTime;
    /**
     * 注释
     */
    private String tableComment;
}

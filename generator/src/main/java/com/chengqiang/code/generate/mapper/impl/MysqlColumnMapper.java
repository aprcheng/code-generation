package com.chengqiang.code.generate.mapper.impl;

import com.chengqiang.code.generate.mapper.ColumnMapper;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * mysql 字段映射
 */
@Getter
@Setter
public class MysqlColumnMapper implements ColumnMapper {

    protected Map<String, Class> mapper = new HashMap();

    public MysqlColumnMapper() {
        mapper.put("bigint", Long.class);
        mapper.put("varchar", String.class);
        mapper.put("decimal", BigDecimal.class);
        mapper.put("timestamp", Date.class);
        mapper.put("datetime", Date.class);
        mapper.put("bit", Boolean.class);
        mapper.put("int", Integer.class);
    }

    public Map<String, Class> getMapper() {
        return mapper;
    }
}

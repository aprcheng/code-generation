package com.chengqiang.code.generate.sql;

import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;

import java.util.List;

public interface DataBaseSources {

    List<TableEntity> queryTable();

    List<ColumnEntity> queryColumnByTableName(String tableName);
}

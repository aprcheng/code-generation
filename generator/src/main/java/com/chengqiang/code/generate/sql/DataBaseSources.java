package com.chengqiang.code.generate.sql;

import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;

import java.util.List;

public interface DataBaseSources {

    List<TableEntity> queryTableList();

    TableEntity queryTable(String tableName);

    List<ColumnEntity> queryColumnByTableName(String tableName);
}

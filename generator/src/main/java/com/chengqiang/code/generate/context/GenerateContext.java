package com.chengqiang.code.generate.context;

import com.chengqiang.code.generate.configuration.GenerateBaseConfig;
import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateContext {

    private GenerateBaseConfig config;
    private TableEntity table;
    private List<ColumnEntity> columnList;

    public GenerateContext(GenerateBaseConfig config, TableEntity table, List<ColumnEntity> columnList) {
        this.config = config;
        this.table = table;
        this.columnList = columnList;
    }
}

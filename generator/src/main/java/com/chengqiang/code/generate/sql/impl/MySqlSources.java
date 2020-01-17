package com.chengqiang.code.generate.sql.impl;

import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;
import com.chengqiang.code.generate.sql.DataBaseSources;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MySqlSources implements DataBaseSources {

    private final JdbcTemplate jdbcTemplate;

    public MySqlSources(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TableEntity> queryTableList() {
        String sql =
                "SELECT TABLE_NAME, CREATE_TIME, UPDATE_TIME, TABLE_COMMENT " +
                        "FROM information_schema.`TABLES` " +
                        "WHERE TABLE_SCHEMA = (SELECT DATABASE())";
        BeanPropertyRowMapper<TableEntity> rowMapper = new BeanPropertyRowMapper<>(TableEntity.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public TableEntity queryTable(String tableName) {
        String sql =
                "SELECT TABLE_NAME, CREATE_TIME, UPDATE_TIME, TABLE_COMMENT " +
                        "FROM information_schema.`TABLES` " +
                        "WHERE TABLE_SCHEMA = (SELECT DATABASE()) AND TABLE_NAME = ?";
        BeanPropertyRowMapper<TableEntity> rowMapper = new BeanPropertyRowMapper<>(TableEntity.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, tableName);
    }

    @Override
    public List<ColumnEntity> queryColumnByTableName(String tableName) {
        String sql =
                "SELECT COLUMN_NAME, ORDINAL_POSITION, IS_NULLABLE, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, " +
                        "NUMERIC_PRECISION, NUMERIC_SCALE, DATETIME_PRECISION, COLUMN_KEY, COLUMN_COMMENT " +
                        "FROM information_schema.`COLUMNS` " +
                        "WHERE TABLE_SCHEMA = (SELECT DATABASE()) AND TABLE_NAME = ? " +
                        "ORDER BY ORDINAL_POSITION";
        BeanPropertyRowMapper<ColumnEntity> rowMapper = new BeanPropertyRowMapper<>(ColumnEntity.class);
        return jdbcTemplate.query(sql, rowMapper, tableName);
    }
}

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
                "SELECT  DISTINCT a.TABLE_SCHEMA, " +
                        "b.TABLE_COMMENT, " +
                        "a.TABLE_NAME, " +
                        "a.COLUMN_NAME, " +
                        "a.ORDINAL_POSITION, " +
                        "a.IS_NULLABLE, " +
                        "a.DATA_TYPE, " +
                        "a.CHARACTER_MAXIMUM_LENGTH, " +
                        "a.NUMERIC_PRECISION, " +
                        "a.NUMERIC_SCALE, " +
                        "a.DATETIME_PRECISION, " +
                        "a.COLUMN_KEY, " +
                        "a.COLUMN_COMMENT " +
                        "FROM " +
                        "information_schema.`COLUMNS` a " +
                        "JOIN information_schema.`TABLES` b ON a.TABLE_NAME = b.TABLE_NAME " +
                        "WHERE  a.TABLE_SCHEMA = (SELECT DATABASE()) " +
                        "AND a.TABLE_NAME = ? " +
                        "ORDER BY a.ORDINAL_POSITION";
        BeanPropertyRowMapper<ColumnEntity> rowMapper = new BeanPropertyRowMapper<>(ColumnEntity.class);
        return jdbcTemplate.query(sql, rowMapper, tableName);
    }
}

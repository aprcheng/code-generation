package com.chengqiang.code.generate.web;

import com.alibaba.fastjson.JSON;
import com.chengqiang.code.generate.entity.DataBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/easy-codding")
@Slf4j
public class TestController {

    private final JdbcTemplate jdbcTemplate;

    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test")
    public String test() {
        String sql = "SELECT TABLE_NAME, CREATE_TIME, UPDATE_TIME, TABLE_COMMENT " +
                "FROM information_schema.`TABLES` " +
                "WHERE TABLE_SCHEMA = (SELECT DATABASE())";
        BeanPropertyRowMapper<DataBase> rowMapper = new BeanPropertyRowMapper<>(DataBase.class);
        List<DataBase> list = jdbcTemplate.query(sql, rowMapper);
        return JSON.toJSONString(list);
    }
}

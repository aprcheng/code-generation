package com.chengqiang.code.generate.web;

import com.alibaba.fastjson.JSON;
import com.chengqiang.code.generate.annotation.EnableCodeGenerate;
import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;
import com.chengqiang.code.generate.sql.impl.MySqlSources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/easy-codding")
@Slf4j
public class TestController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    ApplicationContext applicationContext;

    public TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/table")
    public String test() {
        List<TableEntity> list = new MySqlSources(jdbcTemplate).queryTable();
        return JSON.toJSONString(list);
    }

    @GetMapping("/column/{tableName}")
    public String query(@PathVariable String tableName) {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(EnableCodeGenerate.class);
        if (!CollectionUtils.isEmpty(map)) {
            EnableCodeGenerate enableCodeGenerate = applicationContext.findAnnotationOnBean(map.keySet().iterator().next(), EnableCodeGenerate.class);

        }

        List<ColumnEntity> list = new MySqlSources(jdbcTemplate).queryColumnByTableName(tableName);
        return JSON.toJSONString(list);
    }
}

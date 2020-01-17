package com.chengqiang.code.generate.web;

import com.chengqiang.code.generate.configuration.GenerateBaseConfig;
import com.chengqiang.code.generate.context.GenerateContext;
import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;
import com.chengqiang.code.generate.sql.impl.MySqlSources;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/easy-codding")
@Slf4j
public class GenerateController {

    private final JdbcTemplate jdbcTemplate;
    private final GenerateBaseConfig generateBaseConfig;
    private final Configuration configuration;

    public GenerateController(JdbcTemplate jdbcTemplate, GenerateBaseConfig generateBaseConfig, Configuration configuration) {
        this.jdbcTemplate = jdbcTemplate;
        this.generateBaseConfig = generateBaseConfig;
        this.configuration = configuration;
    }

    @GetMapping("/tableList")
    public List<TableEntity> tableList() {


        return new MySqlSources(jdbcTemplate).queryTableList();
    }

    @GetMapping("/table/{tableName}")
    public TableEntity table(@PathVariable String tableName) {
        return new MySqlSources(jdbcTemplate).queryTable(tableName);
    }

    @GetMapping("/column/{tableName}")
    public List<ColumnEntity> query(@PathVariable String tableName) {

        return new MySqlSources(jdbcTemplate).queryColumnByTableName(tableName);
    }

    @GetMapping("/generate/{tableName}")
    public List<ColumnEntity> generate(@PathVariable String tableName) {
        return new MySqlSources(jdbcTemplate).queryColumnByTableName(tableName);
    }

    @GetMapping("/preview/dao/{tableName}")
    public String previewDao(@PathVariable String tableName) throws Exception {
        TableEntity tableEntity = new MySqlSources(jdbcTemplate).queryTable(tableName);
        List<ColumnEntity> columnEntities = new MySqlSources(jdbcTemplate).queryColumnByTableName(tableName);
        GenerateContext context = new GenerateContext(generateBaseConfig, tableEntity, columnEntities);
        Map<String, Object> map = new HashMap<>();
        map.put("context", context);
        Template template = configuration.getTemplate("dao.ftl");
        StringWriter stringWriter = new StringWriter();
        template.process(map, stringWriter);
        return stringWriter.toString();
    }
}

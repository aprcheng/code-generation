package com.chengqiang.code.generate.web;

import com.chengqiang.code.generate.configuration.GenerateBaseConfig;
import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;
import com.chengqiang.code.generate.sql.impl.MySqlSources;
import com.chengqiang.code.generate.utils.FreemarkerTool;
import com.chengqiang.code.generate.vo.TableVo;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    private FreemarkerTool freemarkerTool;

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

    @GetMapping("/preview/{tableName}")
    public Map<String, String> previewDao(@PathVariable String tableName) {
        List<ColumnEntity> columnEntities = new MySqlSources(jdbcTemplate).queryColumnByTableName(tableName);
        TableVo tableVo = TableVo.convert(generateBaseConfig.getBasePackage().getName(), columnEntities);
        log.info("tableVo={}", tableVo);
        return new HashMap<>();
    }
}

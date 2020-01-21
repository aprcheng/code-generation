package com.chengqiang.code.generate.web;

import com.chengqiang.code.generate.configuration.GenerateBaseConfig;
import com.chengqiang.code.generate.context.GenerateContext;
import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;
import com.chengqiang.code.generate.sql.impl.MySqlSources;
import com.chengqiang.code.generate.utils.FreemarkerTool;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public Map<String, String> previewDao(@PathVariable String tableName) throws Exception {
        TableEntity tableEntity = new MySqlSources(jdbcTemplate).queryTable(tableName);
        List<ColumnEntity> columnEntities = new MySqlSources(jdbcTemplate).queryColumnByTableName(tableName);
        GenerateContext context = new GenerateContext(generateBaseConfig, tableEntity, columnEntities);

        // code genarete
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("context", context);
        // result
        Map<String, String> result = new HashMap<String, String>();
        result.put("dao",freemarkerTool.processString("dao.ftl",params));
        result.put("service",freemarkerTool.processString("service.ftl",params));
        result.put("service_impl",freemarkerTool.processString("service_impl.ftl",params));
        result.put("controller",freemarkerTool.processString("controller.ftl",params));
        result.put("model",freemarkerTool.processString("model.ftl",params));
        return result;
    }
}

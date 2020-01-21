package com.chengqiang.code.generate.web;

import com.chengqiang.code.generate.configuration.DefaultModelConfig;
import com.chengqiang.code.generate.entity.ColumnEntity;
import com.chengqiang.code.generate.entity.TableEntity;
import com.chengqiang.code.generate.sql.DataBaseSources;
import com.chengqiang.code.generate.utils.FreemarkerTool;
import com.chengqiang.code.generate.vo.TableVo;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/easy-codding")
@Slf4j
public class GenerateController {

    private final DataBaseSources dataBaseSources;
    private final FreemarkerTool freemarkerTool;

    public GenerateController(DataBaseSources dataBaseSources, FreemarkerTool freemarkerTool) {
        this.dataBaseSources = dataBaseSources;
        this.freemarkerTool = freemarkerTool;
    }

    @GetMapping("/tableList")
    public List<TableEntity> tableList() {
        return dataBaseSources.queryTableList();
    }

    @GetMapping("/table/{tableName}")
    public TableEntity table(@PathVariable String tableName) {
        return dataBaseSources.queryTable(tableName);
    }

    @GetMapping("/column/{tableName}")
    public List<ColumnEntity> query(@PathVariable String tableName) {
        return dataBaseSources.queryColumnByTableName(tableName);
    }

    @GetMapping("/generate/{tableName}")
    public List<ColumnEntity> generate(@PathVariable String tableName) {
        return dataBaseSources.queryColumnByTableName(tableName);
    }

    @GetMapping("/preview/{tableName}")
    public Map<String, String> previewDao(@PathVariable String tableName) throws IOException, TemplateException {
        List<ColumnEntity> columnEntities = dataBaseSources.queryColumnByTableName(tableName);
        TableVo tableVo = TableVo.convert(new DefaultModelConfig(), columnEntities);
        // code genarete
        Map<String, Object> params = new HashMap<>();
        params.put("table", tableVo);
        Map<String, String> result = new HashMap<>();
//        result.put("dao", freemarkerTool.processString("dao.ftl", params));
//        result.put("service", freemarkerTool.processString("service.ftl", params));
//        result.put("service_impl", freemarkerTool.processString("service_impl.ftl", params));
//        result.put("controller", freemarkerTool.processString("controller.ftl", params));
        result.put("dao", freemarkerTool.processString("test.ftl", params));
        return result;
    }
}

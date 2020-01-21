import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
*
* Created by vv on '${.now?string('yyyy-MM-dd HH:mm:ss')}'.
*/
@Controller
public class ConfController {

    @Resource
    private ${context.table.tableName}Service ${context.table.tableName?uncap_first}Service;

    /**
    * 新增
    */
    @RequestMapping("/insert")
    @ResponseBody
    public ReturnT<String> insert(${context.table.tableName} ${context.table.tableName?uncap_first}){
        return ${context.table.tableName?uncap_first}Service.insert(${context.table.tableName?uncap_first});
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    @ResponseBody
    public ReturnT<String> delete(int id){
        return ${context.table.tableName?uncap_first}Service.delete(id);
    }

    /**
    * 更新
    */
    @RequestMapping("/update")
    @ResponseBody
    public ReturnT<String> update(${context.table.tableName} ${context.table.tableName?uncap_first}){
        return ${context.table.tableName?uncap_first}Service.update(${context.table.tableName?uncap_first});
    }

    /**
    * Load查询
    */
    @RequestMapping("/load")
    @ResponseBody
    public ReturnT<String> load(int id){
        return ${context.table.tableName?uncap_first}Service.load(id);
    }

    /**
    * 分页查询
    */
    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return ${context.table.tableName?uncap_first}Service.pageList(offset, pagesize);
    }

}

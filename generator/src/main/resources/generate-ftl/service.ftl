import java.util.Map;

/**
* ${context.table.tableComment}
*
* Created by vv on '${.now?string('yyyy-MM-dd HH:mm:ss')}'.
*/
public interface ${context.table.tableName}Service {

    /**
    * 新增
    */
    public ReturnT<String> insert(${context.table.tableName} ${context.table.tableName?uncap_first});

    /**
    * 删除
    */
    public ReturnT<String> delete(int id);

    /**
    * 更新
    */
    public ReturnT<String> update(${context.table.tableName} ${context.table.tableName?uncap_first});

    /**
    * Load查询
    */
    public ${context.table.tableName} load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}

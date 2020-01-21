import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
*
* Created by vv on '${.now?string('yyyy-MM-dd HH:mm:ss')}'.
*/
@Service
public class ${context.table.tableName}ServiceImpl implements ${context.table.tableName}Service {

	@Resource
	private ${context.table.tableName}Dao ${context.table.tableName?uncap_first}Dao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(${context.table.tableName} ${context.table.tableName?uncap_first}) {

		// valid
		if (${context.table.tableName?uncap_first} == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		${context.table.tableName?uncap_first}Dao.insert(${context.table.tableName?uncap_first});
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = ${context.table.tableName?uncap_first}Dao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(${context.table.tableName} ${context.table.tableName?uncap_first}) {
		int ret = ${context.table.tableName?uncap_first}Dao.update(${context.table.tableName?uncap_first});
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public ${context.table.tableName} load(int id) {
		return ${context.table.tableName?uncap_first}Dao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<${context.table.tableName}> pageList = ${context.table.tableName?uncap_first}Dao.pageList(offset, pagesize);
		int totalCount = ${context.table.tableName?uncap_first}Dao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		maps.put("pageList", pageList);
		maps.put("totalCount", totalCount);

		return result;
	}

}

package com.itheima.erp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.itheima.erp.action.util.WebUtil;
import com.itheima.erp.biz.IBaseBiz;
public class BaseAction<T> {
	@Autowired
	private IBaseBiz baseBiz;
	private T t1;// 查询使用
	private T t2;// 查询使用
	private Object param;
	private int page;// 页码
	private int rows;// 每页记录数
	private T t;// 编辑数据
	private Long id;// 所有类型id

	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz = baseBiz;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public T getT1() {
		return t1;
	}

	public void setT1(T t1) {
		this.t1 = t1;
	}

	public T getT2() {
		return t2;
	}

	public void setT2(T t2) {
		this.t2 = t2;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	/**
	 * 条件查询
	 */
	public void list() {
		List<T> list = baseBiz.getList(t1, t2, param);
		String jsonString = JSON.toJSONString(list);
		WebUtil.write(jsonString);
	}

	/**
	 * 分页查询
	 */

	public void listByPage() {
		Long count = baseBiz.getCount(t1, t2, param);
		int firstResult = (page - 1) * rows;
		List<T> list = baseBiz.getListBypage(t1, t2, count, firstResult, rows);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("total", count);
		resultMap.put("rows", list);
		
		WebUtil.write(resultMap);
	}


	/**
	 * 添加部门
	 */
	public void add() {

		try {
			baseBiz.add(t);
			WebUtil.ajaxReturn(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			WebUtil.ajaxReturn(false, "发生异常");
		}
	}
	
	/**
	 * 删除部门
	 */
	public void delete() {

		try {
			baseBiz.delete(id);
			WebUtil.write(ajaxReturn(true, "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
			WebUtil.write(ajaxReturn(false, "发生异常"));
		}
	}
	
	/**
	 * 回显数据
	 */
	public void get(){
		T tShow=(T) baseBiz.get(id);
		String  jsonString = JSON.toJSONString(tShow);
		WebUtil.write(mapJson(jsonString,"t."));
	}
	
	/**
	 * 修改部门
	 */
	public void update() {

		try {
			baseBiz.update(t);
			WebUtil.ajaxReturn(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			WebUtil.ajaxReturn(false, "发生异常");
		}
	}

	/**
	 * 返回ajax结构返回体
	 * 
	 * @param b
	 * @param string
	 * @return
	 */

	private String ajaxReturn(boolean success, String message) {
		Map map = new HashMap();
		map.put("success", success);
		map.put("message", message);
		String jsonString = JSON.toJSONString(map);
		return jsonString;
	}

	
	/**
	 * 给json对象的key值加前缀
	 * @param jsonString
	 * @param prefix
	 * @return
	 */
	private Map<String,Object> mapJson(String jsonString, String prefix){
		//{"name":"管理员组","tele":"000000","uuid":1} 不符合要求
		Map<String, Object> jsonMap = JSON.parseObject(jsonString);
		//{"t.name":"管理员组","t.tele":"000000","t.uuid":1}
		Map<String,Object> newMap = new HashMap<String,Object>();
		for (String key : jsonMap.keySet()) {
			newMap.put(prefix + key, jsonMap.get(key));
		}
		return newMap;
	}

}

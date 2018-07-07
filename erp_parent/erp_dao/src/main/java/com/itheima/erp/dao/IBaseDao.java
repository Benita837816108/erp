package com.itheima.erp.dao;

import java.util.List;

public interface IBaseDao<T> {
	/**
	 * 获取所有部门信息
	 */
	List<T> getList(T t1,T t2,Object param);

	/**
	 * 统计个数
	 */
	Long getCount(T t1,T t2,Object param);
	
	
	/**
	 * 分页查询
	 * @param t1
	 * @param t2
	 * @param param
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	List<T> getListBypage(T t1,T t2,Object param,int firstResult, int maxResults);
	
	/**
	 * 添加部门
	 */
	void add(T t);
	
	/**
	 * 数据回显
	 */
	T get(Long uuid);
	/**
	 * 修改部门
	 * @param t
	 */
	void update(T t);

	/**
	 * 删除
	 * @param t
	 */
	void delete(Long uuid);

}

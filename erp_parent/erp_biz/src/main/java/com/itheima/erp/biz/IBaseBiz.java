package com.itheima.erp.biz;

import java.util.List;

public interface IBaseBiz<T> {
	/**
	 *查询列表
	 * 
	 * @return
	 */
	List<T> getList(T t1, T t2, Object param);

	/**
	 * 分页
	 * 
	 * @return
	 */
	List<T> getListBypage(T t1, T t2, Object param, int firstResult, int maxResults);

	/**
	 * 总记录数
	 * 
	 * @return
	 */
	Long getCount(T t1, T t2, Object param);

	/**
	 * 添加
	 * 
	 * @return
	 */
	void add(T t);

	/**
	 * 删除
	 * 
	 * @return
	 */
	void delete(Long id);

	/**
	 * 数据回显
	 * 
	 * @return
	 */
	T get(Long id);

	/**
	 * 修改部门
	 * 
	 * @param t
	 */
	void update(T t);
}

package com.itheima.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.erp.dao.IBaseDao;
@SuppressWarnings("unchecked")
public abstract class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	@Resource(name="sessionFactory")
	public void setSf(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	
	private Class<T> entityClass;
	/**
	 * 添加无参构造方法 实现entityClass的具体类型
	 */
	public BaseDao(){
		//通过子类获取父类
		Type baseDaoClass = getClass().getGenericSuperclass();
		//转成参数化的类型
		ParameterizedType pType=(ParameterizedType) baseDaoClass;
		//类型参数类型的数组
		Type[] types = pType.getActualTypeArguments();
		//得到了泛型里T的类型
		Type targetType = types[0];
		//转成class类型
		entityClass=(Class<T>) targetType;
	}

	
	/**
	 * 查询条件列表
	 */
	
	@Override
	public List<T> getList(T t1,T t2,Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		return (List<T>) getHibernateTemplate().findByCriteria(dc);
	}
	
	/**
	 * 总记录数
	 */
	@Override
	public Long getCount(T t1,T t2,Object param) {
		
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
			dc.setProjection(Projections.rowCount());
			List<?> list = getHibernateTemplate().findByCriteria(dc);
			return (Long) list.get(0);
	
	}
	
	/**
	 * t记录分页
	 */
	
	@Override
	public List<T> getListBypage(T t1, T t2, Object param,int firstResult, int maxResults) {
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		return (List<T>) getHibernateTemplate().findByCriteria(dc,firstResult,maxResults);
	
	}

	
	
	/**
	 * 构建查询方法
	 */
	public abstract DetachedCriteria getDetachedCriteria(T t1,T t2,Object param) ;
	
	/**
	 * 添加部门
	 */
	@Override
	public void add(T t) {
		getHibernateTemplate().save(t);
	}
	
	
	/**
	 * 数据回显
	 */
	@Override
	public T get(Long uuid) {
		return getHibernateTemplate().get(entityClass,uuid);
	}
	
	/**
	 * 修改部门
	 */
	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}
	
	/**
	 * 删除部门
	 */
	@Override
	public void delete(Long uuid) {
		getHibernateTemplate().delete(this.getHibernateTemplate().get(entityClass, uuid));
	}

}

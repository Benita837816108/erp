package com.itheima.erp.dao.impl;



import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.itheima.erp.dao.IDepDao;
import com.itheima.erp.entity.Dep;
 

@Repository("depDao")
public class DepDao extends BaseDao<Dep>  implements IDepDao {

	@Override
	public DetachedCriteria getDetachedCriteria(Dep t1, Dep t2, Object param) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(null != t1) {
			if(!StringUtils.isEmpty(t1.getName())) {
				// 部门名称有值
				// 属性名 
				dc.add(Restrictions.like("name", t1.getName(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(t1.getTele())) {
				dc.add(Restrictions.like("tele", t1.getTele(), MatchMode.ANYWHERE));
			}
		}
		return dc;
		
	}
	
}

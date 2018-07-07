package com.itheima.erp.biz.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.erp.biz.IDepBiz;
import com.itheima.erp.dao.IDepDao;
import com.itheima.erp.entity.Dep;
/**
 * 业务逻辑类
 * @author Administrator
 *
 */
@Service("depBiz")
public class DepBiz extends BaseBiz<Dep> implements IDepBiz {
	
	@Autowired
	private IDepDao depDao;
	
	@Resource(name="depDao")
	public void setDepDao(IDepDao depDao) {
		super.setBaseDao(depDao);
	}

	 

}

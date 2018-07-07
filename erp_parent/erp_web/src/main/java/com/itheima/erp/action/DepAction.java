package com.itheima.erp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.itheima.erp.action.util.WebUtil;
import com.itheima.erp.biz.IDepBiz;
import com.itheima.erp.entity.Dep;


/**
 * 部门Action
 * 
 * @author Administrator
 *
 */

@Controller("depAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
@Action("dep")
public class DepAction extends BaseAction<Dep>{

	@Autowired
	private IDepBiz depBiz;
	@Resource(name="depBiz")
	public void setDepBiz(IDepBiz depBiz) {
		super.setBaseBiz(this.depBiz);
	}

	
}

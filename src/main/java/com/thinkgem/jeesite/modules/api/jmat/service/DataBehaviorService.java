/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;

/**
 * 数据行为Service
 * @author ljc
 * @version 2018-04-09
 */
@Service
@Transactional(readOnly = true)
public class DataBehaviorService extends CrudService<DataBehaviorDao, DataBehavior> {

	public DataBehavior get(String id) {
		return super.get(id);
	}
	
	public List<DataBehavior> findList(DataBehavior dataBehavior) {
		return super.findList(dataBehavior);
	}
	
	public Page<DataBehavior> findPage(Page<DataBehavior> page, DataBehavior dataBehavior) {
		return super.findPage(page, dataBehavior);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(DataBehavior dataBehavior) {
		super.delete(dataBehavior);
	}
	
}
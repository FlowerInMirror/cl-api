/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataClass;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataClassDao;

/**
 * 数据行为分类Service
 * @author ljc
 * @version 2018-04-09
 */
@Service
@Transactional(readOnly = true)
public class DataClassService extends CrudService<DataClassDao, DataClass> {

	public DataClass get(String id) {
		return super.get(id);
	}
	
	public List<DataClass> findList(DataClass dataClass) {
		return super.findList(dataClass);
	}
	
	public Page<DataClass> findPage(Page<DataClass> page, DataClass dataClass) {
		return super.findPage(page, dataClass);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(DataClass dataClass) {
		super.delete(dataClass);
	}
	
}
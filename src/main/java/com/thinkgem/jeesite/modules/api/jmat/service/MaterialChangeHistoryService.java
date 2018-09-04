/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialChangeHistory;
import com.thinkgem.jeesite.modules.api.jmat.dao.MaterialChangeHistoryDao;

/**
 * 子库更新审核表Service
 * @author ljc
 * @version 2018-05-09
 */
@Service
@Transactional(readOnly = true)
public class MaterialChangeHistoryService extends CrudService<MaterialChangeHistoryDao, MaterialChangeHistory> {

	public MaterialChangeHistory get(String id) {
		return super.get(id);
	}
	
	public List<MaterialChangeHistory> findList(MaterialChangeHistory materialChangeHistory) {
		return super.findList(materialChangeHistory);
	}
	
	public Page<MaterialChangeHistory> findPage(Page<MaterialChangeHistory> page, MaterialChangeHistory materialChangeHistory) {
		return super.findPage(page, materialChangeHistory);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(MaterialChangeHistory materialChangeHistory) {
		super.delete(materialChangeHistory);
	}
	
}
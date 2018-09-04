/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.TabkeRelation;
import com.thinkgem.jeesite.modules.api.jmat.dao.TabkeRelationDao;

/**
 * 材料表关联性汇总表Service
 * @author ljc
 * @version 2018-06-16
 */
@Service
@Transactional(readOnly = true)
public class TabkeRelationService extends CrudService<TabkeRelationDao, TabkeRelation> {

	public TabkeRelation get(String id) {
		return super.get(id);
	}
	
	public List<TabkeRelation> findList(TabkeRelation tabkeRelation) {
		return super.findList(tabkeRelation);
	}
	
	public Page<TabkeRelation> findPage(Page<TabkeRelation> page, TabkeRelation tabkeRelation) {
		return super.findPage(page, tabkeRelation);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(TabkeRelation tabkeRelation) {
		super.delete(tabkeRelation);
	}
	
}
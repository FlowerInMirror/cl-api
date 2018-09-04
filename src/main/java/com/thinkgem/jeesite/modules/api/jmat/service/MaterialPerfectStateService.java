/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialPerfectState;
import com.thinkgem.jeesite.modules.api.jmat.dao.MaterialPerfectStateDao;

/**
 * 材料子库完善状态(用于保存地区信息)Service
 * @author ljc
 * @version 2018-03-29
 */
@Service
@Transactional(readOnly = true)
public class MaterialPerfectStateService extends CrudService<MaterialPerfectStateDao, MaterialPerfectState> {

	public MaterialPerfectState get(String id) {
		return super.get(id);
	}
	
	public List<MaterialPerfectState> findList(MaterialPerfectState materialPerfectState) {
		return super.findList(materialPerfectState);
	}
	
	public Page<MaterialPerfectState> findPage(Page<MaterialPerfectState> page, MaterialPerfectState materialPerfectState) {
		return super.findPage(page, materialPerfectState);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(MaterialPerfectState materialPerfectState) {
		super.delete(materialPerfectState);
	}
	
}
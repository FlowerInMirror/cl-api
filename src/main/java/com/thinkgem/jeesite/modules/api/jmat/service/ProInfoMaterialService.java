/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.ProInfoMaterial;
import com.thinkgem.jeesite.modules.api.jmat.dao.ProInfoMaterialDao;

/**
 * 项目材料表Service
 * @author ljc
 * @version 2018-07-26
 */
@Service
@Transactional(readOnly = true)
public class ProInfoMaterialService extends CrudService<ProInfoMaterialDao, ProInfoMaterial> {

	public ProInfoMaterial get(String id) {
		return super.get(id);
	}
	
	public List<ProInfoMaterial> findList(ProInfoMaterial proInfoMaterial) {
		return super.findList(proInfoMaterial);
	}
	
	public Page<ProInfoMaterial> findPage(Page<ProInfoMaterial> page, ProInfoMaterial proInfoMaterial) {
		return super.findPage(page, proInfoMaterial);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(ProInfoMaterial proInfoMaterial) {
		super.delete(proInfoMaterial);
	}
	
}
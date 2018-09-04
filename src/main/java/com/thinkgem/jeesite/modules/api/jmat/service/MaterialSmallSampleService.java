/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialSmallSample;
import com.thinkgem.jeesite.modules.api.jmat.dao.MaterialSmallSampleDao;

/**
 * 材料小样表Service
 * @author ljc
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class MaterialSmallSampleService extends CrudService<MaterialSmallSampleDao, MaterialSmallSample> {

	public MaterialSmallSample get(String id) {
		return super.get(id);
	}
	
	public List<MaterialSmallSample> findList(MaterialSmallSample materialSmallSample) {
		return super.findList(materialSmallSample);
	}
	
	public Page<MaterialSmallSample> findPage(Page<MaterialSmallSample> page, MaterialSmallSample materialSmallSample) {
		return super.findPage(page, materialSmallSample);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(MaterialSmallSample materialSmallSample) {
		super.delete(materialSmallSample);
	}
	
}
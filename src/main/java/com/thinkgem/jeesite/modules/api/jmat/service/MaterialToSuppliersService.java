/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialToSuppliers;
import com.thinkgem.jeesite.modules.api.jmat.dao.MaterialToSuppliersDao;

/**
 * 材料商材料关联表Service
 * @author ljc
 * @version 2018-05-09
 */
@Service
@Transactional(readOnly = true)
public class MaterialToSuppliersService extends CrudService<MaterialToSuppliersDao, MaterialToSuppliers> {

	public MaterialToSuppliers get(String id) {
		return super.get(id);
	}
	
	public List<MaterialToSuppliers> findList(MaterialToSuppliers materialToSuppliers) {
		return super.findList(materialToSuppliers);
	}
	
	public Page<MaterialToSuppliers> findPage(Page<MaterialToSuppliers> page, MaterialToSuppliers materialToSuppliers) {
		return super.findPage(page, materialToSuppliers);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(MaterialToSuppliers materialToSuppliers) {
		super.delete(materialToSuppliers);
	}

	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.TypeInfo;
import com.thinkgem.jeesite.modules.api.jmat.dao.TypeInfoDao;

/**
 * 分类表Service
 * @author ljc
 * @version 2018-04-12
 */
@Service
@Transactional(readOnly = true)
public class TypeInfoService extends CrudService<TypeInfoDao, TypeInfo> {

	public TypeInfo get(String id) {
		return super.get(id);
	}
	
	public List<TypeInfo> findList(TypeInfo typeInfo) {
		return super.findList(typeInfo);
	}
	
	public Page<TypeInfo> findPage(Page<TypeInfo> page, TypeInfo typeInfo) {
		return super.findPage(page, typeInfo);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(TypeInfo typeInfo) {
		super.delete(typeInfo);
	}
	
}
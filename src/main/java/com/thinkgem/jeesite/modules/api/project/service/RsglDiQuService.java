/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.project.entity.RsglDiQu;
import com.thinkgem.jeesite.modules.api.project.dao.RsglDiQuDao;

/**
 * 地区(人事管理)Service
 * @author ljc
 * @version 2018-03-29
 */
@Service
@Transactional(readOnly = true)
public class RsglDiQuService extends CrudService<RsglDiQuDao, RsglDiQu> {

	public RsglDiQu get(String id) {
		return super.get(id);
	}
	
	public List<RsglDiQu> findList(RsglDiQu rsglDiQu) {
		return super.findList(rsglDiQu);
	}
	
	public Page<RsglDiQu> findPage(Page<RsglDiQu> page, RsglDiQu rsglDiQu) {
		return super.findPage(page, rsglDiQu);
	}
	
	@Transactional(readOnly = false)
	public void save(RsglDiQu rsglDiQu) {
		super.save(rsglDiQu);
	}
	
	@Transactional(readOnly = false)
	public void delete(RsglDiQu rsglDiQu) {
		super.delete(rsglDiQu);
	}
	
}
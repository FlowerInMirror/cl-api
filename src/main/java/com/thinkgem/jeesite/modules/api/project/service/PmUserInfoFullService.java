/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.project.dao.PmUserInfoFullDao;
import com.thinkgem.jeesite.modules.api.project.entity.PmUserInfoFull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目人事(新)Service
 * @author ljc
 * @version 2018-06-21
 */
@Service
@Transactional(readOnly = true)
public class PmUserInfoFullService extends CrudService<PmUserInfoFullDao, PmUserInfoFull> {

	public PmUserInfoFull get(String id) {
		return super.get(id);
	}
	
	public List<PmUserInfoFull> findList(PmUserInfoFull pmUserInfoFull) {
		return super.findList(pmUserInfoFull);
	}
	
	public Page<PmUserInfoFull> findPage(Page<PmUserInfoFull> page, PmUserInfoFull pmUserInfoFull) {
		return super.findPage(page, pmUserInfoFull);
	}
	
	@Transactional(readOnly = false)
	public void save(PmUserInfoFull pmUserInfoFull) {
		super.save(pmUserInfoFull);
	}
	
	@Transactional(readOnly = false)
	public void delete(PmUserInfoFull pmUserInfoFull) {
		super.delete(pmUserInfoFull);
	}
	
}
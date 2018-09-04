/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.Infocheckandaccept;
import com.thinkgem.jeesite.modules.api.jmat.dao.InfocheckandacceptDao;

/**
 * 信息审核验收表Service
 * @author ljc
 * @version 2018-06-16
 */
@Service
@Transactional(readOnly = true)
public class InfocheckandacceptService extends CrudService<InfocheckandacceptDao, Infocheckandaccept> {

	public Infocheckandaccept get(String id) {
		return super.get(id);
	}
	
	public List<Infocheckandaccept> findList(Infocheckandaccept infocheckandaccept) {
		return super.findList(infocheckandaccept);
	}
	
	public Page<Infocheckandaccept> findPage(Page<Infocheckandaccept> page, Infocheckandaccept infocheckandaccept) {
		return super.findPage(page, infocheckandaccept);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(Infocheckandaccept infocheckandaccept) {
		super.delete(infocheckandaccept);
	}
	
}
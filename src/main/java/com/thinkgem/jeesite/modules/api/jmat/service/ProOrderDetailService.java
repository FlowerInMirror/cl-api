/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.ProOrderDetail;
import com.thinkgem.jeesite.modules.api.jmat.dao.ProOrderDetailDao;

/**
 * 配送单明细Service
 * @author ljc
 * @version 2018-04-14
 */
@Service
@Transactional(readOnly = true)
public class ProOrderDetailService extends CrudService<ProOrderDetailDao, ProOrderDetail> {

	public ProOrderDetail get(String id) {
		return super.get(id);
	}
	
	public List<ProOrderDetail> findList(ProOrderDetail proOrderDetail) {
		return super.findList(proOrderDetail);
	}
	
	public Page<ProOrderDetail> findPage(Page<ProOrderDetail> page, ProOrderDetail proOrderDetail) {
		return super.findPage(page, proOrderDetail);
	}
	

	@Transactional(readOnly = false)
	public void delete(ProOrderDetail proOrderDetail) {
		super.delete(proOrderDetail);
	}
	
}
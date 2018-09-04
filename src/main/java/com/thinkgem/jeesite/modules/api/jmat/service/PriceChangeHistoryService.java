/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.PriceChangeHistory;
import com.thinkgem.jeesite.modules.api.jmat.dao.PriceChangeHistoryDao;

/**
 * 价格变更记录Service
 * @author ljc
 * @version 2018-05-09
 */
@Service
@Transactional(readOnly = true)
public class PriceChangeHistoryService extends CrudService<PriceChangeHistoryDao, PriceChangeHistory> {

	public PriceChangeHistory get(String id) {
		return super.get(id);
	}
	
	public List<PriceChangeHistory> findList(PriceChangeHistory priceChangeHistory) {
		return super.findList(priceChangeHistory);
	}
	
	public Page<PriceChangeHistory> findPage(Page<PriceChangeHistory> page, PriceChangeHistory priceChangeHistory) {
		return super.findPage(page, priceChangeHistory);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(PriceChangeHistory priceChangeHistory) {
		super.delete(priceChangeHistory);
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.MatRegionalIncrease;
import com.thinkgem.jeesite.modules.api.jmat.dao.MatRegionalIncreaseDao;

/**
 * 地区材料涨幅关联表(上月材料地区排行数据)Service
 * @author ljc
 * @version 2018-04-04
 */
@Service
@Transactional(readOnly = true)
public class MatRegionalIncreaseService extends CrudService<MatRegionalIncreaseDao, MatRegionalIncrease> {

	public MatRegionalIncrease get(String id) {
		return super.get(id);
	}
	
	public List<MatRegionalIncrease> findList(MatRegionalIncrease matRegionalIncrease) {
		return super.findList(matRegionalIncrease);
	}
	
	public Page<MatRegionalIncrease> findPage(Page<MatRegionalIncrease> page, MatRegionalIncrease matRegionalIncrease) {
		return super.findPage(page, matRegionalIncrease);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(MatRegionalIncrease matRegionalIncrease) {
		super.delete(matRegionalIncrease);
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeCityInfo;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreeCityInfoDao;

/**
 * 材料地区信息Service
 * @author ljc
 * @version 2018-03-16
 */
@Service
@Transactional(readOnly = true)
public class TreeCityInfoService extends CrudService<TreeCityInfoDao, TreeCityInfo> {

	public TreeCityInfo get(String id) {
		return super.get(id);
	}
	
	public List<TreeCityInfo> findList(TreeCityInfo treeCityInfo) {
		return super.findList(treeCityInfo);
	}
	
	public Page<TreeCityInfo> findPage(Page<TreeCityInfo> page, TreeCityInfo treeCityInfo) {
		return super.findPage(page, treeCityInfo);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(TreeCityInfo treeCityInfo) {
		super.delete(treeCityInfo);
	}
	
}
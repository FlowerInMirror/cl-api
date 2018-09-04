/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.CityToMatCity;
import com.thinkgem.jeesite.modules.api.jmat.dao.CityToMatCityDao;

/**
 * 地区关联Service
 * @author ljc
 * @version 2018-03-29
 */
@Service
@Transactional(readOnly = true)
public class CityToMatCityService extends CrudService<CityToMatCityDao, CityToMatCity> {

	public CityToMatCity get(String id) {
		return super.get(id);
	}
	
	public List<CityToMatCity> findList(CityToMatCity cityToMatCity) {
		return super.findList(cityToMatCity);
	}
	
	public Page<CityToMatCity> findPage(Page<CityToMatCity> page, CityToMatCity cityToMatCity) {
		return super.findPage(page, cityToMatCity);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(CityToMatCity cityToMatCity) {
		super.delete(cityToMatCity);
	}
	
}
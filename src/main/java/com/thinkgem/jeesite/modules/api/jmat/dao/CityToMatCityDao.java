/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.CityToMatCity;
import org.apache.ibatis.annotations.Param;

/**
 * 地区关联DAO接口
 * @author ljc
 * @version 2018-03-29
 */
@MyBatisDao
public interface CityToMatCityDao extends CrudDao<CityToMatCity> {
	
	/**
	 * 获取全部地区关联数据
	 * @author      ljc
	 * @version     2018-03-29
	 * @Description 地区(人事地区)过滤数据
	 * @return      全部地区关联数据
	 */
	public List<CityToMatCity> getAllCityToMatCity();

	// 查找材料系统城市ID通过入参城市ID @author ljc @createTime 2018-8-29 15:08:59
    Integer findMatCityIDByCityID(@Param("cityID") Integer cityID);
}
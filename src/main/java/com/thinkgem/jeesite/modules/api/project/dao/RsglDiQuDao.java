/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.project.entity.RsglDiQu;

/**
 * 地区(人事管理)DAO接口
 * @author ljc
 * @version 2018-03-29
 */
@MyBatisDao
public interface RsglDiQuDao extends CrudDao<RsglDiQu> {
	
	/**
	 * 获取全部地区(人事管理)数据
	 * @author      ljc
	 * @version     2018-03-29
	 * @return      全部地区(人事管理)数据
	 */
	public List<RsglDiQu> getAllRsglDiQu();
	
}
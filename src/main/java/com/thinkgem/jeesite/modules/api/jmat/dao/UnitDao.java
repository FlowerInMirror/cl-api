/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.Unit;

/**
 * 材料单位DAO接口
 * @author ljc
 * @version 2018-03-23
 */
@MyBatisDao
public interface UnitDao extends CrudDao<Unit> {

	/**
	 * 获取全部材料单位
	 * @author ljc
	 * @version 2018-03-23
	 * @return 全部材料单位
	 */
	public List<Unit>  getAllUnit();
	
}
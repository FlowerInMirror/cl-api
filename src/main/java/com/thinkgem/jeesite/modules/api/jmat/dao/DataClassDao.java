/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataClass;

/**
 * 数据行为分类DAO接口
 * @author ljc
 * @version 2018-04-09
 */
@MyBatisDao
public interface DataClassDao extends CrudDao<DataClass> {
	
}
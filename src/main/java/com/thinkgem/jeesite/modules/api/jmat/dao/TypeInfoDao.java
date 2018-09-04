/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TypeInfo;

/**
 * 分类表DAO接口
 * @author ljc
 * @version 2018-04-12
 */
@MyBatisDao
public interface TypeInfoDao extends CrudDao<TypeInfo> {
	
}
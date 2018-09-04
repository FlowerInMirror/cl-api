/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.project.entity.PmUserInfoFull;

/**
 * 项目人事(新)DAO接口
 * @author ljc
 * @version 2018-06-21
 */
@MyBatisDao
public interface PmUserInfoFullDao extends CrudDao<PmUserInfoFull> {
	
}
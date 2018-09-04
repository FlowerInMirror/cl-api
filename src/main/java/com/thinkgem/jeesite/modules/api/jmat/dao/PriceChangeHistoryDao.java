/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.PriceChangeHistory;

/**
 * 价格变更记录DAO接口
 * @author ljc
 * @version 2018-05-09
 */
@MyBatisDao
public interface PriceChangeHistoryDao extends CrudDao<PriceChangeHistory> {
	
}
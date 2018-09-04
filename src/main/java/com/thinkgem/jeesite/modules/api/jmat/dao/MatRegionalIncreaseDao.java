/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.MatRegionalIncrease;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.CostOverallRanking;

/**
 * 地区材料涨幅关联表(上月材料地区排行数据)DAO接口
 * @author ljc
 * @version 2018-04-04
 */
@MyBatisDao
public interface MatRegionalIncreaseDao extends CrudDao<MatRegionalIncrease> {
	
	/**
	 * 获取子库默认页成本三段(成本整体排名数据)
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-04-04 15:16:01
	 * @return     List<CostOverallRanking>     响应数据:排名/地区/材料总价/近期涨幅/近期排名
	 */
	public List<CostOverallRanking> getCastThreeSection();
	
}
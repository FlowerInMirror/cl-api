/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.ProOrderDetail;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.CostMaterialApplicationAmount;

/**
 * 配送单明细DAO接口
 * @author ljc
 * @version 2018-04-14
 */
@MyBatisDao
public interface ProOrderDetailDao extends CrudDao<ProOrderDetail> {
	
	/**
	 * 查找材料使用量集合,通过城市ID与数据量.
	 * @author                      ljc
	 * @version                     1.0
	 * @createTime                  2018-4-14 18:58:12
	 * @param       cityID          城市ID
	 * @param       topNum          数据量
	 * @return      List<CostMaterialApplicationAmount> 材料使用量模型List
	 */
	public List<CostMaterialApplicationAmount> findMatApplicationAmountsByCityIDAndTopNum(@Param("cityID") Integer cityID, @Param("topNum") Integer topNum);

}
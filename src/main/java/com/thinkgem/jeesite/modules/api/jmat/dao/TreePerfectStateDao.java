/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreePerfectState;

/**
 * 材料母库完善状态(保存档次信息)DAO接口
 * @author ljc
 * @version 2018-03-29
 */
@MyBatisDao
public interface TreePerfectStateDao extends CrudDao<TreePerfectState> {

	/**
	 * 保存 地区新增标准 - 材料母库完善状态表(保存规格档次总计)
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018年4月2日 14:47:42
	 * @param      treeFourID          四级ID
	 * @param      levelCount          档次总计
	 * @descride   插入字段:ID,四级ID,基本信息(档次总计)
	 */
	public void saveCityNewlyIncreased(@Param("treeFourID") String treeFourID, @Param("levelCount") Integer levelCount);

	// 查找 材料母库完善状态 实体,通过四级科目ID.
    /**
     * @author ljc
     * @param  treeFourID 四级科目ID
     * @return 材料母库完善状态 实体
     * @createTime 2018-6-27 14:10:04
     */
    TreePerfectState findTreePerfectStateByTreeFourID(@Param("treeFourID")String treeFourID);
}
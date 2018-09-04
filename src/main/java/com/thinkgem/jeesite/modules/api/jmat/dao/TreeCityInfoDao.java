/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeCityInfo;

/**
 * 材料地区信息DAO接口
 * @author ljc
 * @version 2018-03-16
 */
@MyBatisDao
public interface TreeCityInfoDao extends CrudDao<TreeCityInfo> {
	
	/**
	 * 通过城市ID与四级科目树ID,查找材料地区信息.
	 * @author  ljc
	 * @version 2018-03-17 
	 * @param   city         城市ID
	 * @param   treeFourID   四级科目ID
	 * @return  材料地区信息
	 */
	public TreeCityInfo findTreeCityInfoByCityAndTreeID(@Param("city") Integer city, @Param("treeFourID") String treeFourID);
	
	/**
	 * 平台统计(子库起始页状态,材料统计),通过城市ID获得.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-9 19:26:41
	 * @param      cityID           
	 * @return     Map<String, Object>  platform 平台数量,perfect 完善数量,Imperfect 未完善数量.
	 */ 	
	public Map<String, Object> findPlatformStatisticsByCityID(@Param("cityID") Integer cityID);
	
	/**
	 * 查找置顶总计,通过城市ID.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-10 17:16:52 
	 * @param      cityID              城市ID
	 * @return     Integer             置顶总计
	 */
	public Integer findTopCountByCityID(@Param("cityID") Integer cityID);
	
}
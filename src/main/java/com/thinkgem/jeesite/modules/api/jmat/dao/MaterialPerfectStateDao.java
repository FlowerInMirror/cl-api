/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialPerfectState;

/**
 * 材料子库完善状态(用于保存地区信息)DAO接口
 * @author ljc
 * @version 2018-03-29
 */
@MyBatisDao
public interface MaterialPerfectStateDao extends CrudDao<MaterialPerfectState> {
	
	/**
	 * 保存 地区新增标准 - 材料子库完善状态(用于保存地区信息)
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018年4月2日 15:31:02
	 * @param      materialPerfectID    材料子库完善状态(用于保存地区信息)
	 * @param      treeFourID			四级科目ID
	 * @param      cityID               城市ID
	 * @param      levelCount           档次总计 
	 * @describe   插入字段:ID,四级规格ID(一),地区 (多)
	 */
	public void saveCityNewlyIncreased(@Param("treeFourID") String treeFourID, @Param("cityID") Integer cityID, @Param("levelCount") Integer levelCount);
	
	/**
	 * 种类统计(子库起始页状态,材料统计),通过城市ID获得.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-9 17:12:27
	 * @param      cityID   
	 * @return     Map<String, Object>  class 种类数量,perfect 完善数量,Imperfect 未完善数量.
	 */
	public Map<String, Object> findClassStatisticsByCityID(@Param("cityID") Integer cityID);

    /**
     * 获取子库起始页一段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-4-23 11:31:50
     * @return     List<Map<String,Object>>     地区/种类
     */
    public  List<Map<String,Object>> getIndexOneSection();

    /**
     * 材料子库完善状态表: 根据四级ID/城市ID下架
     * @author ljc
     * @param  treeFourID 四级科目ID
     * @param  cityID     城市ID
     * @return 受影响行数
     * @createTime 2018-7-26 20:41:02
     */
    int deleteMatPerfectStateByTreeFourIDAndCityID(@Param("treeFourID")String treeFourID,@Param("cityID") Integer cityID);
}
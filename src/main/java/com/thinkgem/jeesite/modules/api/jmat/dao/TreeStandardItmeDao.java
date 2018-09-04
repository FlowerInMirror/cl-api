/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeStandardItme;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformStandardInfo;

/**
 * 材料标准信息DAO接口
 * 
 * @author ljc
 * @version 2018-03-19
 */
@MyBatisDao
public interface TreeStandardItmeDao extends CrudDao<TreeStandardItme> {
	
	
	/**
	 * 通过类型与四级科目ID,查找材料标准结果集(没有传入检索类型,则当前规格下的所有类型标准).
     * 类型：1瑞祥标准，2质量标准，4小样标准，8包装标准（可位运算|）
	 * @author   ljc
	 * @version  2018-03-19 
	 * @param    type        检索类型
	 * @param    treeFourID  四级科目ID
	 * @return   材料标准结果集.
	 */
	public List<TreeStandardItme> findTreeStandardItmesByTypeAndTreeFourID(@Param("type") Integer type, @Param("treeFourID") String treeFourID);
	
	/**
	 * 移除材料标准信息.
	 * @author   ljc
	 * @version  2018-03-26
	 * @param    standardID      标准ID
	 * @return   InformationBody 响应信息
	 */
	public int deleteTreeStandardItmeByStandardID(String standardID);
	
	/**
	 * 新增or更新or删除,材料标准信息.
	 * @author   ljc
	 * @version  2018-03-27 
	 * @param    platformStandardInfo  平台材料标准信息(新增/更新)POJO
	 * @return   InformationBody       响应信息
	 */
	public int saveOrUpdate(PlatformStandardInfo platformStandardInfo);

	// 查找档次质量标准结果集,通过四级科目ID和档次标识(统一质量标准).
    /**
     * @author ljc
     * @param  treeFourID              四级科目ID
     * @param  levelFlag               档次标识
     * @return List<TreeStandardItme>  统一质量标准结果集
     * @createTime 2018-6-16 19:47:44
     */
    List<TreeStandardItme> findLevelQualityStandardItemsByTreeFourIDAndLevelFlag(@Param("treeFourID") String treeFourID,@Param("levelFlag") Integer levelFlag);

    // 查找档次质量标准结果集,通过四级科目ID和档次标识(档次质量标准).
    /**
     * @author ljc
     * @param  treeFourID              四级科目ID
     * @param  levelFlag               档次标识
     * @return List<TreeStandardItme>  档次质量标准
     * @createTime 2018-6-16 19:50:00
     */
    List<TreeStandardItme> findLevelStasByTreeFourIDAndLevelFlag(@Param("treeFourID")String treeFourID,@Param("levelFlag")Integer levelFlag);
}
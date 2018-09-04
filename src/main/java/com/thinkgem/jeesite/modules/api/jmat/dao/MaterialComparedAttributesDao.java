/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.ContrastItemWithPhoto;
import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialComparedAttributes;

/**
 * 属性对比（对比图）DAO接口
 * @author ljc
 * @version 2018-03-19
 */
@MyBatisDao
public interface MaterialComparedAttributesDao extends CrudDao<MaterialComparedAttributes> {
	
	/**
	 * 通过四级科目ID,查找材料对比属性结果集
	 * @author  ljc
	 * @version 2018-03-19
	 * @param   treeFourID    司机科目ID
	 * @return  材料对比属性结果集
	 */
	public List<MaterialComparedAttributes> findEntitysByTreeFourID(@Param("treeFourID") String treeFourID);
	
	/**
	 * 属性对比(对比图)对比名称,新增或更新.
	 * @author   ljc
	 * @version  2018-03-27 
	 * @param    map        mcaID 属性对比ID,mcaName 对比名称,mcaType  类型（1对比，2材料约定）,treeFourID  四级科目ID
	 * @return   受影响行数
	 */
	public Integer saveOrUpdate(Map<String, Object> map);
	
	/**
	 * 移除对比标准属性API
	 * @author   ljc
	 * @version  2018-03-27 
	 * @param    mcaID           对比标准ID
	 * @return   InformationBody 响应信息
	 */
	public int deleteMaterialComparedAttributesByMcaID(@Param("mcaID")Integer mcaID);

    /**
     * 查找对比项和对比图,通过城市ID/档次标识/四级科目ID
     * 城市ID为null,返回'总标准'材料对比图
     * @author  ljc
     * @version 2018-5-31 22:55:03
     * @param   cityID 城市ID
     * @param   levelFlag   档次标识
     * @param   treeFourID  四级科目ID
     * @return  对比项和对比图结果集
     */
    List<ContrastItemWithPhoto> findContrastItemWithPhotoByCityIDAndLevelFlagAndTreeFourID(@Param("cityID")Integer cityID,@Param("levelFlag") Integer levelFlag,@Param("treeFourID") String treeFourID);
}
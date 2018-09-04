/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.StandardPara;

/**
 * 材料标准参数DAO接口
 * @author ljc
 * @version 2018-03-21
 */
@MyBatisDao
public interface StandardParaDao extends CrudDao<StandardPara> {
	
	/**
	 * 通过四级科目ID,查找材料参数.
	 * @author  ljc
	 * @version 2018-03-21 
	 * @param   treeFourID 四级科目ID
	 * @return  材料参数
	 */
	public 	List<Map<String, Object>> findMaterialParameterByTreeFourID(String treeFourID);
	
	/**
	 * 通过参数ID与四级科目ID,移除材料参数.
	 * @author   ljc
	 * @version  2018-03-25 
	 * @param    paraID      参数ID
	 * @param    treeFourID  四级科目ID
	 * @return   受影响行数
	 */
	public int deleteStandardParaByParaID(@Param("paraID") Integer paraID, @Param("treeFourID") String treeFourID, @Param("updateTime") Date updateTime);
	
	/**
	 * 新增或更新,标准材料参数.
	 * 
	 * @author   ljc
	 * @version  2018-03-26 
	 * @param    sql         联合sql片段
	 * @param    treeFourID  四级科目ID
	 */
	public void saveOrUpdate(@Param("sql") String sql, @Param("treeFourID") String treeFourID);
	
}
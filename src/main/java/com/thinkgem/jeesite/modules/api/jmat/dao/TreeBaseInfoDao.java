/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeBaseInfo;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformBasicInformation;

/**
 * 材料基础信息DAO接口
 * @author ljc
 * @version 2018-03-16
 */
@MyBatisDao
public interface TreeBaseInfoDao extends CrudDao<TreeBaseInfo> {
	
	/**
	 * 通过四级科目ID,查找材料基础信息.
	 * @author  ljc
	 * @version 2018-03-17 
	 * @param   treeFourID  四级科目ID
	 * @return  材料基础信息
	 */
	public TreeBaseInfo findTreeBaseInfoByTreeID(@Param("treeFourID") String treeFourID);
	
	/**
	 * 更新材料基础信息
	 * @author  ljc
	 * @version 2018-03-23 
	 * @param   pbi         平台基础信息更新参数
	 * @return  受影响行数
	 */
	public int updateByPlatformBasicInformation(PlatformBasicInformation pbi);
	
	/**
	 * 新增或更新材料基础信息
	 * @author  ljc
	 * @version 2018-03-27 
	 * @param   treeBaseInfo 材料基础信息Entity
	 * @return  受影响行数
	 */
	public int treeBaseInfoSaveOrUpdate(TreeBaseInfo treeBaseInfo);
	
	/**
	 * 通过四级科目ID,更新包装照片
	 * @author   ljc
	 * @version  2018-03-28 
	 * @param    treeFourID       四级科目ID
	 * @param    photoUrl         照片路径
	 * @return   InformationBody  接口调用状态信息
	 */
	public int updatePhotoUrlByTreeFourID(@Param("treeFourID") String treeFourID, @Param("photoUrl") String photoUrl);
	
	
	/**
	 * 保存 新增标准- 材料基础信息
	 * @author            ljc
	 * @version           1.0
	 * @createTime        2018年4月2日 13:50:14
	 * @param baseInfoID  材料基础信息ID    
	 * @param treeFourID  四级科目ID
	 * @param unitID      单位ID
	 * @describe          插入字段:ID,四级ID,单位ID
	 */
	public void saveCityNewlyIncreased(@Param("baseInfoID") String baseInfoID, @Param("treeFourID") String treeFourID, @Param("unitID") Integer unitID);
	
}
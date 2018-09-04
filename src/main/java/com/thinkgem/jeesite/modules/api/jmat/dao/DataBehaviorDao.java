/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNoteMaterial;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNotePhoto;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin.OperationNotePrice;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;

/**
 * 数据行为DAO接口
 * @author ljc
 * @version 2018-04-09
 */
@MyBatisDao
public interface DataBehaviorDao extends CrudDao<DataBehavior> {
	
	/**
	 * 查找近月材料调整,通过子库最近数据POJO
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-9 20:59:01
	 * @param      subLibraryRecentData   子库最近数据(包含数据行为常量值)POJO
	 * @return     Integer                调整材料数量
	 */
	public Integer findMatAdjustBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	/**
	 * 查找近月照片调整,通过子库最近数据POJO
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-9 20:59:01
	 * @param      subLibraryRecentData   子库最近数据(包含数据行为常量值)POJO
	 * @return     Integer                调整照片数量
	 */
	public Integer findPhotoAdjustBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	/**
	 * 查找近月价格调整,通过子库最近数据POJO
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-9 20:59:01
	 * @param      subLibraryRecentData   子库最近数据(包含数据行为常量值)POJO
	 * @return     Integer                调整价格数量
	 */
	public Integer findPriceAdjustBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	
	/**
	 * 查找子库起始页二段-操作记录数据,通过城市ID
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-11 19:45:49
	 * @param      subLibraryRecentData    子库最近数据(包含数据行为常量值)POJO
	 * @return     Map<String, Object>     Map:materialCount 材料总数,photoCount 照片总数,priceCount 价格总数
	 */
	public Map<String, Object> findIndexTwoSectionOperationNoteByCityID(SubLibraryRecentData subLibraryRecentData);
	
	/**
	 * 查找"材料"操作记录,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-11 17:26:04
	 * @param      subLibraryRecentData             子库最近操作数据模型对象:开始时间/结束时间/起始行数/结束行数/地区ID
	 * @return     List<OperationNoteMaterial>      "材料"操作记录List
	 */ 
	public List<OperationNoteMaterial> findMaterialOperationNoteBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	
	/**
	 * 查找"材料"操作总记录数,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-11 17:26:04
	 * @param      subLibraryRecentData 子库最近操作数据模型对象:开始时间/结束时间/地区ID
	 * @return     Integer              "材料"操作总记录数
	 */
	public Integer findMaterialOperationNoteCountBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	
	/**
	 * 查找"价格"操作总记录数,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-12 20:20:47
	 * @param      subLibraryRecentData 子库最近操作数据模型对象:开始时间/结束时间/地区ID
	 * @return     Integer              "价钱"操作总记录数
	 */
	public Integer findOperationNotePriceCountBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	
	/**
	 * 查找"价格"操作记录,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-12 20:20:42
	 * @param      subLibraryRecentData         子库最近操作数据模型对象:开始时间/结束时间/起始行数/结束行数/地区ID
	 * @return     List<OperationNotePrice>     "价格"操作记录List
	 */ 
	public List<OperationNotePrice> findOperationNotePriceBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	
	/**
	 * 查找"照片"操作总记录数,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-12 20:20:47
	 * @param      subLibraryRecentData  子库最近操作数据模型对象:开始时间/结束时间/地区ID
	 * @return     Integer               "照片"操作总记录数
	 */
	public Integer findOperationNotePhotoCountBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	
	/**
	 * 查找"照片"操作记录,通过子库最近操作数据模型对象.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-12 20:20:42
	 * @param      subLibraryRecentData          子库最近操作数据模型对象:开始时间/结束时间/起始行数/结束行数/地区ID
	 * @return     List<OperationNotePhoto>      "照片"操作记录List
	 */ 
	public List<OperationNotePhoto> findOperationNotePhotoBySubLibraryRecentData(SubLibraryRecentData subLibraryRecentData);
	
}
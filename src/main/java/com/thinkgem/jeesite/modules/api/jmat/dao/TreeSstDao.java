/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeSst;

/**
 * 科目树设置DAO接口
 * @author ljc
 * @version 2018-03-17
 */
@MyBatisDao
public interface TreeSstDao extends CrudDao<TreeSst> {

	/**
	 * 通过四级科目ID,查找科目树设置.
	 * @author  ljc 
	 * @version 2018-03-17 
	 * @param   treeFourID 四级科目ID
	 * @return  科目树设置
	 */
	public TreeSst findTreeSstByTreeID(@Param("treeFourID") String treeFourID);
	
	/**
	 * 更新分类
	 * @author  ljc
	 * @version 2018-03-23
	 * @param   treeID      四级
	 * @param   type        设定状态（16|1成品，2非成品）  （默认0）
	 * @param   updateTime  更新时间
	 * @return  更新受影响行数
	 */
	public int updateTypeStateByTreeID(@Param("treeID") String treeID, @Param("type") Integer type);
	
	/**
	 * 保存 新增标准- 科目树设置
	 * @author            ljc
	 * @version           1.0
	 * @createTime        2018年4月2日 14:26:37
	 * @describe		    插入字段:ID,四级ID,16类型,状态,树等级
	 * @param treeSstID   科目树设置ID
	 * @param treeFourID  四级科目ID
	 * @param treeLevel   树等级：0代表一级，1代表二级，2代表三级，3代表四级，4代表五级
	 * @param type        设定类型：1加工费无限制，2报价推送（不取舍小数），4报价推送（直接进位）,8电线标识 ,16成品安装  （|位运算）
	 * @param classify    设定状态（16|1成品，2非成品）  （默认0）
	 */
	public void saveCityNewlyIncreased(@Param("treeSstID") String treeSstID, @Param("treeFourID") String treeFourID, @Param("treeLevel") Integer treeLevel, @Param("type") Integer type, @Param("classify") Integer classify);
	
}
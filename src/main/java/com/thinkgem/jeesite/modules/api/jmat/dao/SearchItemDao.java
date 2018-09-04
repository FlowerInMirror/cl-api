/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.SearchItem;

/**
 * 材料搜索|用途DAO接口
 * @author ljc
 * @version 2018-03-28
 */
@MyBatisDao
public interface SearchItemDao extends CrudDao<SearchItem> {
	
	/**
	 * 通过四级科目ID,查找材料搜索|用途结果集.
	 * @author  ljc
	 * @version 2018-03-28 
	 * @param   treeFourID     四级科目ID
	 * @param   type           类型：1搜索，2用途，4搜索自动生成内容（可位运算|）
	 * @return  材料搜索|用途集合. 
	 */
	public List<SearchItem> findSearchItemsByTreeFourID(@Param("treeFourID") String treeFourID, @Param("type") Integer type);
	
	/**
	 * 搜索词编辑(删除|新增)
	 * @author   ljc
	 * @version  2018-03-28 
	 * @param    PlatformSearchTerm  平台搜索词POJO
	 * @return   InformationBody     响应状态信息
	 */
	public int searchTermEdit(@Param("treeFourID") String treeFourID, @Param("type") int type, @Param("sql") String sql);
	
}
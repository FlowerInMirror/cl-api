/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.Tree;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.CityNewlyIncreasedCorrelationEcho;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.SubLibraryCityOneSection;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.TreeVo;

/**
 * 科目树DAO接口
 * @author ljc
 * @version 2018-03-15
 */
@MyBatisDao
public interface TreeDao extends CrudDao<Tree> {

	/**
	 * 通过城市ID查找子库地区一段
	 * @author  ljc
	 * @version 2018-03-15
	 * @param   map 输入映射
	 * @return  子库信息一段结果集
	 */
	public List<SubLibraryCityOneSection> findCityOneSectionByMap(Map<String, Object> map);

	/**
	 * 通过参数Map,查找相关内容
	 * @author       ljc
	 * @version      1.0
	 * @createTime   2018-03-29
	 * @describe     科目树文本框键入相关数据回显(子库地区新增标准)
	 * @param        paraMap 输入映射
	 * @return       CityNewlyIncreasedCorrelationEcho  地区新增相关回显(科目树 输出映射)
	 */
	public List<CityNewlyIncreasedCorrelationEcho> findRelevantContentByParaMap(Map<String, Object> paraMap);

	
	/**
	 * 通过 科目数父级ID,查找降序第一条科目数编码.
	 * @author       ljc
	 * @version      1.0
	 * @createTime   2018-04-01
	 * @param        treeParentID 父级ID
	 * @return       String       科目树编码
	 */
	public String findDESCTop1TreeCodeByTreeParentID(@Param("treeParentID") String treeParentID);

	
	/**
	 * 保存 地区新增标准 科目树
	 * @author       ljc
	 * @version      1.0
	 * @createTime   2018年4月2日 11:58:53
	 * @describe     插入字段:ID,编码,名称,父级ID,级别,完善状态
	 * @param        treeOne                 科目树包装类
	 */
	public void saveCityNewlyIncreased(TreeVo treeOne);
	
	/**
	 * 校验科目树是否存在,通过科目树名称与科目树级别
	 * @author      ljc
	 * @version     1.0
	 * @createTime  2018-4-8 16:51:23
	 * @param       treeName           科目树名称
	 * @param       treeLevel 		   科目树级别
	 * @param       parentID           父级ID 
	 * @return      Integer            通过名称与级别,检索到的科目树数量.
	 */
	public Integer verificationTreeExistByTreeNameAndTreeLevelAndParentID(@Param("treeName") String treeName, @Param("treeLevel") Integer treeLevel, @Param("parentID") String parentID);
	
	/**
	 * 获取全部科目树名称，通过四级科目ID，
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-4-12 16:34:52
	 * @param      treeFourID               四级科目ID
	 * @return     SubLibraryCityOneSection 子库地区一段(囊获四级科目名称)
	 */
	public SubLibraryCityOneSection findAllTreeNnameByTreeFourID(@Param("treeFourID") String treeFourID);

    /**
     * 获取科目树名称集,通过科目树父级ID
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-8 16:39:07
     * @param      treeParentID                 科目树父级ID
     * @return     List<Map<String,String>>     K:科目树名称 treeName,科目树ID treeID.
     */
    public List<Map<String,String>> findTreeNamesByParentID(@Param("treeParentID") String treeParentID);

    /**
     * 获取全部二级分类
     * 对接:集团联盟
     * @author     ljc
     * @createTime 2018-7-17 15:45:41
     */
    List<Map<String,Object>> getAllTreeTwoData();


}
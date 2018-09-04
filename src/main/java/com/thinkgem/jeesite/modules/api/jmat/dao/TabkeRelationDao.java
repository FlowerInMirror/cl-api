/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.TabkeRelation;
import org.apache.ibatis.annotations.Param;

/**
 * 材料表关联性汇总表DAO接口
 * @author ljc
 * @version 2018-06-16
 */
@MyBatisDao
public interface TabkeRelationDao extends CrudDao<TabkeRelation> {

    /**
     * 材料自定模块的对应ID 获取自定照片板块ID
     * @author ljc
     * @param  treeFourID  四级科目ID
     * @param  tspBrandID  标准ID（针对对比图）  （针对10类型 是分类ID--V_TypeInfo）
     * @return Stirng       自定照片板块ID
     * @createTime 2018-6-16 11:54:25
     */
    String findIDByTreeFourIDAndBrandID(@Param("treeFourID")String treeFourID,@Param("tspBrandID") String tspBrandID);
}
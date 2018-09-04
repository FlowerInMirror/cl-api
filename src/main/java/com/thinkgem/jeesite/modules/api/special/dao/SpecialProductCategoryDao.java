/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialProductCategory;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialProductCategoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专项产品类别表DAO接口
 * @author ljc
 * @version 2018-08-24
 */
@MyBatisDao
public interface SpecialProductCategoryDao extends CrudDao<SpecialProductCategory> {

    // "=========================C U D=============================="

    // 添加:类别集(产品ID/类别ID/类别名称/类别项ID) @author ljc @createTime 2018-8-26 17:43:00
    int addSpecialProductCategorysByEntitys(List<SpecialProductCategory> specialProductCategorys);

    // "=========================FIND=============================="

    // 浏览页面:类别集 @author ljc @createTime 2018-8-26 21:40:28
    List<SpecialProductCategoryVo> findSpecialProductCategoryVosByProID(@Param("proID") String proID);
}
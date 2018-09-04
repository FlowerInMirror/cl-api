/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampType;
import com.thinkgem.jeesite.modules.api.major.entity.vo.MajorCampTypeVo;

import java.util.List;

/**
 * 主营类别表DAO接口
 * @author ljc
 * @version 2018-08-17
 */
@MyBatisDao
public interface MajorCampTypeDao extends CrudDao<MajorCampType> {

    // "============================== CUD =============================="

    // 主营管理:添加(主营属性类别),通过实体包装类 @auhtor ljc @createTime 2018-8-18 11:25:49
    int addCategoryByEntityVo(MajorCampTypeVo majorCampTypeVo);

    // 主营管理:更新(主营属性类别),通过实体包装类 @auhtor ljc @createTime 2018-8-18 13:14:10
    int updateCategoryByEntityVo(MajorCampTypeVo majorCampTypeVo);

    // 主营管理:删除(主营属性类别),通过实体包装类 @auhtor ljc @createTime 2018-8-18 13:16:38
    int deleteCategoryByEntityVo(MajorCampTypeVo majorCampTypeVo);

    // "============================== FIND/GET =============================="

    // 主营类别集,通过实体检索 @author ljc @createTime 2018-8-17 17:29:03
    List<MajorCampType> findMajorCampTypeListByEntity(MajorCampType majorCampType);



}
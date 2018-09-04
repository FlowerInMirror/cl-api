/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.special.entity.MajorCampGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 主营属性组合表DAO接口
 * @author ljc
 * @version 2018-08-17
 */
@MyBatisDao
public interface MajorCampGroupDao extends CrudDao<MajorCampGroup> {
    // "============================== CUD =============================="

    // 添加:组合套餐集(产品ID/编码/价格/是否主推) @auhtor ljc @createTime 2018-8-26 17:52:48
    int addMajorCampGroupsByEntitys(List<MajorCampGroup> majorCampGroups);

    // "============================ FIND ==============================="

    // 浏览:检索不可被选中项组 @author ljc @cerateTime 2018-8-27 13:18:01
    List<Integer> findDisabledItemIDsByProIDAndSearchCodeAndSearchCodeLen(@Param("proID") String proID,@Param("searchCode") String searchCode,@Param("searchCodeLength") int searchCodeLength);

    // 浏览:检索组合套餐价格 @author ljc @createTime 2018-8-27 14:04:30
    Double findGroupSetMealPriceByProIDAndSearchCode(@Param("proID") String proID,@Param("searchCode") String searchCode);

    // 编辑:检索主营组合套餐集 @author ljc @cerateTime 2018-8-27 20:06:06
    List<MajorCampGroup> findMajorCampGroupsByProID(String proID);
}
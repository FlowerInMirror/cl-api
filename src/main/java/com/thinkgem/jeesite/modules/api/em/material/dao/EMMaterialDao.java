/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.em.material.dao;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.em.material.entity.vo.EMMaterialListVo;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;
import com.thinkgem.jeesite.modules.api.special.entity.vo.MajorCampCategorysVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工程经理.材料DAO接口
 * @author ljc
 * @version 2018-8-30 09:55:43
 */
@MyBatisDao
public interface EMMaterialDao extends BaseDao {

    // "======================= 列表 ========================"

    // 材料列表 @depict 通过城市检索 @author ljc @createTime 2018-8-30 10:20:47
    List<EMMaterialListVo> findEMMaterialListVoByCityID(@Param("cityID") Integer cityID);

}
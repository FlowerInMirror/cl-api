/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialMatRelevance;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialMatRelevanceVo;

/**
 * 专项材料关联表DAO接口
 * @author ljc
 * @version 2018-08-08
 */
@MyBatisDao
public interface SpecialMatRelevanceDao  {
    // "=========================C U D=============================="
    /**
     * @author     ljc
     * @param      specialMatRelevance 专项材料关联实体
     * @return     int 受影响行数
     * @createTime 2018-8-8 17:03:42
     */
    // 专项:添加专项材料关联(专项产品ID/材料ID(专项产品1对1))
    int addSpecialMatRelevanceByEntity(SpecialMatRelevance specialMatRelevance);

    // 专项: 材料关联更新(更新内容/专项关联材料ID/操作人卡号/事件地区)
    /**
     * @author ljc
     * @createTime 2018-8-9 16:01:29
     */
    int updateMatRelevanceByEntityVo(SpecialMatRelevanceVo specialMatRelevanceVo);

    // "=========================GET FIND=============================="
    /**
     * @author     ljc
     * @createTime 2018-8-9 11:35:09
     */
    // 产品关联材料One
    SpecialMatRelevanceVo findSMROneByEntity(SpecialMatRelevance specialMatRelevance);



}
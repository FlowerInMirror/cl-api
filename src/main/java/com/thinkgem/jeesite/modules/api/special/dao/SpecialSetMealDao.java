/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialSetMeal;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialSetMealVo;

import java.util.List;

/**
 * 专项套餐表DAO接口
 * @author ljc
 * @version 2018-08-08
 */
@MyBatisDao
public interface SpecialSetMealDao {

    // "=========================C U D=============================="
    /**
     * @author     ljc
     * @param      ssms 专项产品集
     * @return     int 受影响函数
     * @createTime 2018-8-8 17:17:20
     */
    // 专项: 添加专项套餐集(ID/专项产品ID/套餐名称/套餐价格/套餐图片地址)
    int addSpecialSetMealByEntitys(List<SpecialSetMealVo> ssms);

    // 专项: 添加专项套餐(ID/专项产品ID/套餐名称/套餐价格/套餐图片地址)
    /**
     * @author     ljc
     * @param      ssm 专项产品
     * @return     int 受影响函数
     * @createTime 2018-8-13 19:29:11
     */
    int addSpecialSetMealOneByEntity(SpecialSetMealVo ssm);

    // 专项套餐All (进程：0正常、1删除、2下架  （默认0）)
    /**
     * @author ljc
     * @createTime 2018-8-9 11:38:30
     */
    List<SpecialSetMeal> findSSMAllByEntity(SpecialSetMeal specialSetMeal);

    // 专项: 删除套餐
    /**
     * @author ljc
     * @return int 受影响行数
     * @createTime 2018-8-9 13:00:00
     */
    int deleteSetMealByEntityVo(SpecialSetMealVo specialSetMealVo);

    // 专项: 更新套餐(更新字段/下架功能)
    /**
     * ssm_Status 进程：0正常、1删除、2下架  （默认0）
     * @author ljc
     * @createTime 2018-8-9 11:58:32
     */
    int updateSetMealByEntityVo(SpecialSetMealVo specialSetMealVo);

    // "=========================GET FIND=============================="
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.dao;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.major.entity.po.one.AList;

import java.util.List;

/**
 * 主营管理DAO接口
 * @author ljc
 * @version 2018-08-17
 */
@MyBatisDao
public interface MajorDao  {

    // "============================== 一段 =============================="

    /**
     * @author     ljc
     * @createTime 2018-8-17 17:01:31
     */
    // 主营管理:一段列表
    List<AList> getMajorAList();

    // "============================== 二段 =============================="



    // "============================== 三段 =============================="



	
}
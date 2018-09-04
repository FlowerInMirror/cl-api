/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.Visit;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.VisitVo;

import java.util.List;

/**
 * 项目|人员回访记录V_VisitDAO接口
 * @author  ljc
 * @version 2018-7-25 11:14:17
 */
@MyBatisDao
public interface VisitDao extends CrudDao<Visit> {

    /**
     * 回访记录:添加回访
     * @author ljc
     * @param  visitVo          回访记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-25 11:38:14
     */
    int saveVisit(VisitVo visitVo);

    /**
     * 回访记录:加载回访 通过回访记录包装类
     * @author ljc
     * @param  visitVo          回访记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-25 14:14:12
     */
    List<VisitVo> loadVisitByVisitVo(VisitVo visitVo);
}
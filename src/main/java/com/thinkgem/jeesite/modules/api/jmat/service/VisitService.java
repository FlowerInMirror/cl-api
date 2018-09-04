/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.dao.VisitDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.Visit;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.VisitVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目|人员回访记录V_VisitService
 * @author  ljc
 * @version 2018-7-25 11:14:17
 */
@Service
@Transactional(readOnly = true)
public class VisitService extends CrudService<VisitDao, Visit> {

    /**
     * 回访记录:添加回访
     * @author ljc
     * @param  visitVo          回访记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-25 11:38:14
     */
    @Transactional(readOnly = false)
    public InformationBody saveVisit(VisitVo visitVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {
            int numberOfAffectedRows = dao.saveVisit(visitVo);
            long endTime = System.currentTimeMillis();
            logger.debug("回访记录:添加回访,添加成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("回访记录:添加回访失败 请联系统管理员");
            logger.error("回访记录:添加回访接口,调用失败!", e);
        }
        return informationBody;
    }

    /**
     * 回访记录:加载回访
     * @author ljc
     * @param  visitVo          回访记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-25 14:14:12
     */
    public InformationBody loadVisit(VisitVo visitVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        Map<String, Object> bodyMap = new HashMap<>();

        try {
            // --- 数据区 ---
            List<VisitVo> VisitVos = dao.loadVisitByVisitVo(visitVo); // <符合条件的回访记录集>

            // --- 组织区 ---
            bodyMap.put("visits",VisitVos); // 回访记录集
            bodyMap.put("visitVo",visitVo); // 检索回访记录包装类

            informationBody.setBody(bodyMap);
            long endTime = System.currentTimeMillis();
            logger.debug("回访记录:加载回访,接口调用成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("回访记录:加载回访,接口调用失败 请联系统管理员");
            logger.error("回访记录:加载回访,接口调用失败!", e);
        }
        return informationBody;
    }
}
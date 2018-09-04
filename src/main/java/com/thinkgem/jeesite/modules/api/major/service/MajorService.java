/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreeDao;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.major.dao.MajorCampTypeDao;
import com.thinkgem.jeesite.modules.api.major.dao.MajorCampTypeItemDao;
import com.thinkgem.jeesite.modules.api.major.dao.MajorDao;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampType;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 主营管理Service
 * @author ljc
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly = true)
public class MajorService extends BaseService {

    // "=================== 数据访问对象 ======================"

    @Autowired
    private MajorDao majorDao; // 主营管理
    @Autowired
    private TreeDao treeDao; // 科目树
    @Autowired
    private MajorCampTypeDao majorCampTypeDao; // 主营类型
    @Autowired
    private MajorCampTypeItemDao majorCampTypeItemDao; // 主营类型项


    // "============================== 一段 =============================="

    // 主营:一段列表 @author ljc @createTime 2018-8-17 16:01:05
    public InformationBody getOneList() {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(majorDao.getMajorAList());
            long endTime = System.currentTimeMillis();
            logger.debug("主营:一段列表,数据抓成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营:一段列表,数据抓取失败 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("主营:一段列表,数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }


    // "============================== 二段 =============================="

    // 主营二段:类型 @author ljc @createTime 2018-8-17 17:10:36
    public InformationBody findTwoTypeByTreeTwoID(String treeTwoID) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(majorCampTypeDao.findMajorCampTypeListByEntity(new MajorCampType(treeTwoID)));
            long endTime = System.currentTimeMillis();
            logger.debug("主营二段:类型,数据抓成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营二段:类型,数据抓取失败 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("主营二段:类型,数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // "============================== 三段 =============================="

    // 主营三段:类型项 @author ljc @createTime 2018-8-17 17:36:06
    public InformationBody findTreeTypeItemByMajorTypeID(Integer majorTypeID) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(majorCampTypeItemDao.findMajorCampTypeItemListByEntity(new MajorCampTypeItem(majorTypeID)));
            long endTime = System.currentTimeMillis();
            logger.debug("主营三段:类型项,数据抓成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营三段:类型项,数据抓取失败 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("主主营三段:类型项,数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;

    }


}
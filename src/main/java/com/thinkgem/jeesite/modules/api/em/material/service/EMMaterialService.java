/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.em.material.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.em.material.dao.EMMaterialDao;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.special.entity.*;
import com.thinkgem.jeesite.modules.api.special.entity.qo.CategoryCorrelationQo;
import com.thinkgem.jeesite.modules.api.special.entity.vo.MajorCampCategorysVo;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialMatRelevanceVo;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialProductCategoryVo;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程经理.材料Service
 *
 * @author ljc
 * @version 2018-8-30 09:55:49
 */
@Service
@Transactional(readOnly = true)
public class EMMaterialService extends BaseService {

    @Autowired
    private DataSourceTransactionManager transactionManager; // 手动事务控制

    // "==================== 数据访问对象 ===================="
    @Autowired
    private EMMaterialDao emMaterialDao; // 工程经理.材料

    // "======================= 列表 ========================"

    // 材料列表 @author ljc @createTime 2018-8-30 10:16:25 @depict 通过城市检索
    public InformationBody findEMMaterialListVoByCityID(Integer cityID) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            informationBody.setBody(emMaterialDao.findEMMaterialListVoByCityID(cityID));
            endTime = System.currentTimeMillis();
            logger.debug("工程经理.材料:材料列表,数据抓成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("工程经理.材料:材料列表,数据抓取失败 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("工程经理.材料:材料列表,数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

}
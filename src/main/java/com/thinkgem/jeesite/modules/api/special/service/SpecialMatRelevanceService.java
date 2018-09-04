/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialMatRelevanceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.api.special.dao.SpecialMatRelevanceDao;

/**
 * 专项材料关联表Service
 * @author ljc
 * @version 2018-08-08
 */
@Service
@Transactional(readOnly = true)
public class SpecialMatRelevanceService  extends BaseService {

    @Autowired
    private SpecialMatRelevanceDao specialMatRelevanceDao; // 专项关联材料
    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为


    // 专项: 材料关联更新
    /**
     * 更新内容/专项关联材料ID/操作人卡号/事件地区
     * @author ljc
     * @createTime 2018-8-9 16:01:29
     */
    public InformationBody updateMatRelevance(SpecialMatRelevanceVo specialMatRelevanceVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {

            // 更新套餐
            int updateMatRelevanceNo = specialMatRelevanceDao.updateMatRelevanceByEntityVo(specialMatRelevanceVo);

            // 保存数据行为(操作人类型/操作人卡号/主ID/状态/行为内容/操作事件地区/操作用户)
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(specialMatRelevanceVo.getUserNo());
            dataBehavior.setDbClass(SubLibraryRecentData.M_B_ZX_PRO_UPDATE);
            dataBehavior.setDbMainid(specialMatRelevanceVo.getSmrSPID());
            dataBehavior.setDbEventid(specialMatRelevanceVo.getSmrID().toString());
            dataBehavior.setDbContent("专项: 材料关联更新");
            dataBehavior.setDbCityid(specialMatRelevanceVo.getDbCityID());
            dataBehavior.setDbIP(specialMatRelevanceVo.getDbIP());
            dataBehaviorDao.insert(dataBehavior);

            long endTime = System.currentTimeMillis();
            logger.debug("专项: 材料关联更新成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项: 材料关联更新异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项: 材料关联更新失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;

    }
}
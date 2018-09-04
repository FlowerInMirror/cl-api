/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.major.entity.vo.MajorCampTypeItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.api.major.dao.MajorCampTypeItemDao;

/**
 * 主营属性类别项表Service
 * @author ljc
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly = true)
public class MajorCampTypeItemService extends BaseService {

    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为
    @Autowired
    private MajorCampTypeItemDao majorCampTypeItemDao; // 主营属性类别项


    // 主营管理:添加主营属性(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-18 14:02:52
    public InformationBody addCategoryItemByEntityVo(MajorCampTypeItemVo majorCampTypeItemVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        Integer mctiType = majorCampTypeItemVo.getMctiType(); // 类型:0集团添加项,1材料商专属类别项 (默认0)
        String dbContent = mctiType == null ? "集团:添加主营属性(类别项)" : "材料商:添加自定义主营属性(类别项)";
        try {

            // 1.添加 主营属性(类别项)
            int addNo = majorCampTypeItemDao.addCategoryItemByEntityVo(majorCampTypeItemVo);

            // 2.保存 数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (addNo != 0 ) dataBehaviorDao.insert(new DataBehavior(majorCampTypeItemVo.getUserNo(),6, SubLibraryRecentData.M_B_ZY_ITEM_ADD,majorCampTypeItemVo.getMctiMctid().toString(),majorCampTypeItemVo.getMctiId().toString(),dbContent,majorCampTypeItemVo.getDbIP()));

            // 3.返回本次添加的类别项ID
            informationBody.setBody(majorCampTypeItemVo.getMctiId());

            endTime = System.currentTimeMillis();
            logger.debug("主营管理:"+dbContent+"成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:"+dbContent+"异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:"+dbContent+"失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 主营管理:更新主营属性(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-18 14:02:58
    public InformationBody updateCategoryItemByEntityVo(MajorCampTypeItemVo majorCampTypeItemVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        Integer mctiType = majorCampTypeItemVo.getMctiType(); // 类型:0集团添加项,1材料商专属类别项 (默认0)
        String dbContent = mctiType == null ? "集团:更新主营属性(类别项)" : "材料商:更新自定义主营属性(类别项)";
        try {


            // 1.更新 主营属性(类别项)
            int updateNo = majorCampTypeItemDao.updateCategoryItemByEntityVo(majorCampTypeItemVo);

            // 2.保存 数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            dataBehaviorDao.insert(new DataBehavior(majorCampTypeItemVo.getUserNo(),6, SubLibraryRecentData.M_B_ZY_ITEM_UPDATE,majorCampTypeItemVo.getMctiMctid().toString(),majorCampTypeItemVo.getMctiId().toString(),dbContent,majorCampTypeItemVo.getDbIP()));

            endTime = System.currentTimeMillis();
            logger.debug("主营管理:"+dbContent+"成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:"+dbContent+"异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:"+dbContent+"失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 主营管理:删除主营属性(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-18 14:03:01
    public InformationBody deleteCategoryItemByEntityVo(MajorCampTypeItemVo majorCampTypeItemVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        Integer mctiType = majorCampTypeItemVo.getMctiType(); // 类型:0集团添加项,1材料商专属类别项 (默认0)
        String dbContent = mctiType == null ? "集团:删除主营属性(类别项)" : "材料商:删除自定义主营属性(类别项)";
        try {

            // 1.删除 主营属性(类别项)
            int deleteNo = majorCampTypeItemDao.deleteCategoryItemByEntityVo(majorCampTypeItemVo);
            // 2.保存 数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (deleteNo != 0 ) dataBehaviorDao.insert(new DataBehavior(majorCampTypeItemVo.getUserNo(),6, SubLibraryRecentData.M_B_ZY_ITEM_DELETE,majorCampTypeItemVo.getMctiMctid().toString(),majorCampTypeItemVo.getMctiId().toString(),dbContent,majorCampTypeItemVo.getDbIP()));
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:"+dbContent+"成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:"+dbContent+"异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:"+dbContent+"失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }
}
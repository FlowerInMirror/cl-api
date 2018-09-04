/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.major.dao.MajorCampTypeDao;
import com.thinkgem.jeesite.modules.api.major.entity.vo.MajorCampTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 主营属性类别表Service
 *
 * @author ljc
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly = true)
public class MajorCampTypeService extends BaseService {

    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为
    @Autowired
    private MajorCampTypeDao majorCampTypeDao; // 主营属性类别


    // 主营管理:添加(主营属性类别),通过实体包装类 @auhtor ljc @createTime 2018-8-18 11:16:00
    @Transactional(readOnly = false)
    public InformationBody addCategoryByEntityVo(MajorCampTypeVo majorCampTypeVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();

        try {
            // 1.添加 主营属性类别
            int addNo = majorCampTypeDao.addCategoryByEntityVo(majorCampTypeVo);

            // 2.保存数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (addNo != 0 ) dataBehaviorDao.insert(new DataBehavior(majorCampTypeVo.getUserNo(),6,SubLibraryRecentData.M_B_ZY_TYPE_ADD,majorCampTypeVo.getMctTreetwoid(),majorCampTypeVo.getMctId().toString(),"添加主营属性类别",majorCampTypeVo.getDbIP()));

            endTime = System.currentTimeMillis();
            logger.debug("主营管理:添加主营属性类别成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:添加主营属性类别异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:添加主营属性类别失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 主营管理:更新(主营属性类别),通过实体包装类 @auhtor ljc @createTime 2018-8-18 13:14:10
    @Transactional(readOnly = false)
    public InformationBody updateCategoryByEntityVo(MajorCampTypeVo majorCampTypeVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            // 1.更新 主营属性类别
            int updateNo = majorCampTypeDao.updateCategoryByEntityVo(majorCampTypeVo);

            // 2.保存数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            dataBehaviorDao.insert(new DataBehavior(majorCampTypeVo.getUserNo(),6,SubLibraryRecentData.M_B_ZY_TYPE_UPDATE,majorCampTypeVo.getMctTreetwoid(),majorCampTypeVo.getMctId().toString(),"更新主营属性类别",majorCampTypeVo.getDbIP()));

            endTime = System.currentTimeMillis();
            logger.debug("主营管理:更新主营属性类别-成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:更新主营属性类别-异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:更新主营属性类别-失败!程序运行时间:" + (endTime - startTime) + "ms", e);

        }
        return informationBody;
    }

    // 主营管理:删除(主营属性类别),通过实体包装类 @auhtor ljc @createTime 2018-8-18 13:16:38
    @Transactional(readOnly = false)
    public InformationBody deleteCategoryByEntityVo(MajorCampTypeVo majorCampTypeVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            // 1.删除 主营属性类别
            int deleteNo = majorCampTypeDao.deleteCategoryByEntityVo(majorCampTypeVo);

            // 2.保存 数据行为(操作人类型/操作人卡号/行为分类ID/主ID/事件ID/行为内容/操作用户)
            if (deleteNo != 0 ) dataBehaviorDao.insert(new DataBehavior(majorCampTypeVo.getUserNo(),6,SubLibraryRecentData.M_B_ZX_PRO_DELETE,majorCampTypeVo.getMctTreetwoid(),majorCampTypeVo.getMctId().toString(),"删除主营属性类别",majorCampTypeVo.getDbIP()));

            endTime = System.currentTimeMillis();
            logger.debug("主营管理:删除主营属性类别-成功!程序运行时间:" + (endTime - startTime) + "ms");

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:删除主营属性类别-异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:删除主营属性类别-失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }
}
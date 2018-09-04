/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.special.entity.vo.MajorMaterialDealerItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.api.special.dao.MajorMaterialDealerItemDao;

/**
 * 材料商自定义分项表Service
 * @author ljc
 * @version 2018-08-23
 */
@Service
@Transactional(readOnly = true)
public class MajorMaterialDealerItemService extends BaseService {

    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为
    @Autowired
    private MajorMaterialDealerItemDao majorMaterialDealerItemDao; // 材料商自定义分项

    // 专项产品:添加材料商自定义(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-23 20:27:15
    public InformationBody addDealerCategoryItemByEntityVo(MajorMaterialDealerItemVo majorMaterialDealerItemVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            // 添加 主营属性(类别项)
            int addNo = majorMaterialDealerItemDao.addDealerCategoryItemByEntityVo(majorMaterialDealerItemVo);

            // 保存 数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (addNo != 0 ) dataBehaviorDao.insert(new DataBehavior(majorMaterialDealerItemVo.getUserNo(),6, SubLibraryRecentData.M_B_ZY_ITEM_ADD,majorMaterialDealerItemVo.getMmdiSpid(),majorMaterialDealerItemVo.getMmdiId().toString(),"添加材料商自定义(类别项)",majorMaterialDealerItemVo.getDbIP()));

            endTime = System.currentTimeMillis();
            logger.debug("专项产品:添加材料商自定义(类别项)成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:添加主营属性(类别项)异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("专项产品:添加材料商自定义(类别项)失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 专项产品:更新材料商自定义(类别项),通过实体包装类 @auhtor ljc @createTime 2018-8-23 20:27:19
    public InformationBody updateDealerCategoryItemByEntityVo(MajorMaterialDealerItemVo majorMaterialDealerItemVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            // 更新 主营属性(类别项)
            int updateNo = majorMaterialDealerItemDao.updateDealerCategoryItemByEntityVo(majorMaterialDealerItemVo);

            // 保存 数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            dataBehaviorDao.insert(new DataBehavior(majorMaterialDealerItemVo.getUserNo(),6, SubLibraryRecentData.M_B_ZY_ITEM_ADD,majorMaterialDealerItemVo.getMmdiSpid(),majorMaterialDealerItemVo.getMmdiId().toString(),"更新材料商自定义(类别项)",majorMaterialDealerItemVo.getDbIP()));

            endTime = System.currentTimeMillis();
            logger.debug("专项产品:更新材料商自定义(类别项)成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项产品:更新材料商自定义(类别项)异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("专项产品:更新材料商自定义(类别项)失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 专项产品:删除材料商自定义(类别项)API,通过实体包装类 @auhtor ljc @createTime 2018-8-23 20:27:23
    public InformationBody deleteDealerCategoryItemByEntityVo(MajorMaterialDealerItemVo majorMaterialDealerItemVo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            // 删除 主营属性(类别项)
            int deleteNo = majorMaterialDealerItemDao.deleteDealerCategoryItemByEntityVo(majorMaterialDealerItemVo);
            // 保存 数据行为(操作人类型/操作人卡号/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (deleteNo != 0 ) dataBehaviorDao.insert(new DataBehavior(majorMaterialDealerItemVo.getUserNo(),6, SubLibraryRecentData.M_B_ZY_ITEM_ADD,majorMaterialDealerItemVo.getMmdiSpid(),majorMaterialDealerItemVo.getMmdiId().toString(),"删除材料商自定义(类别项)",majorMaterialDealerItemVo.getDbIP()));
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:删除主营属性(类别项)成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("主营管理:删除主营属性(类别项)异常 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("主营管理:删除主营属性(类别项)失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

}
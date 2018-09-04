/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.service;

import java.util.List;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialSetMealVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.api.special.dao.SpecialSetMealDao;

/**
 * 专项套餐表Service
 * @author ljc
 * @version 2018-08-08
 */
@Service
@Transactional(readOnly = true)
public class SpecialSetMealService extends BaseService {

    // --- 数据访问对象 ---
    @Autowired
    private SpecialSetMealDao specialSetMealDao; // 专项套餐
    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为

    // 专项: 删除套餐
    /**
     * ssm_Status 进程：0正常、1删除、2下架  （默认0）
     * @author ljc
     * @createTime 2018-8-9 11:58:24
     */
    public InformationBody deleteSetMeal(SpecialSetMealVo specialSetMealVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {
            // 删除套餐
            int deleteSetMealNo = specialSetMealDao.deleteSetMealByEntityVo(specialSetMealVo);

            // 保存数据行为(操作人类型/操作人卡号/主ID/状态/行为内容/操作事件地区/操作用户)
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(specialSetMealVo.getUserNo());
            dataBehavior.setDbClass(SubLibraryRecentData.M_B_ZX_PRO_UPDATE);
            dataBehavior.setDbMainid(specialSetMealVo.getSsmSPID());
            dataBehavior.setDbEventid(specialSetMealVo.getSsmID());
            dataBehavior.setDbContent("专项:套餐删除");
            dataBehavior.setDbCityid(specialSetMealVo.getDbCityID());
            dataBehavior.setDbIP(specialSetMealVo.getDbIP());
            dataBehaviorDao.insert(dataBehavior);

            long endTime = System.currentTimeMillis();
            logger.debug("专项: 删除套餐成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项: 删除套餐异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项: 删除套餐失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 专项: 更新套餐(更新字段/下架/上架功能)
    /**
     * ssm_Status 进程：0正常、1删除、2下架  （默认0）
     * @author ljc
     * @createTime 2018-8-9 11:58:32
     */
    public InformationBody updateSetMeal(SpecialSetMealVo specialSetMealVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {
            // --- 赋值调用 ---
            Integer operationType = specialSetMealVo.getSsmStatus(); // 进程：0正常、1删除、2下架  （默认0）

            // --- 变量区 ---
            String dbCountent = "专项:套餐更新";

            // 更新套餐
            int updateSetMealNo = specialSetMealDao.updateSetMealByEntityVo(specialSetMealVo);

            // 保存数据行为(操作人类型/操作人卡号/主ID/状态/行为内容/操作事件地区/操作用户)
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(specialSetMealVo.getUserNo());
            dataBehavior.setDbClass(SubLibraryRecentData.M_B_ZX_PRO_UPDATE);
            dataBehavior.setDbMainid(specialSetMealVo.getSsmSPID());
            dataBehavior.setDbEventid(specialSetMealVo.getSsmID());

            // 判断更新套餐类型,设置套餐操作内容
            if (operationType != null){
                if (operationType.equals(1))
                    dbCountent = "专项:套餐下架";
                if (operationType.equals(0))
                    dbCountent = "专项:套餐上架";
            }
            dataBehavior.setDbContent(dbCountent);
            dataBehavior.setDbCityid(specialSetMealVo.getDbCityID());
            dataBehavior.setDbIP(specialSetMealVo.getDbIP());
            dataBehaviorDao.insert(dataBehavior);

            long endTime = System.currentTimeMillis();
            logger.debug("专项: 更新套餐成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项: 更新套餐异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项: 更新套餐失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 专项: 批量更新套餐集(含括新增套餐)
    /**
     * @author ljc
     * @createTime 2018-8-13 16:57:25
     */
    public InformationBody updateSetMeals(List<SpecialSetMealVo> specialSetMealVos, String requestUserIP) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {

            for (SpecialSetMealVo specialSetMealVo : specialSetMealVos) {

                // 处理 套餐图片路径(为:/static/mms/images/pic/zan_nopic.png 置为null)
                String ssmMealPhotoURL = specialSetMealVo.getSsmMealPhotoURL();
                if (StringUtils.isBlank(ssmMealPhotoURL) || "/static/mms/images/pic/zan_nopic.png".equals(ssmMealPhotoURL) || "".equals(ssmMealPhotoURL))
                    specialSetMealVo.setSsmMealPhotoURL(null);

                String dbContent = "专项:套餐更新";
                specialSetMealVo.setDbIP(requestUserIP);

                // 判断是否传递套餐ID,无为新增套餐
                if (StringUtils.isBlank(specialSetMealVo.getSsmID())){
                    dbContent = "专项:套餐新增";
                    // 新增套餐
                    int addSpecialSetMealOneNo = specialSetMealDao.addSpecialSetMealOneByEntity(specialSetMealVo);
                } else {
                    // 更新套餐
                    int updateSetMealNo = specialSetMealDao.updateSetMealByEntityVo(specialSetMealVo);
                }


                // 保存数据行为(操作人类型/操作人卡号/主ID/状态/行为内容/操作事件地区/操作用户)
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(specialSetMealVo.getUserNo());
                dataBehavior.setDbClass(SubLibraryRecentData.M_B_ZX_PRO_UPDATE);
                dataBehavior.setDbMainid(specialSetMealVo.getSsmSPID());
                dataBehavior.setDbEventid(specialSetMealVo.getSsmID());
                dataBehavior.setDbContent(dbContent);
                dataBehavior.setDbCityid(specialSetMealVo.getDbCityID());
                dataBehavior.setDbIP(specialSetMealVo.getDbIP());
                dataBehaviorDao.insert(dataBehavior);
            }

            long endTime = System.currentTimeMillis();
            logger.debug("专项: 更新套餐成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项: 更新套餐异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项: 更新套餐失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }
}
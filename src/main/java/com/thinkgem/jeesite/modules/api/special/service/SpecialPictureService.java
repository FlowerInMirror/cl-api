/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialPictureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.api.special.dao.SpecialPictureDao;

/**
 * 专项图片表Service
 * @author ljc
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly = true)
public class SpecialPictureService extends BaseService {

    @Autowired
    private SpecialPictureDao specialPictureDao; // 专项图片
    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为

    // 添加专项相关照片 @author ljc @Depict 添加内容:专项产品ID/专项图片类型:1主图/图片地址 @createTime 2018-8-20 11:14:32
    public InformationBody addSpecialPictureByEntityVo(SpecialPictureVo specialPictureVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {

            // 专项:添加图片
            int addSpecialPictureNo = specialPictureDao.addSpecialPictureByEntityVo(specialPictureVo);

            // 保存数据行为(有参:操作人卡号/操作人类型/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (addSpecialPictureNo != 0 ) dataBehaviorDao.insert( new DataBehavior(specialPictureVo.getUserNo(),6,SubLibraryRecentData.M_B_ZX_PRO_UPDATE,specialPictureVo.getSpdSpid(),specialPictureVo.getSdpId(),"专项产品:添加图片",specialPictureVo.getDbIP()));

            long endTime = System.currentTimeMillis();
            logger.debug("专项:添加图片,成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项:添加图片,异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项:添加图片,异常!程序运行时间:" + (endTime - startTime) + "ms",e);
        }
        return informationBody;
    }

    // 更新-专项产品照片 @author ljc @createTime 2018-8-20 11:42:27
    public InformationBody updateSpecialPictureByEntityVo(SpecialPictureVo specialPictureVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {

            // 专项:更新图片
            int updateSpecialPictureNo = specialPictureDao.updateSpecialPictureByEntityVo(specialPictureVo);

            // 保存数据行为(有参:操作人卡号/操作人类型/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (updateSpecialPictureNo != 0 ) dataBehaviorDao.insert( new DataBehavior(specialPictureVo.getUserNo(),6,SubLibraryRecentData.M_B_ZX_PRO_UPDATE,specialPictureVo.getSpdSpid(),specialPictureVo.getSdpId(),"专项产品:更新图片",specialPictureVo.getDbIP()));

            long endTime = System.currentTimeMillis();
            logger.debug("专项:更新图片,成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项:更新图片,异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项:更新图片,异常!程序运行时间:" + (endTime - startTime) + "ms",e);
        }
        return informationBody;
    }

    // 删除-专项产品照片 @author ljc @createTime 2018-8-20 11:42:39
    public InformationBody deleteSpecialPictureByEntityVo(SpecialPictureVo specialPictureVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        try {

            // 专项:删除图片
            int deleteSpecialPictureNo = specialPictureDao.deleteSpecialPictureByEntityVo(specialPictureVo);

            // 保存数据行为(有参:操作人卡号/操作人类型/行为类型/主ID/事件ID/行为内容/操作用户IP)
            if (deleteSpecialPictureNo != 0 ) dataBehaviorDao.insert( new DataBehavior(specialPictureVo.getUserNo(),6,SubLibraryRecentData.M_B_ZX_PRO_UPDATE,specialPictureVo.getSpdSpid(),specialPictureVo.getSdpId(),"专项产品:删除图片",specialPictureVo.getDbIP()));

            long endTime = System.currentTimeMillis();
            logger.debug("专项:删除图片,成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项:删除图片,异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项:删除图片,异常!程序运行时间:" + (endTime - startTime) + "ms",e);
        }
        return informationBody;
    }
}
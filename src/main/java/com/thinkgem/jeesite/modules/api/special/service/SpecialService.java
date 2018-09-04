/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.special.dao.*;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专项Service
 *
 * @author ljc
 * @version 2018-8-9 10:52:58
 */
@Service
@Transactional(readOnly = true)
public class SpecialService extends BaseService {

    @Autowired
    private DataSourceTransactionManager transactionManager; // 手动事务控制

    // --- 数据访问对象 ---
    @Autowired
    private SpecialDao specialDao; // 专项
    @Autowired
    private SpecialProductDao specialProductDao; // 专项产品
    @Autowired
    private SpecialPictureDao specialPictureDao; // 专项图片
    @Autowired
    private SpecialProductCategoryDao specialProductCategoryDao; // 专项产品类别
    @Autowired
    private MajorMaterialDealerItemDao majorMaterialDealerItemDao; // 主营材料材料商自定义类别项
    @Autowired
    private MajorCampGroupDao majorCampGroupDao; // 主营属性组合
    @Autowired
    private SpecialMatRelevanceDao specialMatRelevanceDao; // 专项关联材料
    @Autowired
    private SpecialSetMealDao specialSetMealDao; // 专项套餐
    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为


    // 专项二段:产品 @author ljc @createTime 2018-8-26 19:09:56
    /**
     * @param      cityID     城市ID
     * @param      spDealerID 经销商ID
     * @return     产品名称/产品状态/材料商ID/组合最低价 ~ 组合最高价
     */
    public InformationBody getSpecialTwoProduct(Integer cityID, Integer spDealerID) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        Map<String, Object> body = new HashMap<>();
        try {
            informationBody.setBody(specialProductDao.findSpecialProductALLByEntity(new SpecialProduct(spDealerID, cityID)));
            endTime = System.currentTimeMillis();
            logger.debug("专项二段:产品,数据抓成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项二段:产品数据抓取失败 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("专项二段:产品,数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 浏览-专项产品
    /**
     * @author     ljc
     * @param      spID  专项产品ID
     * @createTime 2018-8-26 18:51:17
     */
    public InformationBody getSpecialThreeProductItems(String spID) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        Map<String, Object> body = new HashMap<>();
        try {
            // --- 数据区 ---
            SpecialProductVo specialProduct = specialProductDao.findSpecialProductOneByEntity(new SpecialProduct(spID)); // <产品/产品详情>
            SpecialMatRelevanceVo specialMatRelevance = specialMatRelevanceDao.findSMROneByEntity(new SpecialMatRelevance(spID)); // <关联材料>
            List<SpecialSetMeal>  specialSetMeals = specialSetMealDao.findSSMAllByEntity(new SpecialSetMeal(spID)); // <套餐集>

            // --- 组织区 ---

            // 产品
            body.put("product",specialProduct);

            // 关联材料
            body.put("matRelevance",specialMatRelevance);

            // 套餐
            body.put("setMeals",specialSetMeals);

            informationBody.setBody(body);
            long endTime = System.currentTimeMillis();
            logger.debug("专项三段:产品,数据抓成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项二段:产品数据抓取失败 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项三段:产品,数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 添加专项产品:获取所有类别与类别项(含材料商专属类别项) @depict 根据主营二级科目ID/材料商ID检索 @author ljc @createTime
    public InformationBody findAddCategorysByTreeTwoIDAndZXUserID(String treeTwoID, Integer zxUserID) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            List<MajorCampCategorysVo> majorCampCategorysVos = specialDao.findAddCategorysByTreeTwoIDAndZXUserID(treeTwoID,zxUserID);
            for (MajorCampCategorysVo majorCampCategorysVo : majorCampCategorysVos) {
                majorCampCategorysVo.setCategoryItem(specialDao.findCategoryItemsByCategoryIDAndZXUserID(majorCampCategorysVo.getMctId(),zxUserID));
            }
            informationBody.setBody(majorCampCategorysVos);
            endTime = System.currentTimeMillis();
            logger.debug("添加专项产品:获取所有类别与类别项抓取成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("添加专项产品:获取所有类别与类别项数据抓取失败 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("添加专项产品:获取所有类别与类别项,数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;

    }

    // 浏览页面:回显  @author ljc @createTime 2018-8-26 18:52:38
    public InformationBody findOpenProductBrowsingDataByProID(String proID) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        Map<String, Object> body = new HashMap<>();
        try {
            // 专项产品基础信息
            SpecialProductVo specialProduct = specialProductDao.findSpecialProductOneByEntity(new SpecialProduct(proID)); // <产品/产品详情>
            if (specialProduct == null)
            {
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("错误:未能给予正确的专项产品ID");
                return informationBody;
            }

            // 产品主图集(1主图)
            List<SpecialPicture> specialPictures = specialPictureDao.findSpecialPicturesByProIDAndType(proID,1);

            // 组织类别:1.获取产品下的类别;2.根据类别组织针对产品类别下的类别项.
            List<SpecialProductCategoryVo> specialProductCategoryVos = specialProductCategoryDao.findSpecialProductCategoryVosByProID(proID);
            specialProductCategoryVos.forEach(item->{item.setCategoryItem(specialDao.findCategoryItemsByCategoryIDAndProID(item.getSpcMctid(),proID));});

            // 设置数据返回
            body.put("product",specialProduct);
            body.put("pictures",specialPictures);
            body.put("categorys",specialProductCategoryVos);
            informationBody.setBody(body);
            endTime = System.currentTimeMillis();
            logger.debug("浏览页面:回显 数据抓取成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("浏览页面:回显 数据抓取失败 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("浏览页面:回显 数据抓取失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 浏览页面:检索类别相关(获取组合套餐价以及类别是否不可选) @author ljc @createTime 2018-8-27 10:37:57
    public InformationBody searchCategoryCorrelationByProIDAndCategoryItems(CategoryCorrelationQo categoryCorrelationQo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        try {
            Integer searchType = categoryCorrelationQo.getSearchType();
            String proID = categoryCorrelationQo.getProID();
            List<Integer> categoryItems = categoryCorrelationQo.getCategoryItems();

            // 区分类别:1.检索不可被选中项组;2.检索组合套餐价格

            if (searchType  == 1)
            // 检索不可选中项组
            {
                // 1.组织:检索编码
                String searchCode = StringUtils.join(categoryItems, "-") + "-";

                // 2.检索:根据检索编码处理步骤2获取的编码集得到最终不可选项组
                List<Integer> disabledItemIDs = majorCampGroupDao.findDisabledItemIDsByProIDAndSearchCodeAndSearchCodeLen(proID, searchCode, searchCode.length() + 1);

                // 3.返回:禁用不可选项ID组
                informationBody.setBody(disabledItemIDs);
            }
            if (searchType  == 2)
            // 检索组合套餐价格
            {
                // 1.组织组合套餐编码
                String searchCode = StringUtils.join(categoryItems, "-");
                // 2.根据步骤1组合套餐编码检索价格返回
                Double groupSetMealPrice = majorCampGroupDao.findGroupSetMealPriceByProIDAndSearchCode(proID,searchCode);
                // 3.返回组合套餐价格
                if (groupSetMealPrice != null) {
                    informationBody.setBody(new DecimalFormat("#.00").format(groupSetMealPrice));
                }

            }

            endTime = System.currentTimeMillis();
            logger.debug("浏览页面:检索类别相关 数据抓取成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("浏览页面:检索类别相关 数据抓取失败 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("浏览页面:检索类别相关 数据抓取失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }

    // 编辑页面:数据回显  @author ljc @createTime 2018-8-27 19:34:57
    public InformationBody findOpenProductEditDataByProID(String proID) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();
        Map<String, Object> body = new HashMap<>();
        try {
            // 专项产品基础信息
            SpecialProductVo specialProduct = specialProductDao.findSpecialProductOneByEntity(new SpecialProduct(proID)); // <产品/产品详情>
            if (specialProduct == null)
            {
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("错误:未能给予正确的专项产品ID");
                return informationBody;
            }

            // 产品主图集(1主图)
            List<SpecialPicture> specialPictures = specialPictureDao.findSpecialPicturesByProIDAndType(proID,1);

            // 默认显示:类别集相关数据
            List<MajorCampCategorysVo> majorCampCategorysVos = specialDao.findAddCategorysByTreeTwoIDAndZXUserID(specialProduct.getSpMajorTreeTwoID(),specialProduct.getSpDealerID());
            majorCampCategorysVos.forEach(item->{item.setCategoryItem(specialDao.findEditCategoryItemsByCategoryIDAndProID(item.getMctId(),proID));});

            // 已有回显:类别集相关数据
            List<SpecialProductCategoryVo> specialProductCategoryVos = specialProductCategoryDao.findSpecialProductCategoryVosByProID(proID);
            specialProductCategoryVos.forEach(item->{item.setCategoryItem(specialDao.findCategoryItemsByCategoryIDAndProID(item.getSpcMctid(),proID));});

            // 组合套餐数据
            List<MajorCampGroup> majorCampGroups =  majorCampGroupDao.findMajorCampGroupsByProID(proID);

            // 设置返回
            body.put("product",specialProduct);
            body.put("pictures",specialPictures);
            body.put("categorys",specialProductCategoryVos);
            body.put("dftCategorys",majorCampCategorysVos);
            body.put("groups",majorCampGroups);
            informationBody.setBody(body);
            endTime = System.currentTimeMillis();
            logger.debug("编辑页面:数据回显 数据抓取成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("编辑页面:数据回显 数据抓取失败 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("编辑页面:数据回显 数据抓失败!程序运行时间:" + (endTime - startTime) + "ms", e);
        }
        return informationBody;
    }
}
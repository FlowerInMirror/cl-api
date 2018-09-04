/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.service;

import java.util.List;
import java.util.UUID;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.special.dao.*;
import com.thinkgem.jeesite.modules.api.special.entity.*;
import com.thinkgem.jeesite.modules.api.special.entity.bo.SpecialProductBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 专项产品Service
 * @author ljc
 * @version 2018-08-08
 */
@Service
@Transactional(readOnly = true)
public class SpecialProductService extends BaseService {

    @Autowired
    private DataSourceTransactionManager transactionManager; // 手动事务控制

    // --- 数据访问对象 ---
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

    // 专项:添加产品,通过专项产品业务
    /**
     * @param      specialProductBo 专项产品业务类
     * @return     当前新增的专项产品ID
     * @createTime 2018-8-26 12:57:21
     */
    @Transactional(readOnly = false)
    public InformationBody addSpecialProductoBySpecialProductBo(SpecialProductBo specialProductBo) {
        long startTime = System.currentTimeMillis();
        long endTime;
        InformationBody informationBody = new InformationBody();

        // 手动事务控制
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        // 新的专项产品ID
        String newSPID = UUID.randomUUID().toString();

        try {
            // 数据准备
            List<SpecialPicture> specialPictures = specialProductBo.getSpecialPictures(); // 专项产品主图集
            List<SpecialProductCategory> specialProductCategorys = specialProductBo.getSpecialProductCategory(); // 类别集
            List<MajorMaterialDealerItem> majorMaterialDealerItems = specialProductBo.getMajorMaterialDealerItems(); // 材料商自定义项
            List<MajorCampGroup> majorCampGroups = specialProductBo.getMajorCampGroups(); // 针对产品 材料商自定义项集
            specialPictures.forEach(item->{item.setSpdSpid(newSPID);});
            specialProductCategorys.forEach(item->{item.setSpcSpid(newSPID);});
            majorMaterialDealerItems.forEach(item->{item.setMmdiSpid(newSPID);});
            majorCampGroups.forEach(item->{item.setMcgSpid(newSPID);});

            // 开始保存

            // 1.专项产品基础信息(材料商ID/材料商城市/所属主营二级科目ID/产品名称/单位/产品详细)
            int addSpecialProductNo = specialProductDao.addSpecialProductByEntity(specialProductBo.setSpID(newSPID));

            if (addSpecialProductNo != 0)
            {
                // 2.专项产品主图集(专项产品ID/图片类型/图片地址)
                int addSpecialPicturesNo = specialPictureDao.addSpecialPicturesByEntitys(specialPictures);

                // 3.类别集(产品ID/类别ID/类别名称/类别项ID)
                int addSpecialProductCategorysNo = specialProductCategoryDao.addSpecialProductCategorysByEntitys(specialProductCategorys);

                // 4.材料商自定义项集(类别项ID/产品ID/名称)
                if (majorMaterialDealerItems.size() > 0){
                    int addDealerCategoryItemNo = majorMaterialDealerItemDao.addDealerCategoryItemsByEntitys(majorMaterialDealerItems);
                }


                // 5.组合套餐集(产品ID/编码/价格/是否主推)
                int majorCampGroupNo = majorCampGroupDao.addMajorCampGroupsByEntitys(majorCampGroups);

                // 6.保存 数据行为(添加:专项产品)
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperatuser(specialProductBo.getUserNo());// 操作人卡号
                dataBehavior.setDbClass(SubLibraryRecentData.M_B_ZX_PRO_ADD);// 操作行为分类编码
                dataBehavior.setDbMainid(newSPID);// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
                dataBehavior.setDbContent("专项:专项产品添加");// 行为内容
                dataBehavior.setDbIP(specialProductBo.getDbIP()); // 操作用户IP
                dataBehaviorDao.insert(dataBehavior);
            }
            else
            {
                transactionManager.rollback(status);
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("专项添加产品:添加产品基础数据异常,请联系系统管理员!");
                return informationBody;
            }

            // 设置专项产品ID返回
            informationBody.setBody(newSPID);
            transactionManager.commit(status);
            endTime = System.currentTimeMillis();
            logger.debug("专项:添加产品,成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            transactionManager.rollback(status);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项:添加产品失败 请联系统管理员");
            endTime = System.currentTimeMillis();
            logger.debug("专项:添加产品,异常!程序运行时间:" + (endTime - startTime) + "ms",e);
        }
        return informationBody;
    }

    // 专项产品更新(产品字段更新/产品详情修改/产品上架/下架)
    /**
     * @author ljc
     * @param  specialProductBo 专项产品包装类
     * @return InformationBody  响应信息体
     * @createTime 2018-8-27 23:02:29
     */
    public InformationBody updateSProBySpecialProductBo(SpecialProductBo specialProductBo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        String dbCountent = "专项:套餐更新";
        try {
            // --- 赋值调用 ---
            Integer operationType = specialProductBo.getSpStatus(); // 操作类型 = > 进程：0正常、1删除、2下架  （默认0）

            // 更新专项产品
            int updateSProNo = specialProductDao.updateSProBySpecialProductBo(specialProductBo);

            // 保存数据行为(更新:专项产品)
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);// 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商，200集团主案，201地方主案）
            dataBehavior.setDbOperatuser(specialProductBo.getUserNo());// 操作人卡号
            dataBehavior.setDbClass(SubLibraryRecentData.M_B_ZX_PRO_UPDATE);// 操作行为分类编码
            dataBehavior.setDbMainid(specialProductBo.getSpID());// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
            // 判断更新套餐类型,设置套餐操作内容
            if (operationType != null){
                if (operationType.equals(1))
                    dbCountent = "专项:产品下架";
                if (operationType.equals(0))
                    dbCountent = "专项:产品上架";
            }
            dataBehavior.setDbContent(dbCountent);// 行为内容
            dataBehavior.setDbIP(specialProductBo.getDbIP()); // 操作用户IP
            dataBehaviorDao.insert(dataBehavior);

            long endTime = System.currentTimeMillis();
            logger.debug(dbCountent + ",成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg(dbCountent + ",异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug(dbCountent + ",异常!程序运行时间:" + (endTime - startTime) + "ms",e);
        }
        return informationBody;
    }

    // 专项产品删除
    /**
     * @author ljc
     * @param  specialProductBo 专项产品包装类
     * @return InformationBody  响应信息体
     * @createTime 2018-8-27 23:02:33
     */
    public InformationBody deleteSProBySpecialProductBo(SpecialProductBo specialProductBo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();
        String dbCountent = "";
        try {

            // 删除专项产品
            int deleteSProNo = specialProductDao.deleteSProBySpecialProductBo(specialProductBo);

            // 保存数据行为(更新:专项产品)
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);// 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商，200集团主案，201地方主案）
            dataBehavior.setDbOperatuser(specialProductBo.getUserNo());// 操作人卡号
            dataBehavior.setDbClass(SubLibraryRecentData.M_B_ZX_PRO_DELETE);// 操作行为分类编码
            dataBehavior.setDbMainid(specialProductBo.getSpID());// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
            dataBehavior.setDbContent("专项:产品删除");// 行为内容
            dataBehavior.setDbIP(specialProductBo.getDbIP()); // 操作用户IP
            dataBehaviorDao.insert(dataBehavior);

            long endTime = System.currentTimeMillis();
            logger.debug("专项:产品删除,成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("专项:产品删除,异常 请联系统管理员");
            long endTime = System.currentTimeMillis();
            logger.debug("专项:产品删除,异常!程序运行时间:" + (endTime - startTime) + "ms",e);
        }
        return informationBody;
    }
}
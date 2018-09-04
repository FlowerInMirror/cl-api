/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;
import java.util.UUID;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.dao.*;
import com.thinkgem.jeesite.modules.api.jmat.entity.*;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.PhotoUpload;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.MaterialBO;
import com.thinkgem.jeesite.modules.api.jmat.utils.MaterialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 材料子库Service
 * @author ljc
 * @version 2018-03-16
 */
@Service
@Transactional(readOnly = true)
public class MaterialService extends CrudService<MaterialDao, Material> {

	public Material get(String id) {
		return super.get(id);
	}
	
	public List<Material> findList(Material material) {
		return super.findList(material);
	}
	
	public Page<Material> findPage(Page<Material> page, Material material) {
		return super.findPage(page, material);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(Material material) {
		super.delete(material);
	}


    @Autowired//手动事务控制
    private DataSourceTransactionManager transactionManager;

    /**
     * 业务层对象
     */
    @Autowired // 材料图片
    private TreeStandardPhotoService treeStandardPhotoService;

    /**
     * 数据访问对象
     */
    @Autowired// 信息审核验收表
    private InfocheckandacceptDao infocheckandacceptDao;
    @Autowired// 数据行为表
    private DataBehaviorDao dataBehaviorDao;
    @Autowired // 科目树
    private TreeDao treeDao;
    @Autowired // 材料小样表DAO接口
    private MaterialSmallSampleDao materialSmallSampleDao;


    // 保存材料基本信息分项（品牌|规格）（子库）
    /**
     * @author   ljc
     * @param    materialBO      材料相关信息包装类(更新实体)
     * @return   InformationBody 响应状态信息
     * @createTime   2018-6-22 13:07:52
     */
    public InformationBody saveMateriaByMaterialBO(MaterialBO materialBO) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态


        InformationBody informationBody = new InformationBody();
        try {
            String matID = materialBO.getmID();// 当前操作材料ID
            Integer matLevel = materialBO.getmLevel();// 当前操作材料档次

            // 更新前校验入参是否有效
            if (StringUtils.isBlank(matID)){
                informationBody.setStatusMsg("保存失效！无效的材料");
                logger.warn("材料后台：保存失效！无效的材料");
                return informationBody;
            }

            Material oldMaterial = dao.get(matID);// 开始操作前的材料(作用:更新操作通过该实体与数据库交互)

            // 旧
            String oldMatBrandName = oldMaterial.getMBrandname();// 品牌名称
            String oldMatDescription = oldMaterial.getMMatdescription();// 描述
            String oldMatBrandType = oldMaterial.getMBrandtype(); // 品牌类型
            Double oldMatCostPrice = oldMaterial.getMCostprice(); // 成本
            Double oldMatQuotesPrice = oldMaterial.getMQuotesprice(); // 报价
            Double oldMatInstallPrice = oldMaterial.getMInstallprice(); // 安装价
            Integer oldMatHostState = oldMaterial.getMHoststate(); // 主推
            Integer oldMatType = oldMaterial.getMMattype(); // 类型

            // 新
            String newMatBrandName = materialBO.getmBrandName();// 品牌名称
            String newMatDescription = materialBO.getmMatDescription();// 描述
            String newMatBrandType = materialBO.getmBrandType(); // 品牌类型
            Double newCostPrice = materialBO.getmCostPrice(); // 成本
            Double newQuotesPrice = materialBO.getmQuotesPrice(); // 报价
            Double newInstallPrice = materialBO.getmInstallPrice(); // 安装价
            Integer newMatHostState = materialBO.getmHostState(); // 主推
            Integer newMatType = materialBO.getmMatType(); // 类型



            // 校验保存更新类型
            Integer saveType = materialBO.getSaveType();// 保存类型：1保存品牌，2保存型号

            int numberOfAffectedRows = 0; // 受影响行数

            // 品牌信息
            if (1 == saveType){

                // 校验是否 数据是否改变 否则 不进行保存更新操作 直接返回
                if (newMatBrandName.equals(oldMaterial.getMBrandname()) && newMatDescription.equals(oldMaterial.getMMatdescription())){
                    logger.info("材料后台：保存失效！数据未改变,无需保存.");
                    return informationBody;
                }

                oldMaterial.setMBrandname(newMatBrandName);
                oldMaterial.setMMatdescription(newMatDescription);
                numberOfAffectedRows = dao.saveBrandItemByMaterialEntity(oldMaterial);

            }
            // 型号信息
            else if (2 == saveType) {

                // 设值更新 型号名称/成本/报价/安装/主推/材料类型
                oldMaterial.setMBrandtype(newMatBrandType);
                oldMaterial.setMMattype(newMatType);
                oldMaterial.setMHoststate(newMatHostState);
                numberOfAffectedRows = dao.saveBrandItemByMaterialEntity(oldMaterial);

            }
            // 价格 开通调价功能 2018-9-3 15:35:31 by ljc
            else if (3 == saveType){

                Double newTotalCostPrice = newCostPrice + newInstallPrice; // 总成本

                // 价格校验
                List<MaterialBO> materials = dao.findAllMaterialPriceBeInterrelatedInfoByCityIDAndTreeFourID(materialBO.getmCityID(),materialBO.getmTreeFour()); // 当前规格下不所有档次材料,价格相关数据.
                for (MaterialBO item : materials) {

                    Integer itemMatLevel = item.getmLevel();
                    Double itemCostPrice = item.getmCostPrice();
                    Double itemQuotesPrice = item.getmQuotesPrice();
                    Double itemInstallPrice = item.getmInstallPrice();
                    Double itemTotalCostPrice = itemCostPrice + itemInstallPrice;


                    // 校验 当前档次高 ：价格不能低于低档次价格  低于返回 false
                    if (matLevel < itemMatLevel){
                        if (newQuotesPrice <= itemQuotesPrice){
                            informationBody.setStatusCode(-1);
                            informationBody.setStatusMsg("报价不能" + (newQuotesPrice < itemQuotesPrice ? "低于" : "等于" ) + "低档次的价格 误差值:" + (newQuotesPrice - itemQuotesPrice) + "元");
                            return informationBody;
                        }

                        if (newTotalCostPrice <= itemTotalCostPrice) {
                            informationBody.setStatusCode(-1);
                            informationBody.setStatusMsg("成本价" + (itemInstallPrice > 0 || newInstallPrice > 0 ? "加安装价" : "") + "不能" + ( newTotalCostPrice < itemTotalCostPrice ? "低于" : "等于") + "低档次的价格 误差值:" + (newTotalCostPrice - itemTotalCostPrice) + "元");
                            return informationBody;
                        }
                    }

                    // 校验 当前档次低：价格不能高于高档次价格  高于返回 false
                    if (matLevel > itemMatLevel){
                        if (newQuotesPrice >= itemQuotesPrice){
                            informationBody.setStatusCode(-1);
                            informationBody.setStatusMsg("报价不能"+(newQuotesPrice > itemQuotesPrice ? "高于" : "等于")+"高档次的价格 误差值:" + (newCostPrice - itemQuotesPrice) + "元");
                            return informationBody;
                        }
                        if (newTotalCostPrice >= itemTotalCostPrice){
                            informationBody.setStatusCode(-1);
                            informationBody.setStatusMsg("成本价" + (itemInstallPrice + newInstallPrice > 0 ? "加安装价" : "") + "不能"+(newTotalCostPrice > itemTotalCostPrice ? "高于" : "等于")+"高档次的价格 误差值↑:" + (newTotalCostPrice - itemTotalCostPrice) + "元");
                            return informationBody;
                        }
                    }
                }

                oldMaterial.setMCostprice(newCostPrice);
                oldMaterial.setMQuotesprice(newQuotesPrice);
                oldMaterial.setMInstallprice(newInstallPrice);
                numberOfAffectedRows = dao.saveBrandItemByMaterialEntity(oldMaterial);

            }

            // 校验 更新状态
            if (0 < numberOfAffectedRows){

                // 更新审核信息
                Infocheckandaccept infoCheckAndAccept = new Infocheckandaccept();
                infoCheckAndAccept.setIcaMatid(oldMaterial.getMId());
                infoCheckAndAccept.setIcaTreeid(oldMaterial.getMTreefour());
                infoCheckAndAccept.setIcaCityid(oldMaterial.getMCityid());
                infoCheckAndAccept.setIcaObject(2); // 对象：1一审（照片），2二审（品牌材料），4三审（质量标准），8四审（材料商）
                infoCheckAndAccept.setIcaType(saveType == 1 ? 1 : 2); // 类型：100完结 （集团工程 1品牌、2型号、3约定） （集团主案  1品牌、2主图、10平台-平台、11平台-照片、12平台对比
                infocheckandacceptDao.updateInfoCheckAndAcceptStateToReset(infoCheckAndAccept);

                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(materialBO.getUserNo());
                dataBehavior.setDbMainid(materialBO.getmID());

                StringBuilder brandChengSB = new StringBuilder();

                // 品牌信息变更
                if (saveType == 1){

                    if (!newMatBrandName.equals(oldMaterial.getMBrandname()))
                        brandChengSB.append("品牌(").append(oldMaterial.getMBrandname()).append("改为").append(newMatBrandName).append(");");
                    if (null != newMatBrandType && !newMatBrandType.equals(oldMaterial.getMBrandtype()))
                        brandChengSB.append("型号(").append(oldMaterial.getMBrandtype()).append("改为").append(newMatBrandType).append(");");

                } else if (saveType == 2){

                    if (!newMatHostState.equals(oldMaterial.getMHoststate()))
                        brandChengSB.append("改为").append(newMatHostState == 1 ? "主推" : "非主推").append(";");
                    if (!newMatType.equals(oldMaterial.getMMattype()))
                        brandChengSB.append("改为").append(newMatType == 1 ? "品牌材料" : "瑞祥专供材料").append(";");

                } else if (saveType == 3){
                    if (newCostPrice!= null && !newCostPrice.equals(oldMatCostPrice))
                        brandChengSB.append("成本价").append(oldMatCostPrice).append("元,调整为").append(newCostPrice+"元;");
                    if (newQuotesPrice!= null && !newQuotesPrice.equals(oldMatQuotesPrice))
                        brandChengSB.append("报价").append(oldMatQuotesPrice).append("元,调整为").append(newQuotesPrice+"元;");
                    if (newInstallPrice!= null && !newInstallPrice.equals(oldMatInstallPrice))
                        brandChengSB.append("安装价").append(oldMatInstallPrice).append("元,调整为").append(newInstallPrice+"元;");
                }

                //保存品牌基本信息 更新记录
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_SAVE);
                dataBehavior.setDbContent(brandChengSB.toString());
                dataBehaviorDao.insert(dataBehavior);

                logger.info("子库管理：保存材料基本信息。材料ID："+matID);
            }
            transactionManager.commit(status); //提交
        } catch (Exception e) {
            transactionManager.rollback(status);  //回滚
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("子库管理：保存材料基本信息失败!");
            logger.error("子库管理：保存材料基本信息失败!", e);
        }
        return informationBody;
    }

    // 添加品牌（子库>地区>档次）
    /**
     * @author   ljc
     * @param    materialBO      材料相关信息包装类(更新实体)
     * @return   InformationBody 响应状态信息
     * @createTime  2018-6-22 13:07:52
     * @updateTime  2018-8-3 15:29:27   全部材料价格校验 修改 入库材料价格校验
     */
    public InformationBody addMateriaByMaterialBO(MaterialBO materialBO) {
        // 手动事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        InformationBody informationBody = new InformationBody();
        try {
            // 安全判断
            if (null == materialBO || StringUtils.isBlank(materialBO.getmTreeFour())){
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("添加品牌失效！无效的信息!");
                return informationBody;
            }


            // 添加品牌项校验价格

            Integer newMatLevel = materialBO.getmLevel(); // 档次
            Double newCostPrice = materialBO.getmCostPrice(); // 成本价
            Double newQuotesPrice = materialBO.getmQuotesPrice(); // 报价
            Double newInstallPrice = materialBO.getmInstallPrice(); // 安装价
            Double newTotalCostPrice = newCostPrice + newInstallPrice; // 总成本

            // 获取 当前规格下不所有档次材料,价格相关数据.
            List<MaterialBO> materials = dao.findAllMaterialPriceBeInterrelatedInfoByCityIDAndTreeFourID(materialBO.getmCityID(),materialBO.getmTreeFour());
            for (MaterialBO item : materials) {

                Integer itemMatLevel = item.getmLevel();
                Double itemCostPrice = item.getmCostPrice();
                Double itemQuotesPrice = item.getmQuotesPrice();
                Double itemInstallPrice = item.getmInstallPrice();
                Double itemTotalCostPrice = itemCostPrice + itemInstallPrice;


                // 校验 当前档次高 ：价格不能低于低档次价格  低于返回 false
                if (newMatLevel < itemMatLevel){
                    if (newQuotesPrice <= itemQuotesPrice){
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("报价价不能" + (newQuotesPrice < itemQuotesPrice ? "低于" : "等于" ) + "低档次的价格 误差值:" + (newQuotesPrice - itemQuotesPrice) + "元");
                        return informationBody;
                    }

                    if (newTotalCostPrice <= itemTotalCostPrice) {
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("成本价" + (itemInstallPrice > 0 || newInstallPrice > 0 ? "加安装价" : "") + "不能" + ( newTotalCostPrice < itemTotalCostPrice ? "低于" : "等于") + "低档次的价格 误差值:" + (newTotalCostPrice - itemTotalCostPrice) + "元");
                        return informationBody;
                    }
                }

                // 校验 当前档次低：价格不能高于高档次价格  高于返回 false
                if (newMatLevel > itemMatLevel){
                    if (newQuotesPrice >= itemQuotesPrice){
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("报价价不能"+(newQuotesPrice > itemQuotesPrice ? "高于" : "等于")+"高档次的价格 误差值:" + (newQuotesPrice - itemQuotesPrice) + "元");
                        return informationBody;
                    }
                    if (newTotalCostPrice >= itemTotalCostPrice){
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("成本价" + (itemInstallPrice + newInstallPrice > 0 ? "加安装价" : "") + "不能"+(newTotalCostPrice > itemTotalCostPrice ? "高于" : "等于")+"高档次的价格 误差值↑:" + (newTotalCostPrice - itemTotalCostPrice) + "元");
                        return informationBody;
                    }
                }
            }


            // 组织设值材料编码
            String cityCode = materialBO.getCityCode(); // 城市编码
            Integer levelFlag = materialBO.getmLevel(); // 材料档次标识
            String LevelCharacter = MaterialUtils.levelFlagChangerLevelCharacter(levelFlag); // 档次字母
            String treeFourID = materialBO.getmTreeFour(); // 四级科目ID
            if (StringUtils.isBlank(cityCode) || StringUtils.isBlank(LevelCharacter) || StringUtils.isBlank(treeFourID)){
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("添加品牌失效！无法生成材料编号");
                return informationBody;
            }
            String matCodeTail = LevelCharacter + "|" + cityCode; // 材料编码尾部部分
            String matCode = dao.getNewMatCodeByTreeFourIDAndMatCodeTail(treeFourID,matCodeTail);
            materialBO.setMatCode(matCode);
            String newMatID = UUID.randomUUID().toString(); // 新材料ID
            materialBO.setmID(newMatID);

            // 添加
            int affectedRowNumber = dao.addBrandItem(materialBO); // 插入受影响行数

            // 插入图片
            List<PhotoUpload> matPhotoItems = materialBO.getPhotoItems(); // 材料相片集
            for (PhotoUpload matPhotoItem : matPhotoItems) { // 组织数据执行插入操作
                matPhotoItem.setTspTSID(UUID.randomUUID().toString());
                matPhotoItem.setTspTSID(newMatID);
                matPhotoItem.setTspTreeFour(treeFourID);
                matPhotoItem.setTspClass(2); // 分类：1母库规格，2子库材料
                matPhotoItem.setTspMatLevel(levelFlag);
                treeStandardPhotoService.addMaterialPhotoByPhotoUpload(matPhotoItem);
            }

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(materialBO.getUserNo());
            dataBehavior.setDbMainid(newMatID);
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_ADD);
            dataBehavior.setDbContent("地方完善"+materialBO.getmBrandName()+"品牌信息，提交集团审核");
            dataBehaviorDao.insert(dataBehavior);

            // 更新审核信息 -- 新加品牌
            Infocheckandaccept infoCheckAndAccept = new Infocheckandaccept();
            infoCheckAndAccept.setIcaTreeid(treeFourID);
            infoCheckAndAccept.setIcaCityid(materialBO.getmCityID());
            infocheckandacceptDao.updateInfoCheckToResetToAddNewBrand(infoCheckAndAccept); //子库：新加品牌重新开始（审核重置）

            transactionManager.commit(status); //提交
        } catch (Exception e) {
            transactionManager.rollback(status);  //回滚
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("添加品牌（子库>地区>档次）失败!");
            logger.error("添加品牌（子库>地区>档次）失败!", e);
        }
        return informationBody;
    }

    // 删除品牌项(子库>地区>档次)
    /**
     * @author  ljc
     * @param   materialBO      材料相关信息业务对象(数据更新实体)
     * @return  InformationBody 响应信息实体
     * @createTime 2018-6-27 15:19:16
     */
    public InformationBody deleteBrandItem(MaterialBO materialBO) {
        InformationBody informationBody = new InformationBody();
        String matID = materialBO.getmID(); // 材料ID
        try {
            // 安全校验
            if (StringUtils.isBlank(matID)){
                informationBody.setStatusMsg("error(status code 400): entry parameter not found.");
                logger.warn("error(status code 400): entry parameter not found.");
                return informationBody;
            }

            // 删除
            int numberOfAffectedRows = dao.deleteBrandItem(materialBO);
            if (-1 == numberOfAffectedRows){
                informationBody.setStatusMsg("项目使用过此材料，无法删除.");
                logger.warn("子库后台:删除材料失败,项目使用过此材料，无法删除");
                return informationBody;
            };
            if (0 < numberOfAffectedRows){
                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(materialBO.getUserNo());
                dataBehavior.setDbMainid(matID);
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_SAVE);
                dataBehavior.setDbContent("材料删除");
                dataBehaviorDao.insert(dataBehavior);
                logger.info("子库：删除材料。材料ID：" + matID);
            }

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("材料删除失败!材料ID："+matID);
            logger.error("材料删除失败!材料ID："+matID, e);
        }
        return informationBody;
    }

    // 入库品牌（子库>地区>档次）
    /**
     * @author   ljc
     * @param    materialBO      材料相关信息包装类(更新实体)
     * @return   InformationBody 响应状态信息
     * @createTime 2018-6-29 11:34:57
     * @updateTime 2018-8-3  16:38:29  更新内容:入库前 加入 价格校验(不能高于高档价格/不能低于低档价格)
     */
    public InformationBody intoBrandItem(MaterialBO materialBO) {
        InformationBody informationBody = new InformationBody();
        String matID = materialBO.getmID(); // 材料ID

        try {
            // 入参 安全校验
            if (StringUtils.isBlank(matID)){
                informationBody.setStatusMsg("error(status code 400): entry parameter not found.");
                logger.warn("error(status code 400): entry parameter not found.");
                return informationBody;
            }

            // 入库前 价格校验
            Integer newMatLevel = materialBO.getmLevel(); // 档次
            Double newCostPrice = materialBO.getmCostPrice(); // 成本价
            Double newQuotesPrice = materialBO.getmQuotesPrice(); // 报价
            Double newInstallPrice = materialBO.getmInstallPrice(); // 安装价
            Double newTotalCostPrice = newCostPrice + newInstallPrice; // 总成本

            // 1.获取 当前规格下所有档次材料价格相关数据.
            List<MaterialBO> materials = dao.findAllMaterialPriceBeInterrelatedInfoByCityIDAndTreeFourID(materialBO.getmCityID(),materialBO.getmTreeFour());

            for (MaterialBO item : materials) {

                Integer itemMatLevel = item.getmLevel();
                Double itemCostPrice = item.getmCostPrice();
                Double itemQuotesPrice = item.getmQuotesPrice();
                Double itemInstallPrice = item.getmInstallPrice();
                Double itemTotalCostPrice = itemCostPrice + itemInstallPrice;

                // 2.校验(当前档次 高与 校验档次,开始与低档价验证:价格不能低于等于 低档材料)
                if (newMatLevel < itemMatLevel){

                    // 报价 低于等于低档材料时 执行
                    if (newQuotesPrice <= itemQuotesPrice){
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("入库失败:报价价不能" + (newQuotesPrice < itemQuotesPrice ? "'低于'" : "'等于'" ) + "低档次的价格 误差值:" + (newQuotesPrice - itemQuotesPrice) + "元");
                        return informationBody;
                    }
                    // 成本 低于等于低档材料时 执行
                    if (newTotalCostPrice <= itemTotalCostPrice) {
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("入库失败:成本价" + (itemInstallPrice > 0 || newInstallPrice > 0 ? "加安装价" : "") + "不能" + ( newTotalCostPrice < itemTotalCostPrice ? "'低于'" : "'等于'") + "低档次的价格 误差值:" + (newTotalCostPrice - itemTotalCostPrice) + "元");
                        return informationBody;
                    }
                }

                // 3.校验(当前档次 低与 校验档次,开始与高档价验证:价格不能高于等于 高档材料)
                if (newMatLevel > itemMatLevel){
                    if (newQuotesPrice >= itemQuotesPrice){
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("入库失败:报价价不能"+(newQuotesPrice > itemQuotesPrice ? "'高于'" : "'等于'")+"高档次的价格 误差值:" + (newQuotesPrice - itemQuotesPrice) + "元");
                        return informationBody;
                    }
                    if (newTotalCostPrice >= itemTotalCostPrice){
                        informationBody.setStatusCode(-1);
                        informationBody.setStatusMsg("入库失败:成本价" + (itemInstallPrice + newInstallPrice > 0 ? "加安装价" : "") + "不能"+(newTotalCostPrice > itemTotalCostPrice ? "'高于'" : "'等于'")+"高档次的价格 误差值↑:" + (newTotalCostPrice - itemTotalCostPrice) + "元");
                        return informationBody;
                    }
                }
            }


            // 验证通过 执行入库
            int numberOfAffectedRows = dao.intoOrAuditingBrandItem(materialBO);
            if (0 < numberOfAffectedRows){
                // 保存数据行为(操作人(卡号)/操作人类型/行为分类/操作主ID/行为内容)
                dataBehaviorDao.insert(new DataBehavior(materialBO.getUserNo(),6,SubLibraryRecentData.M_A_SL_BRAND_ADD,matID,"品牌入库"));
                logger.info("子库：入库品牌成功。材料ID：" + matID);

                // 更新审核信息
                Infocheckandaccept infoCheckAndAccept = new Infocheckandaccept();
                infoCheckAndAccept.setIcaMatid(matID);
                infocheckandacceptDao.updateInfoCheckAndAcceptStateToResetMat(infoCheckAndAccept);
            }

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("材料入库失败,请联系管理员.材料ID："+matID);
            logger.error("材料入库失败!材料ID："+matID, e);
        }
        return informationBody;
    }

    // 更新小样类型
    /**
     * @author   ljc
     * @param    matID      材料ID
     * @param    sampleType 小样类型
     * @param    userCard   用户卡号
     * @return   InformationBody 响应状态信息
     * @createTime  2018-7-19 18:29:49
     */
    public InformationBody updateSampleType(String matID, Integer sampleType,String userCard) {
        InformationBody informationBody = new InformationBody();
        try {
            if (StringUtils.isBlank(matID)){
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("error:400,请检查入参matID");
                return informationBody;
            }
            int updateSampleTypeResult = dao.updateSampleType(new MaterialBO(matID,sampleType));

            // 保存数据行为 -- 在小样状态被更新时执行
            if (updateSampleTypeResult != 0 ){
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(userCard);
                dataBehavior.setDbMainid(matID);
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_SAVE);
                dataBehavior.setDbContent("更新小样类型:" + (sampleType == 1 ? "需要" : "不需要"));
                dataBehaviorDao.insert(dataBehavior);
            }

            logger.debug("子库:更新小样状态成功,matID:"+matID);
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("材料入库失败,请联系管理员.材料ID：" + matID + ",类型修改为"+ (sampleType == 1 ? "需要" : "不需要"));
            logger.error("子库:更新小样状态失败,matID:"+matID, e);
        }
        return informationBody;

    }

    //  预操作:预下架与取消预下架(mShoppingState 类型:0取消预下架/2预下架) @author ljc @createTiem 2018-9-3 18:30:06
    public InformationBody matPreOperationByMaterialBO(MaterialBO materialBO) {
        InformationBody informationBody = new InformationBody();
        String matID = materialBO.getmID(); // 材料ID
        String userCard = materialBO.getUserNo(); // 用户卡号
        Integer mShoppingState = materialBO.getmShoppingState();
        try {

            if (StringUtils.isBlank(matID)){
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("error:400,请检查入参matID");
                return informationBody;
            }

            int updateSampleTypeResult = dao.matPreOperationByMaterialBO(materialBO);

            // 保存数据行为 -- 在小样状b态被更新时执行
            if (updateSampleTypeResult != 0 ){
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(userCard);
                dataBehavior.setDbMainid(matID);
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_SAVE);
                dataBehavior.setDbContent("预操作:" + (mShoppingState == 2 ? "预下架" : "取消预下架"));
                dataBehaviorDao.insert(dataBehavior);
            }

            logger.debug("子库:预处理成功,matID:"+matID);
        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("材料预处理失败,请联系管理员.材料ID：" + matID + ",预处理"+ (mShoppingState == 2 ? "预下架" : "取消预下架"));
            logger.error("子库:预处理失败,matID:"+matID, e);
        }
        return informationBody;
    }
}
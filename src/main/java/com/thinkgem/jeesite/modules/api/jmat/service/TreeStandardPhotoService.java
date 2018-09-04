/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.UUID;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.dao.*;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.entity.Infocheckandaccept;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialComparedAttributes;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.PhotoUpload;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeStandardPhoto;

/**
 * 材料母库照片Service
 * @author ljc
 * @version 2018-04-12
 */
@Service
@Transactional(readOnly = true)
public class TreeStandardPhotoService extends CrudService<TreeStandardPhotoDao, TreeStandardPhoto> {

    /**
     * 数据访问对象
     */
    @Autowired// 数据行为表
    private DataBehaviorDao dataBehaviorDao;
    @Autowired// 信息审核验收表
    private InfocheckandacceptDao infocheckandacceptDao;
    @Autowired// 属性对比（对比图）
    private MaterialComparedAttributesDao materialComparedAttributesDao;
    @Autowired// 材料表关联性汇总表
    private TabkeRelationDao tabkeRelationDao;

    // 添加材料照片
    /**
     * @author      ljc
     * @param       photoUpload      图片上传实体
     * @return      InformationBody  响应体
     * @createTime  2018-6-15 16:45:40
     */
    public InformationBody addMaterialPhotoByPhotoUpload(PhotoUpload photoUpload) {
        InformationBody informationBody = new InformationBody();
        try {
            String newTspID = UUID.randomUUID().toString();// 新的材料照片ID
            String tspPhotoURL = photoUpload.getTspPhotoURL();
            // 校验上传照片是否有效
            if (photoUpload == null ||(photoUpload.getTspType() != 2 && StringUtils.isBlank(tspPhotoURL)) ){
                informationBody.setStatusMsg("上传照片失效！无效的照片");
                logger.error("材料后台：上传照片失效！无效的照片");
                return informationBody;
            }

            // 设值图片类型：母库|子库
            if (photoUpload.getTspClass() == 0){
                switch (photoUpload.getTspType()){
                    case 1: case 2: photoUpload.setTspClass(2);break;
                    default:photoUpload.setTspClass(1);break;
                }
            }

            // 保存材料照片
            photoUpload.setTspID(newTspID);

            int numberOfAffectedRows = dao.addMaterialPhotoByPhotoUpload(photoUpload);// 插入后的受影响行数

            // 校验受影响行数:true 更新审核信息,保存数据行为/fasle 返回状态信息,打印日志.
            if (0 < numberOfAffectedRows){
                // 更新审核信息
                // 组织 信息审核验收实体,进行更新操作.
                Infocheckandaccept infoCheckAndAccept = new Infocheckandaccept();
                infoCheckAndAccept.setIcaTreeid(photoUpload.getTspTreeFour());
                infoCheckAndAccept.setIcaCityid(photoUpload.getTspCityID());
                // 上传照片类型为  2子库材料,设值材料ID到审核信息实体中
                if (2 == photoUpload.getTspClass())
                    infoCheckAndAccept.setIcaMatid(photoUpload.getTspTSID());
                // 校验图片类型 设值审核对象与类型
                // 审核对象ica_Object：1一审（照片），2二审（品牌材料），4三审（质量标准），8四审（材料商）
                // 审核类型ica_Type：100完结 （集团工程 1品牌、2型号、3约定） （集团主案  1品牌、2主图、10平台-平台、11平台-照片、12平台对比）
                // 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                switch (photoUpload.getTspType()){
                    case 1://一审(品牌)
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(1);//材料
                        break;
                    case 4://一审（主图）
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(2);//材料
                        //不考虑材料
                        break;
                    case 3://一审（平台-对比）
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(12);//平台-对比
                        // 获取对比KEY
                        // ica_MatID 材料ID|对比项ID|照片自定模块ID
                        // 通过对比ID获取对比KEY infoM.tsp_ParaID
                        MaterialComparedAttributes mca = materialComparedAttributesDao.get(photoUpload.getTspParaID().toString());
                        if (mca != null)
                            infoCheckAndAccept.setIcaMatid(mca.getMcaKey());
                        break;
                    case 10://一审（平台-照片）
                        infoCheckAndAccept.setIcaObject(1) ;
                        infoCheckAndAccept.setIcaType(11);//平台-照片
                        // 获取自定照片板块ID
                        infoCheckAndAccept.setIcaMatid(tabkeRelationDao.findIDByTreeFourIDAndBrandID(photoUpload.getTspTreeFour(), photoUpload.getTspBrandID()));
                        break;
                    case 2://二审（材料-型号）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(2);//材料-型号
                        break;
                    case 11://二审（材料约定）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(3);//材料约定
                        break;
                    default:
                        break;
                }
                // 重置审核验收状态未重新开始（审核过的重置）
                infocheckandacceptDao.updateInfoCheckAndAcceptStateToReset(infoCheckAndAccept);

                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(photoUpload.getUserNo());
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_PHOTO);
                dataBehavior.setDbMainid(newTspID);
                dataBehavior.setDbEventid(newTspID);// 事件ID
                dataBehavior.setDbContent("集团添加材料照片");
                dataBehavior.setDbData("tspID:"+photoUpload.getTspID()+",newUrl:"+photoUpload.getTspPhotoURL()+",oType:1");
                dataBehaviorDao.insert(dataBehavior);

                informationBody.setStatusMsg("ok" + newTspID);
                logger.info("上传照片成功！"+newTspID);
            }else {
                informationBody.setStatusMsg("上传照片失败！");
                logger.error("上传照片失败！");
            }

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("添加材料照片失败!");
            logger.error("添加材料照片!", e);
        }
        return informationBody;
    }

    // 更新材料照片
    /**
     * @author       ljc
     * @createTime   2018-6-16 14:08:57
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    public InformationBody updateMaterialPhotoByPhotoUpload(PhotoUpload photoUpload) {
        InformationBody informationBody = new InformationBody();
        try {

            // 校验上传照片是否有效
            if (photoUpload == null ||photoUpload.getTspID() == null || StringUtils.isBlank(photoUpload.getTspPhotoURL())){
                informationBody.setStatusMsg("更新照片失效！无效的照片");
                logger.error("材料后台：更新照片失效！无效的照片");
                return informationBody;
            }
            // 获取 更新'前'照片实体
            TreeStandardPhoto oldTreeStandardPhoto = dao.get(photoUpload.getTspID());
            // 更新
            int numberOfAffectedRows =  dao.updateTreeStandardPhotoURLByPhotoUpload(photoUpload);// 更新受影响行数
            // 校验受影响行数
            if (0 < numberOfAffectedRows){
                // 更新审核信息
                // 组织 信息审核验收实体,进行更新操作.
                Infocheckandaccept infoCheckAndAccept = new Infocheckandaccept();
                infoCheckAndAccept.setIcaTreeid(photoUpload.getTspTreeFour());
                infoCheckAndAccept.setIcaCityid(photoUpload.getTspCityID());

                // 上传照片类型为  2子库材料,设值材料ID到审核信息实体中
                if (2 == photoUpload.getTspClass())
                    infoCheckAndAccept.setIcaMatid(photoUpload.getTspTSID());

                // 校验图片类型 设值审核对象与类型
                // 审核对象ica_Object：1一审（照片），2二审（品牌材料），4三审（质量标准），8四审（材料商）
                // 审核类型ica_Type：100完结 （集团工程 1品牌、2型号、3约定） （集团主案  1品牌、2主图、10平台-平台、11平台-照片、12平台对比）
                // 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                switch (photoUpload.getTspType()){
                    case 1://一审(品牌)
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(1);//材料
                        break;
                    case 4://一审（主图）
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(2);//材料
                        //不考虑材料
                        break;
                    case 3://一审（平台-对比）
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(12);//平台-对比
                        // 获取对比KEY
                        // ica_MatID 材料ID|对比项ID|照片自定模块ID
                        // 通过对比ID获取对比KEY infoM.tsp_ParaID
                        MaterialComparedAttributes mca = materialComparedAttributesDao.get(oldTreeStandardPhoto.getTspParaid().toString());
                        if (mca != null)
                            infoCheckAndAccept.setIcaMatid(mca.getMcaKey());
                        break;
                    case 10://一审（平台-照片）
                        infoCheckAndAccept.setIcaObject(1) ;
                        infoCheckAndAccept.setIcaType(11);//平台-照片
                        // 获取自定照片板块ID
                        infoCheckAndAccept.setIcaMatid(tabkeRelationDao.findIDByTreeFourIDAndBrandID(oldTreeStandardPhoto.getTspTreefour(), oldTreeStandardPhoto.getTspBrandid()));
                        break;
                    case 2://二审（材料-型号）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(2);//材料-型号
                        break;
                    case 11://二审（材料约定）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(3);//材料约定
                        break;
                    default:
                        break;
                }
                // 重置审核验收状态未重新开始（审核过的重置）
                infocheckandacceptDao.updateInfoCheckAndAcceptStateToReset(infoCheckAndAccept);

                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(photoUpload.getUserNo());
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_PHOTO);
                dataBehavior.setDbMainid(oldTreeStandardPhoto.getTspId());
                dataBehavior.setDbEventid(oldTreeStandardPhoto.getTspId());// 事件ID
                dataBehavior.setDbContent("集团更新材料照片");
                dataBehavior.setDbData("tspID:"+photoUpload.getTspID()+"oldUrl:"+oldTreeStandardPhoto.getTspPhotourl()+",newUrl:"+photoUpload.getTspPhotoURL()+",oType:2");
                dataBehaviorDao.insert(dataBehavior);
                logger.info("材料后台：更新照片成功。照片ID："+oldTreeStandardPhoto.getTspId());
            }else {
                informationBody.setStatusMsg("更新上传材料照片失败！");
                logger.error("更新上传照片失败！");
            }

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("更新材料照片失败!");
            logger.error("更新材料照片!", e);
        }
        return informationBody;
    }

    // 更新材料照片参数/描述
    /**
     * @author       ljc
     * @createTime   2018-6-16 15:32:07
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    public InformationBody updateMaterialPhotoOterText(PhotoUpload photoUpload) {
        InformationBody informationBody = new InformationBody();
        try {
            // 校验上传照片是否有效
            if (photoUpload == null ||photoUpload.getTspID() == null ){
                informationBody.setStatusMsg("保存失效！无效的照片信息");
                logger.error("材料后台：保存失效！无效的照片信息");
                return informationBody;
            }
            // 获取照片实体
            TreeStandardPhoto oldTreeStandardPhoto = dao.get(photoUpload.getTspID());
            // 品牌型号设值 内容2
            if (2 == oldTreeStandardPhoto.getTspType()){
                photoUpload.setTspContent2(oldTreeStandardPhoto.getTspContent2());
            }
            // 更新 材料照片附加信息
            int numberOfAffectedRows = dao.updateMaterialPhotoOterTextByPhotoUpload(photoUpload);

            // 保存数据行为/更新审核信息
            if (0 < numberOfAffectedRows){
                // 更新审核信息
                // 组织 信息审核验收实体,进行更新操作.
                Infocheckandaccept infoCheckAndAccept = new Infocheckandaccept();
                infoCheckAndAccept.setIcaTreeid(photoUpload.getTspTreeFour());
                infoCheckAndAccept.setIcaCityid(photoUpload.getTspCityID());

                // 上传照片类型为  2子库材料,设值材料ID到审核信息实体中
                if (2 == photoUpload.getTspClass())
                    infoCheckAndAccept.setIcaMatid(photoUpload.getTspTSID());

                // 校验图片类型 设值审核对象与类型
                // 审核对象ica_Object：1一审（照片），2二审（品牌材料），4三审（质量标准），8四审（材料商）
                // 审核类型ica_Type：100完结 （集团工程 1品牌、2型号、3约定） （集团主案  1品牌、2主图、10平台-平台、11平台-照片、12平台对比）
                // 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                switch (photoUpload.getTspType()){
                    case 1://一审(品牌)
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(1);//材料
                        break;
                    case 4://一审（主图）
                        infoCheckAndAccept.setIcaObject(1);
                        if (2 == oldTreeStandardPhoto.getTspClass())
                            infoCheckAndAccept.setIcaType(1);//材料
                        else
                            infoCheckAndAccept.setIcaType(11);//平台-照片
                        break;
                    case 3://一审（平台-对比）
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(12);//平台-对比
                        break;
                    case 10://一审（平台-照片）
                        infoCheckAndAccept.setIcaObject(1) ;
                        infoCheckAndAccept.setIcaType(11);//平台-照片
                        break;
                    case 2://二审（材料-型号）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(2);//材料-型号
                        break;
                    case 11://二审（材料约定）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(3);//材料约定
                        break;
                    default:
                        break;
                }
                // 重置审核验收状态未重新开始（审核过的重置）
                infocheckandacceptDao.updateInfoCheckAndAcceptStateToReset(infoCheckAndAccept);

                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(photoUpload.getUserNo());
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_PHOTO);
                dataBehavior.setDbMainid(oldTreeStandardPhoto.getTspId());
                dataBehavior.setDbEventid(oldTreeStandardPhoto.getTspId());
                dataBehavior.setDbContent("集团保存照片附加信息");
                dataBehavior.setDbData("tspID:"+photoUpload.getTspID()+",oldValue1:"+oldTreeStandardPhoto.getTspTitle()+",oldValue2:"+oldTreeStandardPhoto.getTspContent1()+",oldValue3:"+oldTreeStandardPhoto.getTspContent2()+",newValue1:"+photoUpload.getTspTitle()+",newValue2:"+photoUpload.getTspContent1()+",newValue3:"+photoUpload.getTspContent2());
                dataBehaviorDao.insert(dataBehavior);
                logger.info("材料后台：保存照片附加信息成功。照片ID："+oldTreeStandardPhoto.getTspId());
            }else {
                informationBody.setStatusMsg("保存照片附加信息失败！");
                logger.error("保存照片附加信息失败！");
            }

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("保存照片附加信息失败!");
            logger.error("保存照片附加信息失败!", e);
        }
        return informationBody;
    }

    //  删除材料照片
    /**
     * @author       ljc
     * @createTime   2018-6-22 09:43:39
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    public InformationBody deleteMaterialPhotoByPhotoUpload(PhotoUpload photoUpload) {
        InformationBody informationBody = new InformationBody();
        try {
            // 校验删除照片入参是否有效
            if (StringUtils.isBlank(photoUpload.getTspID())){
                informationBody.setStatusMsg("删除照片失效！删除的照片");
                logger.error("材料后台：删除照片失效！无效的照片");
                return informationBody;
            }

            // 删除(更新状态)
            int numberOfAffectedRows =  dao.deleteTreeStandardPhotoURLByPhotoUpload(photoUpload);// 删除受影响行数

            // 校验受影响行数
            if (0 < numberOfAffectedRows){// 删除成功
                TreeStandardPhoto treeStandardPhoto = dao.get(photoUpload.getTspID());// 照片实体

                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);
                dataBehavior.setDbOperatuser(photoUpload.getUserNo());
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_SL_BRAND_PHOTO);
                dataBehavior.setDbMainid(treeStandardPhoto.getTspId());
                dataBehavior.setDbEventid(treeStandardPhoto.getTspId());// 事件ID
                dataBehavior.setDbContent("集团删除材料照片");
                dataBehavior.setDbData("tspID:"+photoUpload.getTspID() + ",newUrl:"+ photoUpload.getTspPhotoURL() +",oType:3");
                dataBehaviorDao.insert(dataBehavior);

                // 更新审核信息
                Infocheckandaccept infoCheckAndAccept = new Infocheckandaccept();// 组织 信息审核验收实体,进行更新操作.
                infoCheckAndAccept.setIcaTreeid(photoUpload.getTspTreeFour());
                infoCheckAndAccept.setIcaCityid(photoUpload.getTspCityID());

                if (2 == photoUpload.getTspClass())           // 上传照片类型为  2子库材料,设值材料ID到审核信息实体中
                    infoCheckAndAccept.setIcaMatid(photoUpload.getTspTSID());

                // 校验图片类型 设值审核对象与类型
                // 审核对象ica_Object：1一审（照片），2二审（品牌材料），4三审（质量标准），8四审（材料商）
                // 审核类型ica_Type：100完结 （集团工程 1品牌、2型号、3约定） （集团主案  1品牌、2主图、10平台-平台、11平台-照片、12平台对比）
                // 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺；10照片展示自定义类，11材料约定
                switch (photoUpload.getTspType()){
                    case 1://一审(品牌)
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(1);//材料
                        break;
                    case 4://一审（主图）
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(2);//材料
                        //不考虑材料
                        break;
                    case 3://一审（平台-对比）
                        infoCheckAndAccept.setIcaObject(1);
                        infoCheckAndAccept.setIcaType(12);//平台-对比
                        // 获取对比KEY
                        // ica_MatID 材料ID|对比项ID|照片自定模块ID
                        // 通过对比ID获取对比KEY infoM.tsp_ParaID
                        MaterialComparedAttributes mca = materialComparedAttributesDao.get(treeStandardPhoto.getTspParaid().toString());
                        if (mca != null)
                            infoCheckAndAccept.setIcaMatid(mca.getMcaKey());
                        break;
                    case 10://一审（平台-照片）
                        infoCheckAndAccept.setIcaObject(1) ;
                        infoCheckAndAccept.setIcaType(11);//平台-照片
                        // 获取自定照片板块ID
                        infoCheckAndAccept.setIcaMatid(tabkeRelationDao.findIDByTreeFourIDAndBrandID(treeStandardPhoto.getTspTreefour(), treeStandardPhoto.getTspBrandid()));
                        break;
                    case 2://二审（材料-型号）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(2);//材料-型号
                        break;
                    case 11://二审（材料约定）
                        infoCheckAndAccept.setIcaObject(2);
                        infoCheckAndAccept.setIcaType(3);//材料约定
                        break;
                    default:
                        break;
                }
                // 重置审核验收状态未重新开始（审核过的重置）
                infocheckandacceptDao.updateInfoCheckAndAcceptStateToReset(infoCheckAndAccept);
                logger.info("材料后台：删除照片成功。照片ID："+treeStandardPhoto.getTspId());

            }else {
                informationBody.setStatusMsg("删除上传材料照片失败！");
                logger.error("删除上传照片失败！");
            }

        } catch (Exception e) {
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("删除材料照片错误!");
            logger.error("删除材料照片!", e);
        }
        return informationBody;
    }
}
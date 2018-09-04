/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;

import com.thinkgem.jeesite.modules.api.jmat.dao.*;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.VisitVo;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.status.MatHandleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialHandle;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 材料处理表Service
 * @author ljc
 * @version 2018-07-26
 */
@Service
@Transactional(readOnly = true)
public class MaterialHandleService extends CrudService<MaterialHandleDao, MaterialHandle> {

    // --- 数据访问对象 ---
    @Autowired
    private TreeDao treeDao; // 科目树
    @Autowired
    private VisitDao visitDao; // 回访记录
    @Autowired
    private ProInfoMaterialDao proInfoMaterialDao; // 项目材料
    @Autowired
    private MaterialPerfectStateDao materialPerfectStateDao; //材料子库完善状态表 (规格地区保存)
    @Autowired
    private MaterialDao materialDao; // 材料子库
    @Autowired
    private TreeStandardPhotoDao treeStandardPhotoDao; // 材料母库照片
    @Autowired
    private MaterialToSuppliersDao materialToSuppliersDao; // 材料商材料关联表 (供货商相关)
    @Autowired
    private DataBehaviorDao dataBehaviorDao; // 数据行为


    // --- 功能对象 ---
    @Autowired
    private DataSourceTransactionManager transactionManager; // 手动事务控制

    // 获取 材料处理实体
	public MaterialHandle get(String id) {
		return super.get(id);
	}

	// 查找 材料处理实体集
	public List<MaterialHandle> findList(MaterialHandle materialHandle) {
		return super.findList(materialHandle);
	}

    /**
     * 材料处理:添加处理
     * @author ljc
     * @param  matHandleVo      处理记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-26 13:57:18
     */
    @Transactional(readOnly = false)
    public InformationBody saveMatHandle(MatHandleVo matHandleVo) {
        long startTime = System.currentTimeMillis();
        InformationBody informationBody = new InformationBody();

        // --- 手动事务控制相关 ---
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            // --- 赋值调用 ---
            Integer vmhType = matHandleVo.getVmhType(); // 处理类型：1正常，2问题，3异常,4.下架
            Integer vmhCityid = matHandleVo.getVmhCityid(); // 处理城市ID
            String vmhContent = matHandleVo.getVmhContent(); // 处理内容
            String vmhMainobject = matHandleVo.getVmhMainobject(); // 处理主对象
            String vmhUsercard = matHandleVo.getVmhUsercard(); // 处理人卡号
            Integer vmhUsertype = matHandleVo.getVmhUsertype(); // 处理人类型：1集团，2地方
            Integer visitType = matHandleVo.getVisitType(); // 回访类型:回访类型：1项目，2材料商，3配送单,100材料子库,200集团主案

            // 1.添加 处理记录
            int influenceNumber  = dao.saveMatHandle(matHandleVo);

            // 2.判断 受影响行数是否继续执行
            if (0 != influenceNumber){

                // 3.安全判断
                if (null != vmhType){

                    // 4.判断 处理类型执行不同类型操作
                    // 类型4:下架
                    if (vmhType.equals(4)){
                        // 1.获取下架验证依据：验证1(是否在项目中被使用)
                        int fourTreeMatCount = proInfoMaterialDao.findFourTreeMatCountByTreeFourIDAndCityID(vmhMainobject,vmhCityid); // 四级科目材料下项目材料使用量.

                        // 2.未通过验证:回滚事务/设置提示信息/结束方法
                        if (0 != fourTreeMatCount){
                            transactionManager.rollback(status);
                            informationBody.setStatusCode(-1);
                            informationBody.setStatusMsg("项目材料,无法下架!如需下架,请联系统管理员.");
                            return informationBody;
                        }

                        // 3.通过验证:执行下架相关操作
                        // )1.材料子库完善状态表: 根据四级ID/城市ID 下架
                        int mpsNm = materialPerfectStateDao.deleteMatPerfectStateByTreeFourIDAndCityID(vmhMainobject,vmhCityid);

                        // )2.材料子库表(规格下的所有材料): 根据四级ID/城市ID,检索所有材料指定条件下的材料 下架
                        int mNm = materialDao.deleteMatsByTreeFourIDAndCityID(vmhMainobject,vmhCityid);

                        // )3.材料母库照片表(规格下的所有材料的所有照片): 根据四级ID/城市ID,检索满足条件的记录 下架
                        int mpNm = treeStandardPhotoDao.deleteMatPhotosByTreeFourIDAndCityID(vmhMainobject,vmhCityid);

                        // )4.材料商材料关联表(材料的所有供货商): 根据四级ID/城市ID,检索满足条件的材料商关联 下架
                        int mtsNm = materialToSuppliersDao.deleteMatToSuppliersByTreeFourIDAndCityID(vmhMainobject,vmhCityid);

                        // 4.保存操作记录(有参:操作人卡号/操作人类型/行为类型/行为主ID/行为内容)
                        int dbNm = dataBehaviorDao.insert(new DataBehavior(vmhUsercard, vmhUsertype, SubLibraryRecentData.M_B_SL_TREE_DELETE, vmhMainobject, "材料处理:下架(" + vmhContent + ")"));

                    } else {
                        // 操作记录 设置当前处理类型
                        String vmhTypeStr = "";
                        switch (vmhType){
                            case 1: vmhTypeStr = "正常"; break;
                            case 2: vmhTypeStr = "问题"; break;
                            case 3: vmhTypeStr = "异常"; break;
                        }
                        // 设置 操作记录
                        int dbNm = dataBehaviorDao.insert(new DataBehavior(vmhUsercard, vmhUsertype, SubLibraryRecentData.M_A_TS_BASE_SAVE, vmhMainobject, "材料处理:"+vmhTypeStr+"(" + vmhContent + ")"));
                    }

                    // 5.添加 同步处理记录相关内容到回访记录(有参:类型/操作人类型/主ID/城市ID/标记/内容/卡号)
                    visitDao.saveVisit(new VisitVo(visitType,vmhUsertype,vmhMainobject,vmhCityid,vmhType == 4  ? 3 : vmhType,vmhContent,vmhUsercard));
                }
            }

            transactionManager.commit(status);
            long endTime = System.currentTimeMillis();
            logger.debug("材料处理:添加处理,添加成功!程序运行时间:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            transactionManager.rollback(status);
            informationBody.setStatusCode(-1);
            informationBody.setStatusMsg("材料处理:添加处理失败 请联系统管理员");
            logger.error("材料处理:添加处理接口,调用失败!", e);
        }

        return informationBody;
    }

}
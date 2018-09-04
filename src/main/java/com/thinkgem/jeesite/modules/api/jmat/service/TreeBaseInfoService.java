/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.List;
import java.util.UUID;

import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeBaseInfo;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformBasicInformation;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreeBaseInfoDao;
import com.thinkgem.jeesite.modules.api.jmat.dao.TreeSstDao;

/**
 * 材料基础信息Service
 * @author ljc
 * @version 2018-03-16
 */
@Service
@Transactional(readOnly = true)
public class TreeBaseInfoService extends CrudService<TreeBaseInfoDao, TreeBaseInfo> {
	
	//---数据访问对象---
	@Autowired// 科目树设置表
	private TreeSstDao treeSstDao;
	@Autowired// 数据行为表
    private DataBehaviorDao dataBehaviorDao;
	@Autowired// 数据源事务管理类对象
	private DataSourceTransactionManager transactionManager;

	public TreeBaseInfo get(String id) {
		return super.get(id);
	}
	
	public List<TreeBaseInfo> findList(TreeBaseInfo treeBaseInfo) {
		return super.findList(treeBaseInfo);
	}
	
	public Page<TreeBaseInfo> findPage(Page<TreeBaseInfo> page, TreeBaseInfo treeBaseInfo) {
		return super.findPage(page, treeBaseInfo);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(TreeBaseInfo treeBaseInfo) {
		super.delete(treeBaseInfo);
	}
	
	
	
	/**
	 * 更新材料基础信息
	 * 
	 * @author   ljc
	 * @version  2018-03-23
	 * @param    pbi           平台基础信息
	 * @return   响应更新状态信息
	 */
	public InformationBody update(PlatformBasicInformation pbi) {
		InformationBody informationBody = new InformationBody();
		try {
			treeSstDao.updateTypeStateByTreeID(pbi.getTreeID(),pbi.getType());//更新分类
			int numberOfAffectedRows = dao.updateByPlatformBasicInformation(pbi);//更新材料基础信息
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("成功!");
			logger.debug("更新材料基础信息接口,调用成功!受影响行数:"+numberOfAffectedRows);
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("更新材料基础信息接口,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 新增或更新取样方法(小样标准)API
	 * @author     ljc
	 * @version    2018-03-27
	 * @param      treeFourID         四级科目ID
	 * @param      samplingMethod     取样方法
	 * @param      cardNum            操作人卡号
     * @updateTime 2018-6-14 17:01:59
	 * @return     InformationBody    响应状态信息
	 */
	public InformationBody saveOrUpdateSamplingMethod(String treeFourID,String samplingMethod,String cardNum) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

		InformationBody informationBody = new InformationBody();
		TreeBaseInfo treeBaseInfo = null;
		try {
			//通过四级科目ID,获取材料基础信息对象(全国统一,四级科目ID=唯一标识)
			treeBaseInfo = dao.findTreeBaseInfoByTreeID(treeFourID);
			if (null!=treeBaseInfo) {
				//不为空,重新设置值以供更新.
				treeBaseInfo.setTbiMockupsamplingremark(samplingMethod);
			}else{
				//为空,初始化对象设置值以供插入.
				treeBaseInfo = new TreeBaseInfo();
				treeBaseInfo.setTbiId(UUID.randomUUID().toString());
				treeBaseInfo.setTbiTreeid(treeFourID);
				treeBaseInfo.setTbiMockupsamplingremark(samplingMethod);
			}
			
			//更新或新增材料基础数据(更新/新增依据:四级科目ID&进程状态)
			int numberOfAffectedRows = dao.treeBaseInfoSaveOrUpdate(treeBaseInfo);//返回影响行数
			logger.debug("子库：保存材料树（规格级）基础信息-小样标准-取样方法。树ID："+treeFourID+"更新材料基础信息接口,调用成功!受影响行数:"+numberOfAffectedRows);

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(cardNum);
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_STANDARD_INFO_SAVE);
            dataBehavior.setDbMainid(treeFourID);
            dataBehavior.setDbContent("修改材料树（规格级）小样标准-取样方法");
            dataBehaviorDao.insert(dataBehavior);

            transactionManager.commit(status); //提交
        } catch (Exception e) {
            transactionManager.rollback(status);  //回滚
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("子库：保存材料树（规格级）基础信息-小样标准-取样方法接口,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 新增或更新包装标准API
	 * @author   ljc
	 * @version  2018-03-27 
	 * @param    treeFourID       四级科目ID
	 * @param    packGenre        包装类别(类别：1瑞祥标准，2合作商标准)
	 * @param    packQuality      包装材质
	 * @param    packExplain      包装说明
	 * @param    cardNumber       操作人卡号
	 * @return   InformationBody  响应状态信息
     * @updateTime 2018年6月19日19:53:27
	 */
	public InformationBody saveOrUpdatePackStandard(String treeFourID,Integer packGenre,String packQuality,String packExplain,String cardNumber) {
		InformationBody informationBody = new InformationBody();
		TreeBaseInfo treeBaseInfo = null;
		try {
			//通过四级科目ID,获取材料基础信息对象(全国统一,四级科目ID=唯一标识)
			treeBaseInfo = dao.findTreeBaseInfoByTreeID(treeFourID);
			if (null!=treeBaseInfo) {
				//不为空,重新设置值以供更新.
				treeBaseInfo.setTbiPstype(packGenre);//类型
				treeBaseInfo.setTbiPsmaterialquality(packQuality);//材质
				treeBaseInfo.setTbiPsmarkremark(packExplain);//说明
			}else{
				//为空,初始化对象设置值以供插入.
				treeBaseInfo = new TreeBaseInfo();
				treeBaseInfo.setTbiId(UUID.randomUUID().toString());
				treeBaseInfo.setTbiTreeid(treeFourID);
				treeBaseInfo.setTbiPstype(packGenre);//类型
				treeBaseInfo.setTbiPsmaterialquality(packQuality);//材质
				treeBaseInfo.setTbiPsmarkremark(packExplain);//说明
			}
			
			//更新或新增材料基础数据(更新/新增依据:四级科目ID&进程状态)
			int numberOfAffectedRows = dao.treeBaseInfoSaveOrUpdate(treeBaseInfo);//返回影响行数


            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(cardNumber);
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_STANDARD_INFO_SAVE);
            dataBehavior.setDbMainid(treeFourID);
            dataBehavior.setDbContent("修改材料树（规格级）包装标准");
            dataBehaviorDao.insert(dataBehavior);
			logger.warn("子库：保存材料树（规格级）基础信息-包装标准。树ID:"+treeFourID+"更新材料基础信息接口,调用成功!受影响行数:"+numberOfAffectedRows);
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("更新材料基础信息接口,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 新增或更新材料基础信息API
	 * @author  ljc
	 * @version 2018-03-27
	 * @param   unitID           单位ID
	 * @param   treeFourID       四级科目ID
	 * @param   function         功能
	 * @param   depict           描述
	 * @param   type             分类
     * @param   cardNumber       当前操作用户卡号
	 * @return  InformationBody  响应状态信息
	 */
	public InformationBody saveOrUpdateBaseInfo(Integer unitID, String treeFourID, String function, String depict, Integer type,String cardNumber) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

		InformationBody informationBody = new InformationBody();
		TreeBaseInfo treeBaseInfo = null;
		try {
			//通过四级科目ID,获取材料基础信息对象(全国统一,四级科目ID=唯一标识)
			treeBaseInfo = dao.findTreeBaseInfoByTreeID(treeFourID);
			if (null!=treeBaseInfo) {
				//不为空,重新设置值以供更新.
				treeBaseInfo.setTbiUnit(unitID);
				treeBaseInfo.setTbiMatfunction(function);
				treeBaseInfo.setTbiMatdescription(depict);
			}else{
				//为空,初始化对象设置值以供插入.
				treeBaseInfo = new TreeBaseInfo();
				treeBaseInfo.setTbiId(UUID.randomUUID().toString());
				treeBaseInfo.setTbiTreeid(treeFourID);
				treeBaseInfo.setTbiUnit(unitID);
				treeBaseInfo.setTbiMatfunction(function);
				treeBaseInfo.setTbiMatdescription(depict);
			}
			
			treeSstDao.updateTypeStateByTreeID(treeFourID,type);//更新分类

			//更新或新增材料基础数据(更新/新增依据:四级科目ID&进程状态)
			int numberOfAffectedRows = dao.treeBaseInfoSaveOrUpdate(treeBaseInfo);//返回影响行数

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);// 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商，200集团主案，201地方主案）
            dataBehavior.setDbOperatuser(cardNumber);// 操作人卡号
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_BASE_SAVE);// 操作行为分类编码
            dataBehavior.setDbMainid(treeFourID);// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
            dataBehavior.setDbState(1);// 状态：1成功，2失败
            dataBehavior.setDbContent("修改材料树（规格级）基础信息");// 行为内容
            dataBehaviorDao.insert(dataBehavior);
			
			informationBody.setStatusMsg("ok");
            logger.debug("更新材料基础信息接口,调用成功!受影响行数:"+numberOfAffectedRows);
            // 提交
            transactionManager.commit(status);
        } catch (Exception e) {
            // 回滚
            transactionManager.rollback(status);
            informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("fail");
			logger.error("更新材料基础信息接口,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 更新包装照片
	 * @author   ljc
	 * @version  2018-03-28 
	 * @param    treeFourID       四级科目ID
	 * @param    photoUrl         照片路径
	 * @param    cardNumber       操作人卡号
	 * @return   InformationBody  接口调用状态信息
     * @updateTime 2018-6-19 19:36:47
	 */
	public InformationBody updatePackPhoto(String treeFourID, String photoUrl,String cardNumber) {
		InformationBody informationBody = new InformationBody();
		try {
		    // 更新前校验
		    if (treeFourID == null || photoUrl == null){
                informationBody.setStatusCode(-1);
                informationBody.setStatusMsg("上传无效！无效的数据!");
                logger.error("上传无效！无效的数据!");
                return informationBody;
            }
            // 更新
            int numberOfAffectedRows = dao.updatePhotoUrlByTreeFourID(treeFourID,photoUrl);//返回影响行数
            if (0 != numberOfAffectedRows ){
                // 保存数据行为
                DataBehavior dataBehavior = new DataBehavior();
                dataBehavior.setDbOperattype(6);// 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商，200集团主案，201地方主案）
                dataBehavior.setDbOperatuser(cardNumber);// 操作人卡号
                dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_STANDARD_INFO_SAVE);// 操作行为分类编码
                dataBehavior.setDbMainid(treeFourID);// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
                dataBehavior.setDbContent("修改材料树（规格级）包装标准源文件(包装照片)");// 行为内容
                dataBehaviorDao.insert(dataBehavior);
                logger.warn("材料后台：更新母库材料标准(源文件)成功。母库ID："+treeFourID);
            }
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("更新包装照片接口,调用失败!", e);
		}
		return informationBody;
	}
	
	
	
}
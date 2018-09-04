/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.entity.StandardPara;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformStandardParameters;
import com.thinkgem.jeesite.modules.api.jmat.dao.StandardParaDao;

/**
 * 材料标准参数Service
 * @author ljc
 * @version 2018-03-21
 */
@Service
@Transactional(readOnly = true)
public class StandardParaService extends CrudService<StandardParaDao, StandardPara> {

    /**
     * 数据访问对象
     */
    @Autowired// 数据行为表
    private DataBehaviorDao dataBehaviorDao;

	public StandardPara get(String id) {
		return super.get(id);
	}
	
	public List<StandardPara> findList(StandardPara standardPara) {
		return super.findList(standardPara);
	}
	
	public Page<StandardPara> findPage(Page<StandardPara> page, StandardPara standardPara) {
		return super.findPage(page, standardPara);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(StandardPara standardPara) {
		super.delete(standardPara);
	}
	
	/**
	 * 通过参数ID与四级科目ID,移除材料参数.
	 * @author   ljc
	 * @version  2018-03-25 
	 * @param    paraID          参数ID
	 * @param    treeFourID      四级科目ID
	 * @return   InformationBody 响应信息
	 */
	public InformationBody delete(Integer paraID,String treeFourID,String cardNum) {
		InformationBody informationBody = new InformationBody();
		try {
			int numberOfAffectedRows = dao.deleteStandardParaByParaID(paraID,treeFourID,new Date());
			informationBody.setStatusMsg("删除材料参数成功!");
            logger.debug("移除材料参数接口,调用成功!受影响行数:"+numberOfAffectedRows);

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);// 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商，200集团主案，201地方主案）
            dataBehavior.setDbOperatuser(cardNum);// 操作人卡号
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_PARA_SAVE);// 操作行为分类编码
            dataBehavior.setDbMainid(treeFourID);// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
            dataBehavior.setDbState(1);// 状态：1成功，2失败
            dataBehavior.setDbContent("移除材料（规格级）的材料参数");// 行为内容
            dataBehaviorDao.insert(dataBehavior);

		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("移除材料参数接口,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 新增或更新,材料参数.
	 * @author   ljc
	 * @version  2018-03-26 
	 * @param    psp(新增/更新)
	 * @return   InformationBody 响应信息
	 */
	public InformationBody saveOrUpdate(PlatformStandardParameters psp) {
		InformationBody informationBody = new InformationBody();
		//SELECT ''46'' paraID,''1'' paraValue UNION ALL SELECT ''35'',''2'' UNION ALL SELECT ''41'',''2''
		try {
			if (null!=psp&&0!=psp.getParas().size()) {
				StringBuilder stringBuilder = new StringBuilder();
				List<Map<String,String>> items = psp.getParas();
				for (Map<String, String> map : items) {
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
                        String paraID = entry.getKey();//参数IP
						String paraValue = entry.getValue();//参数值
						//UNION 操作符用于合并两个或多个 SELECT 语句的结果集。	
						stringBuilder.append("SELECT ''").append(paraID).append("'' paraID,''").append(paraValue).append("'' paraValue UNION ALL ");//
					}
				}
				String sql = StringUtils.substringBeforeLast(stringBuilder.toString(), "UNION ALL ");//子查询部分
				dao.saveOrUpdate(sql,psp.getTreeFourID());
			}

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);// 操作人类型（0地方监理，1地方工程经理，2集团监理，3集团工程经理，4地方行政主管,5集团客服，6集团材料，7集团项目人事，101项目经理，102材料商，200集团主案，201地方主案）
            dataBehavior.setDbOperatuser(psp.getCradNum());// 操作人卡号
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_PARA_SAVE);// 操作行为分类编码
            dataBehavior.setDbMainid(psp.getTreeFourID());// 主ID（项目ID | 材料ID | 材料树ID | 或者其他）
            dataBehavior.setDbState(1);// 状态：1成功，2失败
            dataBehavior.setDbContent("修改材料（规格级）的材料参数");// 行为内容
            dataBehaviorDao.insert(dataBehavior);

			logger.debug("新增或更新,材料参数接口,调用成功!");
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("新增或更新,材料参数接口,调用失败!", e);
		}
		return informationBody;
	}
	
//	public InformationBody delete(String paraID) {
//		InformationBody informationBody = new InformationBody();
//		try {
//			informationBody.setStatusCode(0);
//			informationBody.setStatusMsg("成功!");
//			logger.debug("移除材料参数接口,调用成功!");
//		} catch (Exception e) {
//			informationBody.setStatusCode(-1);
//			informationBody.setStatusMsg("失败!");
//			logger.error("移除材料参数接口,调用失败!", e);
//		}
//		return dao.deleteStandardParaByParaID(paraID);
//	}
	
}
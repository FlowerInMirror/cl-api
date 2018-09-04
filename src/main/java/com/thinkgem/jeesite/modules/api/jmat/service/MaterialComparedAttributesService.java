/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.modules.api.jmat.dao.DataBehaviorDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.DataBehavior;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.SubLibraryRecentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialComparedAttributes;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.dao.MaterialComparedAttributesDao;

/**
 * 属性对比（对比图）Service
 * @author ljc
 * @version 2018-03-19
 */
@Service
@Transactional(readOnly = true)
public class MaterialComparedAttributesService extends CrudService<MaterialComparedAttributesDao, MaterialComparedAttributes> {

	public MaterialComparedAttributes get(String id) {
		return super.get(id);
	}
	
	public List<MaterialComparedAttributes> findList(MaterialComparedAttributes materialComparedAttributes) {
		return super.findList(materialComparedAttributes);
	}
	
	public Page<MaterialComparedAttributes> findPage(Page<MaterialComparedAttributes> page, MaterialComparedAttributes materialComparedAttributes) {
		return super.findPage(page, materialComparedAttributes);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(MaterialComparedAttributes materialComparedAttributes) {
		super.delete(materialComparedAttributes);
	}

    /**
     * 数据访问对象
     */
    @Autowired// 数据行为表
    private DataBehaviorDao dataBehaviorDao;

	
	/**
	 * 对比标准(新增/更新)API
	 * @author     ljc
	 * @version    2018-03-27
	 * @param      mcaID            属性对比ID
	 * @param      mcaName          对比名称
	 * @param      mcaType          类型（1对比，2材料约定）
	 * @param      treeFourID       四级科目ID
	 * @param      cardNum          操作人卡号
     * @updateTime 2018-6-14 19:02:06
	 * @return     InformationBody  响应状态信息
	 */
	public InformationBody saveOrUpdate(Integer mcaID, String mcaName, Integer mcaType, String treeFourID,String cardNum) {
		InformationBody informationBody = new InformationBody();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("mcaID", mcaID);
			map.put("mcaName", mcaName);
			map.put("mcaType", mcaType);
			map.put("treeFourID", treeFourID);
			Integer operationID = dao.saveOrUpdate(map);// 操作ID
			logger.debug("对比标准属性(新增/更新)接口,调用成功!");

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(cardNum);
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_ATTR_ITEM);
            dataBehavior.setDbMainid(operationID.toString());// 对比ID
            dataBehavior.setDbContent("修改材料（规格级）"+(mcaType == 1 ? "对比" : "材料约定")+"属性");
            dataBehaviorDao.insert(dataBehavior);

		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("对比标准属性(新增/更新)接口,调用失败!", e);
		}
		return informationBody;
	}
	
	/**
	 * 移除对比标准属性API
	 * @author     ljc
	 * @version    2018-03-27
	 * @param      mcaID           对比属性ID
     * @param      attrType        对比属性类型（1对比，2材料约定）
     * @param      cardNum         操作人卡号
     * @updateTime 2018-6-14 18:45:27
	 * @return     InformationBody 响应信息
	 */
	public InformationBody delete(Integer mcaID,Integer attrType,String cardNum) {
		InformationBody informationBody = new InformationBody();
		try {
			int numberOfAffectedRows = dao.deleteMaterialComparedAttributesByMcaID(mcaID);
			logger.debug("移除对比标准属性接口,调用成功!受影响行数:"+numberOfAffectedRows);

            // 保存数据行为
            DataBehavior dataBehavior = new DataBehavior();
            dataBehavior.setDbOperattype(6);
            dataBehavior.setDbOperatuser(cardNum);
            dataBehavior.setDbClass(SubLibraryRecentData.M_A_TS_ATTR_ITEM);
            dataBehavior.setDbMainid(mcaID.toString());// 对比ID
            dataBehavior.setDbContent("删除材料（规格级）"+(attrType == 1 ? "对比" : "材料约定")+"属性");
            dataBehaviorDao.insert(dataBehavior);

		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("移除对比标准属性接口,调用失败!", e);
		}
		return informationBody;
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.web.Constants;
import com.thinkgem.jeesite.modules.api.jmat.entity.Unit;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;

import net.sf.json.JSONArray;

import com.thinkgem.jeesite.modules.api.jmat.dao.UnitDao;

/**
 * 材料单位Service
 * @author ljc
 * @version 2018-03-23
 * @param <V>
 */
@Service
@Transactional(readOnly = true)
public class UnitService<V> extends CrudService<UnitDao, Unit> {
	//Redis 键前缀
	public static final String KEY_PREFIX = Global.getConfig("redis.keyPrefix");

	public Unit get(String id) {
		return super.get(id);
	}
	
	public List<Unit> findList(Unit unit) {
		return super.findList(unit);
	}
	
	public Page<Unit> findPage(Page<Unit> page, Unit unit) {
		return super.findPage(page, unit);
	}

	
	@Transactional(readOnly = false)
	public void delete(Unit unit) {
		super.delete(unit);
	}
	
	/**
	 * 获取全部单位
	 * @author ljc
	 * @version 2018-03-23
	 * @return 全部材料单位
	 */
	public InformationBody getAllUnit() {
		InformationBody informationBody = new InformationBody();
		try {
			List<Map<String, Object>> unitArray = new ArrayList<Map<String, Object>>();
			String unitsStr = JedisUtils.get(KEY_PREFIX + Constants.UNIT);
			if(null == unitsStr){
				List<Unit> units = dao.getAllUnit();//全部单位
				for (Unit unit : units) {
					Map<String, Object> unitMap = new HashMap<String,Object>();
					unitMap.put("unitID", unit.getUnitId());//单位ID
					unitMap.put("unitName", unit.getUnitName());//单位名称
					unitArray.add(unitMap);
				}
				JedisUtils.set(KEY_PREFIX + Constants.UNIT,JSON.toJSONString(unitArray),60 * 60 * 24);
				logger.debug("设置单位到Redis缓存中.有效期:" + 60 * 60 * 24);
			}else {
				unitArray = JSONArray.fromObject(unitsStr);
				logger.debug("Redis缓存中获取单位.");
			}
			informationBody.setBody(unitArray);//设置响应体
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("成功!");
			logger.debug("获取全部单位API,响应成功!", informationBody);
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败!");
			logger.error("获取全部单位API,调用失败!", e);
		}
		return informationBody;
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.api.jmat.entity.Parameter;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.dao.ParameterDao;

/**
 * 材料参数Service
 * @author ljc
 * @version 2018-03-23
 */
@Service
@Transactional(readOnly = true)
public class ParameterService extends CrudService<ParameterDao, Parameter> {

	public Parameter get(String id) {
		return super.get(id);
	}
	
	public List<Parameter> findList(Parameter parameter) {
		return super.findList(parameter);
	}
	
	public Page<Parameter> findPage(Page<Parameter> page, Parameter parameter) {
		return super.findPage(page, parameter);
	}
	

	
	@Transactional(readOnly = false)
	public void delete(Parameter parameter) {
		super.delete(parameter);
	}
	
	/**
	 * 获取全部材料参数
	 * @author   ljc
	 * @version  2018-03-23
	 */
	public InformationBody getAllParameter() {
		InformationBody informationBody = new InformationBody();
		try {
			List<Parameter> parameters = dao.getAllParameter();//全部材料参数
			List<Map<String, Object>> parameterMaps = new ArrayList<Map<String, Object>>();
			for (Parameter parameter : parameters) {//组织响应体
				HashMap<String, Object> linkedHashMap = new LinkedHashMap<String,Object>();
				linkedHashMap.put("paraId", parameter.getParaId());//参数ID
				linkedHashMap.put("paraName", parameter.getParaName());//参数名称
				linkedHashMap.put("paraUnitname", parameter.getParaUnitname());//参数单位
				parameterMaps.add(linkedHashMap);
			}
			informationBody.setBody(parameterMaps);//设置响应体
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("成功!");
			logger.debug("获取全部材料参数API,响应成功!", informationBody);
		} catch (Exception e) {
			informationBody.setStatusCode(1);
			informationBody.setStatusMsg("失败!");
			logger.error("获取全部材料参数API,调用失败!", e);
		}
		return informationBody;
	}
	
}
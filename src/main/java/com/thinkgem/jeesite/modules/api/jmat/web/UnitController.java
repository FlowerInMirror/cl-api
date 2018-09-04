/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.service.UnitService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 材料单位Controller
 * @author ljc
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "unit-api")
@Api(value="材料单位Controller",description="材料单位API")
public class UnitController extends BaseController {

	@Autowired
	private UnitService unitService;
	
	/**
	 * 获取全部材料单位API
	 * @author   ljc
	 * @version  2018-03-23
	 */
	@RequestMapping(value={"all_unit"},method=RequestMethod.GET)
	@ApiOperation(value="全部材料单位API",httpMethod="GET",notes="获取全部材料单位.<br/>"
			+ "@author  ljc<br/>@version 2018-03-15<br/>body:<br/>"
			+ "[{unitID 单位ID,unitName 单位名称}... ]",response=InformationBody.class)
	public@ResponseBody InformationBody getAllUnit(){
		return unitService.getAllUnit();
	}


}
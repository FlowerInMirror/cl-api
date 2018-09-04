/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.service.ParameterService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 材料参数Controller
 * @author ljc
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "parameter-api")
@Api(value="材料参数Controller",description="材料参数API")
public class ParameterController extends BaseController {

	@Autowired
	private ParameterService parameterService;
	
	/**
	 * 获取全部材料参数API
	 * @author   ljc
	 * @version  2018-03-23
	 */
	@RequestMapping(value={"/all_parameter"})
	@ApiOperation(value="全部材料参数API",httpMethod="GET",notes="获取全部材料参数.<br/>"
			+ "@author  ljc<br/>@version 2018-03-15<br/>body:<br/>"
			+ "[{paraId 参数ID,paraName 参数名称,paraUnitname 参数单位}... ]",response=InformationBody.class)
	public@ResponseBody InformationBody getAllParameter(){
		return parameterService.getAllParameter();
	}

}
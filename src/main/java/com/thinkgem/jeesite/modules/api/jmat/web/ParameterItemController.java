/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.service.ParameterItemService;

/**
 * 材料参数项Controller
 * @author ljc
 * @version 2018-05-29
 */
@Controller
@RequestMapping(value = "parameter_item-api")
public class ParameterItemController extends BaseController {

	@Autowired
	private ParameterItemService parameterItemService;

    /**
     * 获取全部材料参数项API
     * @author   ljc
     * @version  2018-05-29
     */
    @RequestMapping(value={"/para_items"})
    @ApiOperation(value="材料参数项-通过材料参数ID获取API",httpMethod="GET",notes="获取全部材料参数项.通过材料参数ID<br/>"
            + "@author  ljc<br/>@version 2018-05-29<br/>body:<br/>",response=InformationBody.class)
    public@ResponseBody InformationBody getAllParameter(
            @ApiParam(value="参数ID",name="paramID")@RequestParam(required=false) Integer paramID){
        return parameterItemService.getAllParameterItems(paramID);
    }



}
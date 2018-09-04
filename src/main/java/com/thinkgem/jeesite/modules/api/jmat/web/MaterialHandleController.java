/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.status.MatHandleVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.service.MaterialHandleService;

/**
 * 材料处理表Controller
 * @author ljc
 * @version 2018-07-26
 */
@Controller
@RequestMapping(value = "material_handle-api")
public class MaterialHandleController extends BaseController {

	@Autowired
	private MaterialHandleService materialHandleService;

    /**
     * 材料处理:添加处理
     * @author ljc
     * @param  matHandleVo      处理记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-26 13:57:18
     */
    @RequestMapping(value={"save"})
    @ApiOperation(value="材料处理:添加处理API",httpMethod="POST",notes="材料处理:添加处理<br/>@author  ljc<br/>@version 2018-7-26 13:57:18",response=InformationBody.class)
    public@ResponseBody InformationBody saveMatHandle (
            @ApiParam(required=true,value="处理记录模型",name="matHandleVo")@RequestBody MatHandleVo matHandleVo){
        return materialHandleService.saveMatHandle(matHandleVo);
    }



}
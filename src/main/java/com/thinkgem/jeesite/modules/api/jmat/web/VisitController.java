/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.VisitVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.service.VisitService;

/**
 * 项目|人员回访记录V_VisitController
 * @author  ljc
 * @version 2018-7-25 11:14:17
 */
@Controller
@RequestMapping(value = "visit-api")
public class VisitController extends BaseController {

	@Autowired
	private VisitService visitService;

    /**
     * 回访记录:添加回访
     * @author ljc
     * @param  visitVo          回访记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-25 11:38:14
     */
    @RequestMapping(value={"save"})
    @ApiOperation(value="回访记录:添加回访API",httpMethod="POST",notes="回访记录:添加回访<br/>@author  ljc<br/>@version 2018-7-9 11:44:44",response=InformationBody.class)
    public@ResponseBody InformationBody saveVisit (
            @ApiParam(required=true,value="回访记录模型",name="visit")@RequestBody VisitVo visitVo){
        return visitService.saveVisit(visitVo);
    }

    /**
     * 回访记录:加载回访
     * @author ljc
     * @param  visitVo          回访记录模型
     * @return InformationBody  响应体
     * @createTime 2018-7-25 14:14:12
     */
    @RequestMapping(value={"load"})
    @ApiOperation(value="回访记录:加载回访API",httpMethod="POST",notes="回访记录:加载回访<br/>@author  ljc<br/>@version 2018-7-25 14:14:20",response=InformationBody.class)
    public@ResponseBody InformationBody loadVisit (
            @ApiParam(required=true,value="回访记录模型",name="visit")@RequestBody VisitVo visitVo){
        return visitService.loadVisit(visitVo);
    }

}
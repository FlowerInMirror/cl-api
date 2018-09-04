/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeSstService;

/**
 * 科目树设置Controller
 * @author ljc
 * @version 2018-03-17
 */
@Controller
@RequestMapping(value = "tree_sst-api")
@Api(value="科目树设置Controller",description="科目树设置API")
public class TreeSstController extends BaseController {

	@Autowired
	private TreeSstService treeSstService;

	// 获取材料分类
    /**
     * @author ljc
     * @param  treeFourID 四级科目ID
     * @return body       材料分类 1成品，2非成品 (默认0)
     * @createTime 2018-7-9 11:40:37
     */
    @RequestMapping(value={"get/mat_classify"})
    @ApiOperation(value="获取材料分类API",httpMethod="GET",notes="获取材料分裂<br/>@author  ljc<br/>@version 2018-7-9 11:44:44",response=InformationBody.class)
    public@ResponseBody InformationBody getMatClassify (@ApiParam(required=true,value="四级科目ID",name="treeFourID")@RequestParam String treeFourID){
        return treeSstService.getMatClassify(treeFourID);
    }


}
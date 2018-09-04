/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform.PlatformSearchTerm;
import com.thinkgem.jeesite.modules.api.jmat.service.SearchItemService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.servlet.http.HttpSession;

/**
 * 材料搜索|用途Controller
 * @author ljc
 * @version 2018-03-28
 */
@Controller
@RequestMapping(value = "search_item-api")	
@Api(value="材料搜索|用途Controller",description="材料搜索|用途API")
public class SearchItemController extends BaseController {

	@Autowired
	private SearchItemService searchItemService;

    /**
     * 搜索词编辑(删除|新增)API
     * @author   ljc
     * @version  2018-03-28
     * @param    treeFourID      四级科目ID
     * @param    type            类型：1搜索，2用途，4搜索自动生成内容（可位运算|）
     * @return   InformationBody 响应状态信息
     */
    @RequestMapping(value={"search_term_edit"})
    @ApiOperation(value="搜索词编辑(删除|恢复|新增)API",httpMethod="PUT",notes="搜索词编辑(删除|恢复|新增)<br/>@author  ljc<br/>@version 2018-03-28<br/>",response=InformationBody.class)
    public@ResponseBody InformationBody searchTermEdit (
            @ApiParam(required=true,value="平台搜索词",name="platformSearchTerm")@RequestBody PlatformSearchTerm platformSearchTerm,
            HttpSession session){
        return searchItemService.searchTermEdit(platformSearchTerm, MMSUtils.getCardNumber(session));
    }


}
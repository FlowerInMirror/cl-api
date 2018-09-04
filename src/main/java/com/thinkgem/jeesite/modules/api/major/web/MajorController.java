/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.major.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampType;
import com.thinkgem.jeesite.modules.api.major.entity.MajorCampTypeItem;
import com.thinkgem.jeesite.modules.api.major.entity.po.one.AList;
import com.thinkgem.jeesite.modules.api.major.service.MajorService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主营管理Controller
 * @author ljc
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "major-api")
public class MajorController extends BaseController {

    // "============================ 业务对象 ============================="
	@Autowired
	private MajorService majorService; // 主营管理

    // "============================== 一段 =============================="

    // 主营一段:列表 @author ljc @createTime 2018-8-17 16:01:05
    @RequestMapping(value = "one/list")
    @ApiOperation(value="主营一段:列表",httpMethod="GET",notes="二级科目相关,详见响应实体模型<br/>@author  ljc<br/>@version 2018-8-17 16:01:05",response = AList.class)
    public@ResponseBody InformationBody getOneList() {
        return majorService.getOneList();
    }


    // "============================== 二段 =============================="

    // 主营二段:类型 @author ljc @createTime 2018-8-17 17:10:36
    @RequestMapping(value = "two/type")
    @ApiOperation(value="主营二段:类型API",httpMethod="GET",notes="主营材料下的所有类别<br/>@author  ljc<br/>@version 2018-8-17 2018-8-17 17:10:36",response = MajorCampType.class)
    public@ResponseBody InformationBody findTwoTypeByTreeTwoID(
            @ApiParam(required=false,value="二级科目ID",name="treeTwoID")@RequestParam(required = false) String treeTwoID) {
        return majorService.findTwoTypeByTreeTwoID(treeTwoID);
    }

    // "============================== 三段 =============================="

    // 主营三段:类型项 @author ljc @createTime 2018-8-17 17:36:06
    @RequestMapping(value = "two/type/item")
    @ApiOperation(value="主营三段:类型项API",httpMethod="GET",notes="主营材料下的所有类别项<br/>@author  ljc<br/>@version 2018-8-17 2018-8-17 17:39:54",response = MajorCampTypeItem.class)
    public@ResponseBody InformationBody findTreeTypeItemByMajorTypeID(
            @ApiParam(required=false,value="主营属性类型ID",name="majorTypeID")@RequestParam(required = false) Integer majorTypeID) {
        return majorService.findTreeTypeItemByMajorTypeID(majorTypeID);
    }




}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.web.mms.web;


import com.thinkgem.jeesite.common.web.BaseController;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 材料平台-材料WEB-Controller
 * @author     ljc
 * @version    1.0
 * @createTime 2018-4-24 10:26:02
 */
@Controller
@RequestMapping(value = "mat-web")
public class MatController extends BaseController {

    /**
     * 材料板块-子库(首页)
     * @author      ljc
     * @version     1.0
     * @createTime  2018-4-26 14:37:41
     */
    @RequestMapping(value = {"index"})
    @ApiOperation(value="材料板块-子库(首页)",httpMethod="GET",notes="材料板块-子库(首页)<br/>@author ljc<br/>@version 2018-4-26<br/>")
    public String  sublibraryIndex(){
        return "modules/mms/main/material/sublibrary/index";
    }

    /**
     * 材料板块-主营管理
     * @author      ljc
     * @version     1.0
     * @createTime  2018-8-17 19:23:19
     */
    @RequestMapping(value = {"major"})
    @ApiOperation(value="材料板块-主营管理",httpMethod="GET",notes="材料板块-主营管理<br/>@author ljc<br/>@version 2018-8-17 19:23:19<br/>")
    public String  majorCampManage(){
        return "modules/mms/major/index";
    }

    /**
     * 材料板块-小样管理
     * @author      ljc
     * @version     1.0
     * @createTime  2018-8-27 14:44:03
     */
    @RequestMapping(value = {"sample"})
    @ApiOperation(value="材料板块-小样管理",httpMethod="GET",notes="材料板块-小样管理<br/>@author ljc<br/>@version 2018-8-27 14:44:03<br/>")
    public String  smallSampleManage(){
        return "modules/mms/sample/index";
    }


}
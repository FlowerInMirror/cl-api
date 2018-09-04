/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.web.mms.web;


import com.thinkgem.jeesite.common.web.BaseController;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * iframe-Controller
 * @author     ljc
 * @version    1.0
 * @createTime 2018-5-3 14:01:52
 */
@Controller
@RequestMapping(value = "iframe-web")
public class IframeController extends BaseController {


    @RequestMapping(value = {"material_renshi"})
    @ApiOperation(value="去往子库城市一段列表",httpMethod="GET",notes="跳转-子库城市一段列表@author  ljc<br/>@version 2018-5-11<br/>")
    public String toCityOneList1() {
        return "modules/mms/commons/iframes/material_renshi";
    }
}
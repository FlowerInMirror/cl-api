/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.web.em.material.web;


import com.thinkgem.jeesite.common.web.BaseController;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工程经理.材料
 * @author     ljc
 * @createTime 2018-8-28 13:43:33
 */
@Controller("emMaterialController-web")
@RequestMapping(value = "em/material-web")
public class EMMaterialController extends BaseController {

    @RequestMapping(value = {"{path}"})
    @ApiOperation(value="万能视图(格式:em/material-web/资源1-资源2-资源3...)",httpMethod="GET",notes="@author  ljc<br/>@version 2018-8-28 15:40:30<br/>")
    public String toEMMaterialView(@PathVariable(value = "path") String path) {
        return "modules/mms/material/" + path.replace("-","/");
    }

    @RequestMapping(value = {"index"})
    @ApiOperation(value="材料板块-子库(首页)",httpMethod="GET",notes="材料板块-子库(首页)<br/>@author ljc<br/>@version 2018-4-26<br/>")
    public String  sublibraryIndex(){
        return "modules/mms/main/material/sublibrary/index";
    }
}
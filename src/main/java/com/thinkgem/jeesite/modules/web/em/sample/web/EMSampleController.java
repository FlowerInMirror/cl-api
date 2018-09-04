/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.web.em.sample.web;


import com.thinkgem.jeesite.common.web.BaseController;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 工程经理.小样
 * @author     ljc
 * @createTime 2018-8-29 13:43:41
 */
@Controller("emSampleController-web")
@RequestMapping(value = "em/sample-web")
public class EMSampleController extends BaseController {

    @RequestMapping(value = {"{path}"})
    @ApiOperation(value="万能视图",httpMethod="GET",notes="@author  ljc<br/>@version 2018-8-29 13:43:41<br/>")
    public String toEMSampleView(@PathVariable(value = "path") String path, Model model) {
        return "modules/mms/sample/" + path.replace("-","/");
    }
}
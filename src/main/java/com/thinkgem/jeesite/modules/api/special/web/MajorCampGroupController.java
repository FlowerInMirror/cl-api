/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.special.service.MajorCampGroupService;

/**
 * 主营属性组合表Controller
 * @author ljc
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/special/major/majorCampGroup")
public class MajorCampGroupController extends BaseController {

	@Autowired
	private MajorCampGroupService majorCampGroupService;
	


}
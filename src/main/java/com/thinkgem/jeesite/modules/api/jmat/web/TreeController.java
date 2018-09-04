/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeService;

/**
 * 科目树Controller
 * 
 * @author ljc
 * @version 2018-03-15
 */


@Controller
@RequestMapping(value = "tree-api")
public class TreeController extends BaseController {

	@Autowired
	private TreeService treeService;





}
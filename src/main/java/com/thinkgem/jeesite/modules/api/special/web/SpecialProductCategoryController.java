/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.special.service.SpecialProductCategoryService;

/**
 * 专项产品类别表Controller
 * @author ljc
 * @version 2018-08-24
 */
@Controller
@RequestMapping(value = "special_product_category-api")
public class SpecialProductCategoryController extends BaseController {

	@Autowired
	private SpecialProductCategoryService specialProductCategoryService;
	


}
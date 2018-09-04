/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.api.jmat.entity.MatRegionalIncrease;
import com.thinkgem.jeesite.modules.api.jmat.service.MatRegionalIncreaseService;

/**
 * 地区材料涨幅关联表(上月材料地区排行数据)Controller
 * @author  ljc
 * @version 2018-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/matRegionalIncrease")
public class MatRegionalIncreaseController extends BaseController {

	@Autowired
	private MatRegionalIncreaseService matRegionalIncreaseService;
	
	@ModelAttribute
	public MatRegionalIncrease get(@RequestParam(required=false) String id) {
		MatRegionalIncrease entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = matRegionalIncreaseService.get(id);
		}
		if (entity == null){
			entity = new MatRegionalIncrease();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:matRegionalIncrease:view")
	@RequestMapping(value = {"list", ""})
	public String list(MatRegionalIncrease matRegionalIncrease, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MatRegionalIncrease> page = matRegionalIncreaseService.findPage(new Page<MatRegionalIncrease>(request, response), matRegionalIncrease); 
		model.addAttribute("page", page);
		return "modules/jmat/matRegionalIncreaseList";
	}

	@RequiresPermissions("jmat:matRegionalIncrease:view")
	@RequestMapping(value = "form")
	public String form(MatRegionalIncrease matRegionalIncrease, Model model) {
		model.addAttribute("matRegionalIncrease", matRegionalIncrease);
		return "modules/jmat/matRegionalIncreaseForm";
	}

	@RequiresPermissions("jmat:matRegionalIncrease:edit")
	@RequestMapping(value = "save")
	public String save(MatRegionalIncrease matRegionalIncrease, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, matRegionalIncrease)){
			return form(matRegionalIncrease, model);
		}
		matRegionalIncreaseService.save(matRegionalIncrease);
		addMessage(redirectAttributes, "保存地区材料涨幅关联数据成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/matRegionalIncrease/?repage";
	}
	
	@RequiresPermissions("jmat:matRegionalIncrease:edit")
	@RequestMapping(value = "delete")
	public String delete(MatRegionalIncrease matRegionalIncrease, RedirectAttributes redirectAttributes) {
		matRegionalIncreaseService.delete(matRegionalIncrease);
		addMessage(redirectAttributes, "删除地区材料涨幅关联数据成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/matRegionalIncrease/?repage";
	}

}
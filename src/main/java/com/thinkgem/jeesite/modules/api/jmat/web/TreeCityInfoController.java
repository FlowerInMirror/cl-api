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
import com.thinkgem.jeesite.modules.api.jmat.entity.TreeCityInfo;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeCityInfoService;

/**
 * 材料地区信息Controller
 * @author ljc
 * @version 2018-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/treeCityInfo")
public class TreeCityInfoController extends BaseController {

	@Autowired
	private TreeCityInfoService treeCityInfoService;
	
	@ModelAttribute
	public TreeCityInfo get(@RequestParam(required=false) String id) {
		TreeCityInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = treeCityInfoService.get(id);
		}
		if (entity == null){
			entity = new TreeCityInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:treeCityInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TreeCityInfo treeCityInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TreeCityInfo> page = treeCityInfoService.findPage(new Page<TreeCityInfo>(request, response), treeCityInfo); 
		model.addAttribute("page", page);
		return "modules/jmat/treeCityInfoList";
	}

	@RequiresPermissions("jmat:treeCityInfo:view")
	@RequestMapping(value = "form")
	public String form(TreeCityInfo treeCityInfo, Model model) {
		model.addAttribute("treeCityInfo", treeCityInfo);
		return "modules/jmat/treeCityInfoForm";
	}

	@RequiresPermissions("jmat:treeCityInfo:edit")
	@RequestMapping(value = "save")
	public String save(TreeCityInfo treeCityInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, treeCityInfo)){
			return form(treeCityInfo, model);
		}
		treeCityInfoService.save(treeCityInfo);
		addMessage(redirectAttributes, "保存材料地区信息成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/treeCityInfo/?repage";
	}
	
	@RequiresPermissions("jmat:treeCityInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(TreeCityInfo treeCityInfo, RedirectAttributes redirectAttributes) {
		treeCityInfoService.delete(treeCityInfo);
		addMessage(redirectAttributes, "删除材料地区信息成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/treeCityInfo/?repage";
	}

}
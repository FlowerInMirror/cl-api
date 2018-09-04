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
import com.thinkgem.jeesite.modules.api.jmat.entity.CityToMatCity;
import com.thinkgem.jeesite.modules.api.jmat.service.CityToMatCityService;

/**
 * 地区关联Controller
 * @author ljc
 * @version 2018-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/cityToMatCity")
public class CityToMatCityController extends BaseController {

	@Autowired
	private CityToMatCityService cityToMatCityService;
	
	@ModelAttribute
	public CityToMatCity get(@RequestParam(required=false) String id) {
		CityToMatCity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cityToMatCityService.get(id);
		}
		if (entity == null){
			entity = new CityToMatCity();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:cityToMatCity:view")
	@RequestMapping(value = {"list", ""})
	public String list(CityToMatCity cityToMatCity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CityToMatCity> page = cityToMatCityService.findPage(new Page<CityToMatCity>(request, response), cityToMatCity); 
		model.addAttribute("page", page);
		return "modules/jmat/cityToMatCityList";
	}

	@RequiresPermissions("jmat:cityToMatCity:view")
	@RequestMapping(value = "form")
	public String form(CityToMatCity cityToMatCity, Model model) {
		model.addAttribute("cityToMatCity", cityToMatCity);
		return "modules/jmat/cityToMatCityForm";
	}

	@RequiresPermissions("jmat:cityToMatCity:edit")
	@RequestMapping(value = "save")
	public String save(CityToMatCity cityToMatCity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cityToMatCity)){
			return form(cityToMatCity, model);
		}
		cityToMatCityService.save(cityToMatCity);
		addMessage(redirectAttributes, "保存地区关联成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/cityToMatCity/?repage";
	}
	
	@RequiresPermissions("jmat:cityToMatCity:edit")
	@RequestMapping(value = "delete")
	public String delete(CityToMatCity cityToMatCity, RedirectAttributes redirectAttributes) {
		cityToMatCityService.delete(cityToMatCity);
		addMessage(redirectAttributes, "删除地区关联成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/cityToMatCity/?repage";
	}

}
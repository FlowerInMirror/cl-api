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
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialSmallSample;
import com.thinkgem.jeesite.modules.api.jmat.service.MaterialSmallSampleService;

/**
 * 材料小样表Controller
 * @author ljc
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/materialSmallSample")
public class MaterialSmallSampleController extends BaseController {

	@Autowired
	private MaterialSmallSampleService materialSmallSampleService;
	
	@ModelAttribute
	public MaterialSmallSample get(@RequestParam(required=false) String id) {
		MaterialSmallSample entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = materialSmallSampleService.get(id);
		}
		if (entity == null){
			entity = new MaterialSmallSample();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:materialSmallSample:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaterialSmallSample materialSmallSample, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaterialSmallSample> page = materialSmallSampleService.findPage(new Page<MaterialSmallSample>(request, response), materialSmallSample); 
		model.addAttribute("page", page);
		return "modules/jmat/materialSmallSampleList";
	}

	@RequiresPermissions("jmat:materialSmallSample:view")
	@RequestMapping(value = "form")
	public String form(MaterialSmallSample materialSmallSample, Model model) {
		model.addAttribute("materialSmallSample", materialSmallSample);
		return "modules/jmat/materialSmallSampleForm";
	}

	@RequiresPermissions("jmat:materialSmallSample:edit")
	@RequestMapping(value = "save")
	public String save(MaterialSmallSample materialSmallSample, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, materialSmallSample)){
			return form(materialSmallSample, model);
		}
		materialSmallSampleService.save(materialSmallSample);
		addMessage(redirectAttributes, "保存材料小样表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialSmallSample/?repage";
	}
	
	@RequiresPermissions("jmat:materialSmallSample:edit")
	@RequestMapping(value = "delete")
	public String delete(MaterialSmallSample materialSmallSample, RedirectAttributes redirectAttributes) {
		materialSmallSampleService.delete(materialSmallSample);
		addMessage(redirectAttributes, "删除材料小样表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialSmallSample/?repage";
	}

}
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
import com.thinkgem.jeesite.modules.api.jmat.entity.MaterialToSuppliers;
import com.thinkgem.jeesite.modules.api.jmat.service.MaterialToSuppliersService;

/**
 * 材料商材料关联表Controller
 * @author ljc
 * @version 2018-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/jmat/materialToSuppliers")
public class MaterialToSuppliersController extends BaseController {

	@Autowired
	private MaterialToSuppliersService materialToSuppliersService;
	
	@ModelAttribute
	public MaterialToSuppliers get(@RequestParam(required=false) String id) {
		MaterialToSuppliers entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = materialToSuppliersService.get(id);
		}
		if (entity == null){
			entity = new MaterialToSuppliers();
		}
		return entity;
	}
	
	@RequiresPermissions("jmat:materialToSuppliers:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaterialToSuppliers materialToSuppliers, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaterialToSuppliers> page = materialToSuppliersService.findPage(new Page<MaterialToSuppliers>(request, response), materialToSuppliers); 
		model.addAttribute("page", page);
		return "modules/jmat/materialToSuppliersList";
	}

	@RequiresPermissions("jmat:materialToSuppliers:view")
	@RequestMapping(value = "form")
	public String form(MaterialToSuppliers materialToSuppliers, Model model) {
		model.addAttribute("materialToSuppliers", materialToSuppliers);
		return "modules/jmat/materialToSuppliersForm";
	}

	@RequiresPermissions("jmat:materialToSuppliers:edit")
	@RequestMapping(value = "save")
	public String save(MaterialToSuppliers materialToSuppliers, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, materialToSuppliers)){
			return form(materialToSuppliers, model);
		}
		materialToSuppliersService.save(materialToSuppliers);
		addMessage(redirectAttributes, "保存材料商材料关联表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialToSuppliers/?repage";
	}
	
	@RequiresPermissions("jmat:materialToSuppliers:edit")
	@RequestMapping(value = "delete")
	public String delete(MaterialToSuppliers materialToSuppliers, RedirectAttributes redirectAttributes) {
		materialToSuppliersService.delete(materialToSuppliers);
		addMessage(redirectAttributes, "删除材料商材料关联表成功");
		return "redirect:"+Global.getAdminPath()+"/jmat/materialToSuppliers/?repage";
	}

}
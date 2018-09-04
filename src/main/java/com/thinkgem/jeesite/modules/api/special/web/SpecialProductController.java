/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.thinkgem.jeesite.modules.api.special.entity.bo.SpecialProductBo;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.special.service.SpecialProductService;

/**
 * 专项产品Controller
 * @author ljc
 * @version 2018-08-08
 */
@Controller
@RequestMapping(value = "special_product-api")
public class SpecialProductController extends BaseController {

	@Autowired
	private SpecialProductService specialProductService;

	// 专项产品添加

    /**
     * @author     ljc
     * @param      specialProductBo 专项产品业务类
     * @return     InformationBody  响应信息体
     * @createTime 2018-8-8 19:02:05
     */
	@RequestMapping(value = "add")
    @ApiOperation(value="专项:产品添加",httpMethod="POST",notes="@author  ljc<br/>@version 2018-8-8 19:02:10",response = InformationBody.class)
	public @ResponseBody InformationBody addSpecialProduct(@RequestBody SpecialProductBo specialProductBo, HttpServletRequest request) {
        specialProductBo.setDbIP(MMSUtils.getRequestUserIP(request));
        return specialProductService.addSpecialProductoBySpecialProductBo(specialProductBo);
	}

    // 专项产品更新(产品字段更新/产品详情修改/产品上架/下架)
    /**
     * @author ljc
     * @param  specialProductBo 专项产品业务类
     * @return InformationBody  响应信息体
     * @createTime 2018-8-27 23:02:20
     */
	@RequestMapping(value = "update")
    @ApiOperation(value="专项:产品更新(产品字段更新/产品详情修改/产品上架/下架)",httpMethod="POST",notes="@author  ljc<br/>@version 2018-03-16",response = InformationBody.class)
	public@ResponseBody InformationBody  updateSPro( @RequestBody SpecialProductBo specialProductBo,HttpServletRequest request) {
        specialProductBo.setDbIP(MMSUtils.getRequestUserIP(request));
        return specialProductService.updateSProBySpecialProductBo(specialProductBo);
	}

    // 专项产品删除
    /**
     * @author ljc
     * @param  specialProductBo 专项产品业务类
     * @return InformationBody  响应信息体
     * @createTime 2018-8-27 23:02:25
     */
	@RequestMapping(value = "delete")
    @ApiOperation(value="专项:产品删除",httpMethod="POST",notes="产品ID/操作人卡号/操作人地区<br/>@author  ljc<br/>@version 2018-03-16",response = InformationBody.class)
	public@ResponseBody InformationBody deleteSPro(@RequestBody SpecialProductBo specialProductBo,HttpServletRequest request) {
        specialProductBo.setDbIP(MMSUtils.getRequestUserIP(request));
		return specialProductService.deleteSProBySpecialProductBo(specialProductBo);
	}

}
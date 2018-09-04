/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialPictureVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.special.service.SpecialPictureService;

/**
 * 专项图片表Controller
 * @author ljc
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "special_picture-api")
public class SpecialPictureController extends BaseController {

	@Autowired
	private SpecialPictureService specialPictureService;

    @RequestMapping(value={"add"})
    @ApiOperation(value="添加专项相关图片API",httpMethod="POST",notes="添加内容:专项产品ID/专项图片类型:1主图/图片地址<br/>@author  ljc<br/>@version 2018-8-20 11:14:32<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody addSpecialPicture (@ApiParam(required=true,value="专项图片上传(入参模型)",name="specialPictureVo")@RequestBody SpecialPictureVo specialPictureVo,HttpServletRequest request){
        return specialPictureService.addSpecialPictureByEntityVo(specialPictureVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value={"update"})
    @ApiOperation(value="更新专项相关图片API",httpMethod="POST",notes="<br/>@author  ljc<br/>@version 2018-8-20 11:42:27<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody updateSpecialPicture (@ApiParam(required=true,value="专项图片上传(入参模型)",name="specialPictureVo")@RequestBody SpecialPictureVo specialPictureVo,HttpServletRequest request){
        return specialPictureService.updateSpecialPictureByEntityVo(specialPictureVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value={"delete"})
    @ApiOperation(value="删除专项相关图片API",httpMethod="POST",notes="<br/>@author  ljc<br/>@version 2018-8-20 11:42:39<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody deleteSpecialPicture (@ApiParam(required=true,value="专项图片上传(入参模型)",name="specialPictureVo")@RequestBody SpecialPictureVo specialPictureVo,HttpServletRequest request){
        return specialPictureService.deleteSpecialPictureByEntityVo(specialPictureVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

}
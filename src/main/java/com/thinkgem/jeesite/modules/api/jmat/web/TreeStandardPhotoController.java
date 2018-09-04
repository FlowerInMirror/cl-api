/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.PhotoUpload;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeStandardPhotoService;

/**
 * 材料母库照片Controller
 * @author ljc
 * @version 2018-04-12
 */
@Controller
@RequestMapping(value = "tree_standard_photo-api")
public class TreeStandardPhotoController extends BaseController {

	@Autowired
	private TreeStandardPhotoService treeStandardPhotoService;

	// 添加材料照片
    /**
     * @author       ljc
     * @createTime   2018-6-15 16:38:22
     * @param        photoUpload         图片上传POJO
     * @return       InformationBody     响应状态信息
     */
    @RequestMapping(value={"add"})
    @ApiOperation(value="添加材料照片API",httpMethod="POST",notes="添加材料照片<br/>@author  ljc<br/>@version 2018-6-15<br/>",response=InformationBody.class)
    public@ResponseBody InformationBody addMaterialPhoto (@ApiParam(required=true,value="图片上传(入参模型)",name="photoUpload")@RequestBody PhotoUpload photoUpload){
        return treeStandardPhotoService.addMaterialPhotoByPhotoUpload(photoUpload);
    }

    // 更新材料照片
    /**
     * @author       ljc
     * @createTime   2018-6-16 14:08:57
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    @RequestMapping(value={"update"})
    @ApiOperation(value="更新材料照片API",httpMethod="POST",notes="更新材料照片<br/>@author  ljc<br/>@version 2018-6-16<br/>",response=InformationBody.class)
    public@ResponseBody InformationBody updateMaterialPhoto (@ApiParam(required=true,value="图片上传(入参模型)",name="photoUpload")@RequestBody PhotoUpload photoUpload){
        return treeStandardPhotoService.updateMaterialPhotoByPhotoUpload(photoUpload);
    }

    //  删除材料照片
    /**
     * @author       ljc
     * @createTime   2018-6-22 09:43:39
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    @RequestMapping(value={"delete"})
    @ApiOperation(value="删除材料照片API",httpMethod="POST",notes="删除材料照片<br/>@author  ljc<br/>@version 2018-6-22 09:45:02<br/>",response=InformationBody.class)
    public@ResponseBody InformationBody deleteMaterialPhoto (@ApiParam(required=true,value="图片上传(入参模型)",name="photoUpload")@RequestBody PhotoUpload photoUpload){
        return treeStandardPhotoService.deleteMaterialPhotoByPhotoUpload(photoUpload);
    }

    // 更新材料照片参数/描述
    /**
     * @author       ljc
     * @createTime   2018-6-16 15:32:07
     * @param        photoUpload          图片上传POJO
     * @return       InformationBody      响应状态信息
     */
    @RequestMapping(value={"para/update"})
    @ApiOperation(value="更新材料照片参数/描述API",httpMethod="POST",notes="更新材料照片参数/描述<br/>@author  ljc<br/>@version 2018-6-16<br/>",response=InformationBody.class)
    public@ResponseBody InformationBody saveMaterialPhotoOterText (@ApiParam(required=true,value="图片上传(入参模型)",name="photoUpload")@RequestBody PhotoUpload photoUpload){
        return treeStandardPhotoService.updateMaterialPhotoOterText(photoUpload);
    }




}
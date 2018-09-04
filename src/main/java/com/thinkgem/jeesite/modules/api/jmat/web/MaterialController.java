/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.MaterialBO;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.thinkgem.jeesite.modules.api.project.dao.RsglDiQuDao;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.service.MaterialService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 材料子库Controller
 * @author ljc
 * @version 2018-03-16
 */
@Controller
@RequestMapping(value = "material-api")
public class MaterialController extends BaseController {

	@Autowired
	private MaterialService materialService;
	@Autowired // 公用控制层
    private CommonController commonController;


    @Autowired//地区(工程管理-原人事)
    private RsglDiQuDao rsglDiQuDao;

	// 保存材料基本信息分项（品牌|规格|小样）（子库）
    /**
     * @author   ljc
     * @param    materialBO      材料相关信息包装类(更新实体)
     * @return   InformationBody 响应状态信息
     * @createTime   2018-6-22 13:07:52
     */
    @RequestMapping(value={"brand_item/save"})
    @ApiOperation(value="保存材料基本信息分项（品牌|规格）（子库）API",httpMethod="POST",notes="保存材料基本信息分项（品牌|规格）（子库）<br/>@author  ljc<br/>@version 2018-6-22 13:12:57<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody saveMateriaByMaterialBO(@ApiParam(required=true,value="材料相关信息包装类(更新实体)",name="materialVo")@RequestBody MaterialBO materialBO){
        return materialService.saveMateriaByMaterialBO(materialBO);
    }

    // 添加品牌（子库）（多品牌）
    /**
     * @author   ljc
     * @param    materialBO      材料相关信息包装类(更新实体)
     * @return   InformationBody 响应状态信息
     * @createTime   2018-6-26 11:28:08
     */
    @RequestMapping(value={"brand_item/add"})
    @ApiOperation(value="添加品牌（子库>地区>档次）API",httpMethod="POST",notes="添加品牌（子库>地区>档次）<br/>@author  ljc<br/>@version 2018-6-26 11:28:08<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody addMateriaByMaterialBO(@ApiParam(required=true,value="材料相关信息包装类(更新实体)",name="materialVo")@RequestBody MaterialBO materialBO){
        List<Map<String, Object>> allRsglDiQu = commonController.getCitys();
        for (Map<String, Object> map : allRsglDiQu) {
            Integer cityID = (Integer)map.get("cityID");
            String cityCode = (String)map.get("cityCode");
            if (cityID.equals(materialBO.getmCityID()))
                materialBO.setCityCode(cityCode);
        }
        return materialService.addMateriaByMaterialBO(materialBO);
    }

    // 删除品牌（子库>地区>档次）
    /**
     * @author   ljc
     * @param    materialBO      材料相关信息包装类(更新实体)
     * @return   InformationBody 响应状态信息
     * @createTime   2018-6-27 14:47:58
     */
    @RequestMapping(value={"brand_item/delete"})
    @ApiOperation(value="删除品牌（子库>地区>档次）API",httpMethod="POST",notes="删除品牌（子库>地区>档次）<br/>@author  ljc<br/>@version 2018-6-27 14:48:04<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody deleteBrandItem(@ApiParam(required=true,value="材料相关信息包装类(更新实体)",name="materialVo")@RequestBody MaterialBO materialBO) throws InterruptedException {
        return materialService.deleteBrandItem(materialBO);
    }


    // 入库品牌（子库>地区>档次）
    /**
     * @author   ljc
     * @param    materialBO      材料相关信息包装类(更新实体)
     * @return   InformationBody 响应状态信息
     * @createTime   2018-6-29 11:34:57
     */
    @RequestMapping(value={"brand_item/into"})
    @ApiOperation(value="品牌入库（子库>地区>档次）API",httpMethod="POST",notes="品牌入库（子库>地区>档次）<br/>@author  ljc<br/>@version 2018-6-29 11:35:02<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody intoBrandItem(@ApiParam(required=true,value="材料相关信息包装类(更新实体)",name="materialVo")@RequestBody MaterialBO materialBO){
        return materialService.intoBrandItem(materialBO);
    }

    // 更新小样类型
    /**
     * @author   ljc
     * @param    matID      材料ID
     * @param    sampleType 小样类型
     * @return   InformationBody 响应状态信息
     * @createTime  2018-7-19 18:29:49
     */
    @RequestMapping(value={"update/sample_type"})
    @ApiOperation(value="品牌入库（子库>地区>档次）API",httpMethod="POST",notes="品牌入库（子库>地区>档次）<br/>@author  ljc<br/>@version 2018-6-29 11:35:02<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody updateSampleType(
            @ApiParam(required=true,value="材料ID",name="matID")@RequestParam String matID,
            @ApiParam(required=true,value="小样类型(1需要,2不需要)",name="sampleType")@RequestParam Integer sampleType, HttpSession session){
        return materialService.updateSampleType(matID,sampleType, MMSUtils.getCardNumber(session));
    }

    //  预操作(mShoppingState 类型:0取消预下架/2预下架) @author ljc @createTiem 2018-9-3 18:30:06
    @RequestMapping(value={"brand_item/pre_operation"})
    @ApiOperation(value="品牌预处理:预下架与取消预下架（子库>地区>档次）API",httpMethod="POST",notes="品牌入库（子库>地区>档次）<br/>@author  ljc<br/>@version 2018-6-29 11:35:02<br/>",response=InformationBody.class)
    public@ResponseBody
    InformationBody matPreOperation(@ApiParam(required=true,value="材料相关信息包装类(更新实体)",name="materialVo")@RequestBody MaterialBO materialBO){
        return materialService.matPreOperationByMaterialBO(materialBO);
    }

	

}
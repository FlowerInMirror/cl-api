/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.special.entity.SpecialProduct;
import com.thinkgem.jeesite.modules.api.special.entity.bo.SpecialProductBo;
import com.thinkgem.jeesite.modules.api.special.entity.qo.CategoryCorrelationQo;
import com.thinkgem.jeesite.modules.api.special.entity.vo.SpecialProductVo;
import com.thinkgem.jeesite.modules.api.special.service.SpecialService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 专项Controller
 * @author ljc
 * @version 2018-8-9 10:41:05
 */
@Controller
@RequestMapping(value = "special-api")
public class SpecialController extends BaseController {

	@Autowired
	private SpecialService specialService; // 专项服务对象

    // 专项二段: 产品
    /**
     * @author     ljc
     * @param      cityID     城市ID
     * @param      spDealerID 经销商ID
     * @createTime 2018-8-26 20:16:58
     */
	@RequestMapping(value = "two/product")
    @ApiOperation(value="专项二段: 产品API",httpMethod="GET",notes="通过城市ID,经销商ID检索:产品名称/产品状态/材料商ID/组合最低价 ~ 组合最高价<br/>@author  ljc<br/>@version 2018-8-26 20:17:03",response = SpecialProduct.class)
	public@ResponseBody InformationBody  twoProduct(
	        @ApiParam(required=false,value="城市ID",name="cityID")@RequestParam(required = false) Integer cityID,
            @ApiParam(required=true,value="经销商ID",name="spDealerID")@RequestParam Integer spDealerID) {
        return specialService.getSpecialTwoProduct(cityID,spDealerID);
	}

    // 专项三段: 产品项
    /**
     * @author     ljc
     * @param      spID  专项产品ID
     * @createTime 2018-8-9 11:08:38
     */
    @RequestMapping(value = "three/product/items")
    @ApiOperation(value="专项三段: 产品项API",httpMethod="GET",notes="通过产品ID检索内容<br/>@author  ljc<br/>@version  2018-8-9 11:08:38",response = SpecialProductVo.class)
    public@ResponseBody InformationBody  twoProduct(
            @ApiParam(required=true,value="专项产品ID",name="spID")@RequestParam String spID) {
        return specialService.getSpecialThreeProductItems(spID);
    }

    // 添加页面:类别组  @author ljc @createTime 2018-8-26 18:52:25
    @RequestMapping(value = "open/product/add/categorys")
    @ApiOperation(value="添加专项产品:获取所有类别与类别项API",httpMethod="GET",notes="根据主营二级科目ID/材料商ID检索<br/>@author  ljc<br/>@version  2018-8-22 13:31:07",response = SpecialProductVo.class)
    public@ResponseBody InformationBody  findAddCategorys(
            @ApiParam(required=true,value="专项主营二级科目ID",name="treeTwoID")@RequestParam String treeTwoID,
            @ApiParam(required=true,value="专项产品材料商ID",name="zxUserID")@RequestParam Integer zxUserID) {
        return specialService.findAddCategorysByTreeTwoIDAndZXUserID(treeTwoID,zxUserID);
    }

    // 浏览页面:数据回显  @author ljc @createTime 2018-8-26 18:52:38
    @RequestMapping(value = "open/product/browsing")
    @ApiOperation(value="浏览页面:数据回显API",httpMethod="GET",notes="内容:专项产品基础相关数据/类别集相关数据<br/>@author  ljc<br/>@version  2018-8-26 20:20:02",response = SpecialProductBo.class)
    public@ResponseBody InformationBody  openProductBrowsing(
            @ApiParam(required=true,value="专项产品ID",name="proID")@RequestParam String proID) {
        return specialService.findOpenProductBrowsingDataByProID(proID);
    }

    // 浏览页面:检索类别相关(1.类别是否不可选2.获取组合套餐价) @author ljc @createTime 2018-8-27 10:37:57
    @RequestMapping(value = "search/category")
    @ApiOperation(value="浏览页面:检索类别相关((1.类别是否不可选2.获取组合套餐价))API",httpMethod="POST",notes="获取组合套餐价以及类别是否不可选类别项组,通过专项产品ID以及类别项组检索,还有检索类别(1.检索是否不可选类别项组,2检索组合套餐价格.).<br/>@author  ljc<br/>@version  2018-8-27 10:37:57",response = InformationBody.class)
    public@ResponseBody InformationBody  searchCategoryCorrelation(
            @ApiParam(required=true,value="类别相关检索类",name="categoryCorrelationQo")@RequestBody CategoryCorrelationQo categoryCorrelationQo) {
        return specialService.searchCategoryCorrelationByProIDAndCategoryItems(categoryCorrelationQo);
    }

    // 编辑页面:数据回显  @author ljc @createTime 2018-8-27 19:34:57
    @RequestMapping(value = "open/product/edit")
    @ApiOperation(value="编辑页面:数据回显API",httpMethod="GET",notes="内容:专项产品基础相关数据/类别集相关数据/组合套餐数据<br/>@author  ljc<br/>@version  2018-8-27 19:34:57",response = SpecialProductBo.class)
    public@ResponseBody InformationBody  openProductEdit(
            @ApiParam(required=true,value="专项产品ID",name="proID")@RequestParam String proID) {
        return specialService.findOpenProductEditDataByProID(proID);
    }

}
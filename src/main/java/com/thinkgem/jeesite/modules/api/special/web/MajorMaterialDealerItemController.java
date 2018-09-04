/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.special.web;

import javax.servlet.http.HttpServletRequest;

import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.special.entity.vo.MajorMaterialDealerItemVo;
import com.thinkgem.jeesite.modules.web.mms.utils.MMSUtils;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.special.service.MajorMaterialDealerItemService;

/**
 * 材料商自定义分项表Controller
 * @author ljc
 * @version 2018-08-23
 */
@Controller
@RequestMapping(value = "major_material_dealer_item-api")
public class MajorMaterialDealerItemController extends BaseController {

	@Autowired
	private MajorMaterialDealerItemService majorMaterialDealerItemService;

    @RequestMapping(value = "add")
    @ApiOperation(value="专项产品:添加材料商自定义(类别项)API",httpMethod="POST",notes="保存内容:专项产品ID/属性类别ID/名称<br/>@author  ljc<br/>@version 2018-8-23 20:26:21",response = InformationBody.class)
    public @ResponseBody
    InformationBody addDealerCategoryItem(@RequestBody MajorMaterialDealerItemVo majorMaterialDealerItemVo, HttpServletRequest request) {
        return majorMaterialDealerItemService.addDealerCategoryItemByEntityVo(majorMaterialDealerItemVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value = "update")
    @ApiOperation(value="专项产品:更新材料商自定义(类别项)API",httpMethod="POST",notes="@author  ljc<br/>@version 2018-8-23 20:26:26",response = InformationBody.class)
    public @ResponseBody InformationBody updateDealerCategoryItemByEntityVo(@RequestBody MajorMaterialDealerItemVo majorMaterialDealerItemVo, HttpServletRequest request) {
        return majorMaterialDealerItemService.updateDealerCategoryItemByEntityVo(majorMaterialDealerItemVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }

    @RequestMapping(value = "delete")
    @ApiOperation(value="专项产品:删除材料商自定义(类别项)API",httpMethod="POST",notes="@author  ljc<br/>@version 2018-8-23 20:26:29",response = InformationBody.class)
    public @ResponseBody InformationBody deleteDealerCategoryItemByEntityVo(@RequestBody MajorMaterialDealerItemVo majorMaterialDealerItemVo, HttpServletRequest request) {
        return majorMaterialDealerItemService.deleteDealerCategoryItemByEntityVo(majorMaterialDealerItemVo.setDbIP(MMSUtils.getRequestUserIP(request)));
    }


}
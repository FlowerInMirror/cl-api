package com.thinkgem.jeesite.modules.api.alliance.web;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.alliance.service.AllianceService;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 集团联盟Controller
 * @author ljc
 * @createTime 2018-7-17 15:38:58
 */
@Controller
@RequestMapping(value = "alliance-api")
@Api(value="集团联盟Controller",description="联盟API")
public class AllianceController extends BaseController {

    @Autowired // 联盟服务类
    private AllianceService allianceService;

    /**
     * 集团联盟:获取全部二级分类
     * @author     ljc
     * @createTime 2018-7-17 15:45:41
     */
    @RequestMapping(value={"get/all_treetwo"})
    @ApiOperation(value="集团联盟:获取全部二级分类API",httpMethod="GET",notes="二级类名称/二级类ID/父级ID/编码<br/>@author  ljc<br/>@version 2018-7-17 16:02:19<br/>")
    public@ResponseBody InformationBody getAllTreeTwoData(){
        return  allianceService.getAllTreeTwoData();
    }

    /**
     * 集团联盟:查找二级类与城市下的所有材料
     * @author     ljc
     * @param      treeTwoIDs 二级类ID(可提交多个)
     * @param      cityID    城市ID
     * @createTime 2018-7-17 15:45:41
     */
    @RequestMapping(value={"find/all_material"},method = RequestMethod.POST)
    @ApiOperation(value="集团联盟:二级类与城市下的所有材料(支持多二级类检索逗号分隔 注:方法限定接受POST请求)",httpMethod="POST",notes="<br/>@author  ljc<br/>@version 2018-7-11 15:39:07<br/>@update 2018-7-18 13:40:21<br/>品牌名称/规格名称/总成本(成本价加安装)/材料ID/报价/型号名称/主图地址/材料名称")
    public@ResponseBody InformationBody findAllBrandByTreeTwoIDAndCityID(
            @ApiParam(required=true,value="二级类ID(支持多二级类检索,使用','号分隔)",name="treeTwoIDs")@RequestParam String treeTwoIDs,
            @ApiParam(required=true,value="城市ID",name="cityID")@RequestParam Integer cityID){
        return  allianceService.findAllBrandByTreeTwoIDAndCityID(treeTwoIDs,cityID);
    }



}

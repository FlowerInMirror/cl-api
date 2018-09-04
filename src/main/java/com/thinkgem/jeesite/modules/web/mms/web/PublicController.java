/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.web.mms.web;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.web.mms.pojo.WPSLoginUser;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 材料管理系统WEB公共-Controller
 * @author     ljc
 * @version    1.0
 * @createTime 2018-5-3 14:01:52
 */
@Controller
@RequestMapping(value = "public-web")
public class PublicController extends BaseController {

    public static final String ADM_ROLE = Global.getConfig("sys.admin");//人事管理角色编码
    public static final String MAT_ROLE = Global.getConfig("sys.material");//材料管理角色编码

    /**
     * 页面LOGO跳转,根据当前登录用户角色编号进行跳转.
     * @author      ljc
     * @version     1.0
     * @createTime  2018-4-26 14:37:41
     * @return      逻辑视图
     */
    @RequestMapping(value = {"to/index"})
    @ApiOperation(value="页面LOGO跳转",httpMethod="GET",notes="页面LOGO跳转<br/>@author ljc<br/>@version 2018-4-26<br/>")
    public String  matMerchantIndex(HttpSession session){
        WPSLoginUser loginUser = (WPSLoginUser) session.getAttribute("user");
        if(loginUser!=null){

            //获取当前登录用户角色ID,根据当前登录用户角色编码,执行不同的页面跳转.
            String userRoleCode = loginUser.getJuese().toString();
            //材料
            if (MAT_ROLE.equals(userRoleCode)||ADM_ROLE.equals(userRoleCode))
                return "redirect:/mat-web/index";
        }
        //会话关闭,重新登录.
        return "redirect:/error/to/403";
    }

    // 子库相关视图 @author ljc @createTime 2018-9-3 13:54:09
    @RequestMapping(value = {"sublibrary/{path}"})
    @ApiOperation(value="子库-相关视图",httpMethod="GET",notes="@author  ljc<br/>@version 2018-9-3 13:35:04<br/>")
    public String toEMSampleView(@PathVariable(value = "path") String path, Model model) {
        return "modules/mms/main/material/sublibrary/" + path.replace("-","/");
    }

    // --- 子库地区 AngularJS 路由支持 begin
    /**
     * 子库城市一段列表
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-10 21:05:57
     */
    @RequestMapping(value = {"sublibrary/city_one_list"})
    @ApiOperation(value="去往子库城市一段列表",httpMethod="GET",notes="跳转-子库城市一段列表@author  ljc<br/>@version 2018-5-11<br/>")
    public String toCityOneList() {
        return "modules/mms/main/material/sublibrary/city/one/list";
    }

    /**
     * 子库城市二段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-16 14:52:10
     */
    @RequestMapping(value = {"sublibrary/city_two_all"})
    @ApiOperation(value="去往子库城市二段",httpMethod="GET",notes="跳转-子库城市二段@author  ljc<br/>@version 2018-5-16<br/>")
    public String toCityTwoSection() {
        return "modules/mms/main/material/sublibrary/city/two/all";
    }

    /**
     * 档次 子库城市二段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-22 21:09:14
     */
    @RequestMapping(value = {"sublibrary/city_two_level"})
    @ApiOperation(value="去往'档次'子库城市二段",httpMethod="GET",notes="跳转-子库城市二段@author  ljc<br/>@version 2018-5-22<br/>")
    public String toCityTwoSectionLevel() {
        return "modules/mms/main/material/sublibrary/city/two/level";
    }

    /**
     * 成本 子库城市二段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-22 21:09:14
     */
    @RequestMapping(value = {"sublibrary/city_two_cost"})
    @ApiOperation(value="去往'成本'子库城市二段",httpMethod="GET",notes="跳转-'成本'子库城市二段@author  ljc<br/>@version 2018-5-22<br/>")
    public String toCityTwoSectionCost() {
        return "modules/mms/main/material/sublibrary/city/two/cost";
    }

    /**
     * 应用 子库城市二段
     * @author     ljc
     * @version    1.0
     * @createTime 2018-5-22 21:09:14
     */
    @RequestMapping(value = {"sublibrary/city_two_app"})
    @ApiOperation(value="去往'应用'子库城市二段",httpMethod="GET",notes="跳转-'应用'子库城市二段@author  ljc<br/>@version 2018-5-22<br/>")
    public String toCityTwoSectionApp() {
        return "modules/mms/main/material/sublibrary/city/two/app";
    }

    // --- 子库地区 相关分布页

    // --- 状态(三段 处理)
    @RequestMapping(value = {"sublibrary/city_three_status/{path}"})
    @ApiOperation(value="子库城市状态(三段 处理)",httpMethod="GET",notes="子库城市状态(三段 处理)@author  ljc<br/>@version 2018-7-24 17:05:06<br/>")
    public String toCityThreeStatusHandle(@PathVariable(value = "path") String path) {
        return "modules/mms/main/material/sublibrary/city/three/status/" + path;
    }

    // --- 平台(三段 基础,质量标准,小样标准,包装标准,对比标准,搜索词)
    @RequestMapping(value = {"sublibrary/city_three_platform/{path}"})
    @ApiOperation(value="子库城市平台(三段 基础,质量标准,小样标准,包装标准,对比标准,搜索词)",httpMethod="GET",notes="子库城市平台(三段 基础,质量标准,小样标准,包装标准,对比标准,搜索词)@author  ljc<br/>@version 2018-5-25<br/>")
    public String toCityThreePlatformBase(@PathVariable(value = "path")String path) {
        return "modules/mms/main/material/sublibrary/city/three/platform/" + path;
    }

    // --- 平台(四段 货架)
    @RequestMapping(value = {"sublibrary/city_four_platform/{path}"})
    @ApiOperation(value="子库城市平台(四段 货架)",httpMethod="GET",notes="子库城市平台(四段 货架)@author  ljc<br/>@version 2018-7-20 16:34:42<br/>")
    public String toCityFourPlatformFrame(@PathVariable(value = "path")String path) {
        return "modules/mms/main/material/sublibrary/city/four/platform/" + path;
    }

    // --- 档次(三段 质量标准,A档,B档,C档,添加品牌)
    @RequestMapping(value = {"sublibrary/city_three_level/{path}"})
    @ApiOperation(value="子库城市档次三段(三段 质量标准,A档,B档,C档,添加品牌)",httpMethod="GET",notes="子库城市档次三段(三段 质量标准,A档,B档,C档,添加品牌)@author  ljc<br/>@version 2018-6-6<br/>")
    public String toCityThreeLevelSta(@PathVariable(value = "path") String path) {
        return "modules/mms/main/material/sublibrary/city/three/level/" + path;
    }

    // --- 报价(三段 基础,A档,B档,C档)
    @RequestMapping(value = {"sublibrary/city_three_cost/{path}"})
    @ApiOperation(value="子库城市报价(三段 基础,A档,B档,C档)",httpMethod="GET",notes="子库城市报价(三段 基础,A档,B档,C档)@author  ljc<br/>@version 2018-6-6<br/>@updateTime 2018-7-30 10:59:03")
    public String toCityThreeCostItem(@PathVariable(value = "path") String path) {
        return "modules/mms/main/material/sublibrary/city/three/cost/" + path;
    }

    // --- 报价(四段 品牌项)
    @RequestMapping(value = {"sublibrary/city_four_cost/{path}"})
    @ApiOperation(value="子库城市报价(四段 品牌项)",httpMethod="GET",notes="跳转-'需求-品牌项'子库城市报价四段@author  ljc<br/>@version 2018-7-10 09:05:22<br/>")
    public String toCityFourCostItem(@PathVariable(value = "path") String path) {
        return "modules/mms/main/material/sublibrary/city/four/cost/" + path;
    }

    /**
     * 子库回访
     * @author ljc
     * @return 回访视图
     * @createTime 2018-7-25 16:49:30
     */
    @RequestMapping(value = {"sublibrary/city/visit"})
    @ApiOperation(value="去往材料(平台右上角)列表",httpMethod="GET",notes="跳转-去往材料(平台右上角)列表@author  ljc<br/>@version 2018-7-3<br/>")
    public String toSublibraryVisit() {
        return "modules/mms/main/material/sublibrary/city/visit";
    }

    /**
     * 子库列表
     * @author     ljc
     * @version    1.0
     * @createTime 2018-7-3 14:07:37
     */
    @RequestMapping(value = {"commons/material_list","material/list"})
    @ApiOperation(value="去往材料(平台右上角)列表",httpMethod="GET",notes="跳转-去往材料(平台右上角)列表@author  ljc<br/>@version 2018-7-3<br/>")
    public String toMaterialList() {
        return "modules/mms/main/material/list";
    }

    // --- 专项相关 ---
    @RequestMapping(value = {"special/{path}"})
    @ApiOperation(value="专项视图",httpMethod="GET",notes="专项视图@author  ljc<br/>@version 2018-8-2 13:19:50<br/>")
    public String toSpecialView(@PathVariable(value = "path") String path) {
        return "modules/mms/special/" + path.replace("-","/");
    }

    // --- 主营相关 ---
    @RequestMapping(value = {"major/{path}"})
    @ApiOperation(value="主营视图",httpMethod="GET",notes="@author  ljc<br/>@version 2018-8-18 16:46:30<br/>")
    public String toMajorView(@PathVariable(value = "path") String path) {
        return "modules/mms/major/" + path.replace("-","/");
    }

    // --- 小样相关 ---
    @RequestMapping(value = {"sample/{path}"})
    @ApiOperation(value="主营视图",httpMethod="GET",notes="@author  ljc<br/>@version 2018-8-27 14:37:32<br/>")
    public String toSampleView(@PathVariable(value = "path") String path) {
        return "modules/mms/sample/" + path.replace("-","/");
    }

}
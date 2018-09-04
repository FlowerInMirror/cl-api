/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.web.mms.web;


import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpRequestUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.api.jmat.dao.CityToMatCityDao;
import com.thinkgem.jeesite.modules.web.mms.pojo.WPSLoginUser;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 材料平台WPS登录Controller
 *
 * @author     ljc
 * @version    1.0
 * @createTime 2018-4-24 10:26:02
 */
@Controller
@RequestMapping(value = "wps_login-api")
public class WPSLoginController extends BaseController {

    public static final String ADM_ROLE = Global.getConfig("sys.admin");       //管理角色编码(818)     新增:964
    public static final String MAT_ROLE = Global.getConfig("sys.material");    //材料管理角色编码(902)  新增:890

    @Autowired
    private CityToMatCityDao cityToMatCityDao; // 地区城市关联表

    /**
     * 材料平台WPS登录接口
     *
     * @author     ljc
     * @version    1.0
     * @createTime 2018-4-24 19:16:40
     * @return     逻辑结果集视图
     */
    @RequestMapping(value = {""})
    @ApiOperation(value = "材料平台WPS登录接口API", httpMethod = "GET", notes = "根据当前登录用户的不同角色ID,进行不同登录跳转.<br/>@author ljc<br/>@version 2018-4-26<br/>")
    public String wpsLogin(
            @ApiParam(required = true, value = "账号", name = "card") @RequestParam String card,
            @ApiParam(required = true, value = "密码", name = "password") @RequestParam String password,
            @ApiParam(required = true, value = "WPS-GM(防止免登陆)", name = "GM") @RequestParam String GM,
            HttpSession session, Model model) {
        // WPS登录用户
        WPSLoginUser loginUser = null;

        // 开发账号
        if ("admin".equals(card)&&"admin@1234".equals(password)){
            loginUser = new WPSLoginUser("Developer","集团工程",Integer.parseInt(ADM_ROLE),card,password,"39");
            session.setAttribute("user",loginUser);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(JSON.toJSONString(loginUser),password);
            subject.login(usernamePasswordToken);
            return "redirect:/mat-web/index";
        }

        // 请求人事接口,根据登录账号密码,获取当前登录用户信息.
        loginUser = HttpRequestUtils.sendHttpGetGetBean("http://api.p.rx/api/price/GetUserInfoJson?card=" + card + "&password=" + password + "&GM=" + GM.replace(' ', '+'), WPSLoginUser.class);
        if (loginUser != null) {

            // 组织 数据到WPS登录用户实体
            loginUser.setCardNumber(card);
            loginUser.setPassword(password);
            loginUser.setCiphertext(password);

            // 用戶角色編碼
            String userRoleCode = loginUser.getJuese().toString();

            // 新增 权限编码
            if ("890".equals(userRoleCode)){// 材料平台890
                userRoleCode = MAT_ROLE;
                loginUser.setJuese(Integer.parseInt(MAT_ROLE));
            }
            if ("964".equals(userRoleCode)){// 材料经理964
                userRoleCode = ADM_ROLE;
                loginUser.setJuese(Integer.parseInt(ADM_ROLE));
            }

            // Shrio 权限控制 begin

            // )1.设置Shiro Session过期时间 24小时
            SecurityUtils.getSubject().getSession().setTimeout(86400000);

            // )2.获得subject对象  => 每个session都有一个对应的subject
            Subject subject = SecurityUtils.getSubject();

            // )3.将用户和密码封装到token中
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(JSON.toJSONString(loginUser),password);

            // )4.登录去安全管理中心认证
            subject.login(usernamePasswordToken);

            // 设置当前登录的用户,到Session域中.
            session.setAttribute("user", loginUser);

            // 根据当前登录用户角色编码,执行不同的页面跳转.
            if (MAT_ROLE.equals(userRoleCode) || ADM_ROLE.equals(userRoleCode)) return "redirect:/mat-web/index";

        }
        // 没有权限,禁止访问.
        return "redirect:/error/to/403";
    }

    // 工程经理.材料 登录接口
    @RequestMapping(value = {"em/material"})
    @ApiOperation(value = "工程经理.材料(EngineeringManager.Material) 登录接口WPS登录接口API", httpMethod = "GET", notes = "<br/>@author ljc<br/>@version 2018-4-26<br/>")
    public String engineeringManagerMaterialWpsLogin(
            @ApiParam(required = true, value = "账号", name = "card") @RequestParam String card,
            @ApiParam(required = true, value = "密码", name = "password") @RequestParam String password,
            @ApiParam(required = true, value = "WPS-GM(防止免登陆)", name = "GM") @RequestParam String GM,
            HttpSession session, Model model) {

        // WPS登录用户
        WPSLoginUser loginUser = null;

        // 开发账号
        if ("admin".equals(card)&&"admin@1234".equals(password)){
            loginUser = new WPSLoginUser("Developer","集团工程",Integer.parseInt(ADM_ROLE),card,password,"12");
            session.setAttribute("user",loginUser);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(JSON.toJSONString(loginUser),password);
            subject.login(usernamePasswordToken);
            return "redirect:/em/material-web/index";
        }

        // 请求人事接口,根据登录账号密码,获取当前登录用户信息.
        loginUser = HttpRequestUtils.sendHttpGetGetBean("http://api.p.rx/api/price/GetUserInfoJson?card=" + card + "&password=" + password + "&GM=" + GM.replace(' ', '+'), WPSLoginUser.class);

        // 没有权限,禁止访问.
        if (loginUser == null)  return "redirect:/error/to/403";

        // 验证通过 组织 数据到WPS登录用户实体
        loginUser.setCardNumber(card);
        loginUser.setPassword(password);
        loginUser.setCiphertext(password);
        // 转换用户地区为材料管理系统地区
        loginUser.setU_diqu(getMatCity(loginUser.getU_diqu()));

        // Shrio 权限控制 begin

        // )1.设置Shiro Session过期时间 24小时
        SecurityUtils.getSubject().getSession().setTimeout(86400000);

        // )2.获得subject对象  => 每个session都有一个对应的subject
        Subject subject = SecurityUtils.getSubject();

        // )3.将用户和密码封装到token中
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(JSON.toJSONString(loginUser),password);

        // )4.登录去安全管理中心认证
        subject.login(usernamePasswordToken);

        // 设置当前登录的用户,到Session域中.
        session.setAttribute("user", loginUser);

        return "redirect:/em/material-web/index";
    }

    // 工程经理.小样 登录接口
    @RequestMapping(value = {"em/sample"})
    @ApiOperation(value = "工程经理.小样(EngineeringManager.Sample) 登录接口WPS登录接口API", httpMethod = "GET", notes = "<br/>@author ljc<br/>@version 2018-8-29 13:37:08<br/>")
    public String engineeringManagerSamplepsLogin(
            @ApiParam(required = true, value = "账号", name = "card") @RequestParam String card,
            @ApiParam(required = true, value = "密码", name = "password") @RequestParam String password,
            @ApiParam(required = true, value = "WPS-GM(防止免登陆)", name = "GM") @RequestParam String GM,
            HttpSession session, Model model) {

        // WPS登录用户
        WPSLoginUser loginUser = null;

        // 开发账号
        if ("admin".equals(card)&&"admin@1234".equals(password)){
            loginUser = new WPSLoginUser("Developer","集团工程",Integer.parseInt(ADM_ROLE),card,password,"39");
            session.setAttribute("user",loginUser);
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(JSON.toJSONString(loginUser),password);
            subject.login(usernamePasswordToken);
            return "redirect:/em/sample-web/index";
        }

        // 请求人事接口,根据登录账号密码,获取当前登录用户信息.
        loginUser = HttpRequestUtils.sendHttpGetGetBean("http://api.p.rx/api/price/GetUserInfoJson?card=" + card + "&password=" + password + "&GM=" + GM.replace(' ', '+'), WPSLoginUser.class);

        // 没有权限,禁止访问.
        if (loginUser == null)  return "redirect:/error/to/403";

        // 验证通过 组织 数据到WPS登录用户实体
        loginUser.setCardNumber(card);
        loginUser.setPassword(password);
        loginUser.setCiphertext(password);
        // 转换用户地区为材料管理系统地区
        loginUser.setU_diqu(getMatCity(loginUser.getU_diqu()));


        // Shrio 权限控制 begin

        // )1.设置Shiro Session过期时间 24小时
        SecurityUtils.getSubject().getSession().setTimeout(86400000);

        // )2.获得subject对象  => 每个session都有一个对应的subject
        Subject subject = SecurityUtils.getSubject();

        // )3.将用户和密码封装到token中
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(JSON.toJSONString(loginUser),password);

        // )4.登录去安全管理中心认证
        subject.login(usernamePasswordToken);

        // 设置当前登录的用户,到Session域中.
        session.setAttribute("user", loginUser);

        return "redirect:/em/sample-web/index";
    }


    // 城市转材料城市 @author ljc @createTime 2018-8-29 15:07:02
    private String getMatCity(String cityID){
        Integer resultCityID = null;
        if (StringUtils.isNotBlank(cityID))
        {
            resultCityID = cityToMatCityDao.findMatCityIDByCityID(Integer.parseInt(cityID));
        }
        if (resultCityID != null){
            return resultCityID.toString();
        }

        return cityID;
    }



}
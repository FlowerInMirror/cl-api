package com.thinkgem.jeesite.modules.web.mms.utils;

import com.thinkgem.jeesite.modules.web.mms.pojo.WPSLoginUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 材料管理系统工具类
 * @author  ljc
 * @version 2018-5-29 21:45:18
 */
public class MMSUtils {
    /**
     * 获取当前登录用户卡号
     * @param session 当前登录用户http会话对象
     * @return 用户卡号
     */
    public static  String getCardNumber(HttpSession session){
        Object user = session.getAttribute("user");
        if (null != user) {
            WPSLoginUser wpsLoginUser = (WPSLoginUser) user;
            return wpsLoginUser.getCardNumber();
        }
        return null;
    }
    /**
     * 获取当前登录用户
     * @param session 当前登录用户http会话对象
     * @return 当前登录用户
     */
    public static  WPSLoginUser getCurrentUser(HttpSession session){
        Object user = session.getAttribute("user");
        if (null != user) {
            WPSLoginUser wpsLoginUser = (WPSLoginUser) user;
            return wpsLoginUser;
        }
        return null;
    }

    /**
     * 判断当前运行系统是否是windows系统
     */
    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
    }

    // 获取当前访客IP
    /**
     * 获取当前访客IP
     * @author ljc
     * @param  request 超文本传输协议小服务程序请求对象
     * @return 当前访客IP
     */
    public static String getRequestUserIP(HttpServletRequest request){
        String ip=request.getHeader("x-forwarded-for");
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("X-Real-IP");
        }
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        return ip;
    }


}

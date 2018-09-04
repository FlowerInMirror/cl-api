package com.thinkgem.jeesite.modules.web.mms.security;



import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.modules.web.mms.pojo.WPSLoginUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

/**
 * 材料管理系统 安全认证实现类
 * @author ljc
 * @version 2018-6-7 11:59:21
 */
@Service(value = "mmsAuthorizingRealm")
public class MMSAuthorizingRealm extends AuthorizingRealm{

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken paramAuthenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) paramAuthenticationToken;
        WPSLoginUser user = JSON.parseObject(token.getUsername(), WPSLoginUser.class);

        // TODO 关于权限认证应该存放到此处

        // 参数1: 认证信息实体,通常填当前登陆对象
        // 参数2: 正确的密码,securityManager会帮我们判断密码是否正确
        // 参数3: 填写当前realm的名字   this.getName()=>返回当前realm的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        return info;
    }
}

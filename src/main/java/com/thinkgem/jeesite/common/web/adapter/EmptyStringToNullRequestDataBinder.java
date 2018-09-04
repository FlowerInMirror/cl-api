package com.thinkgem.jeesite.common.web.adapter;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.ServletRequest;

/**
 * 空字符串到空请求数据绑定器
 *
 * @author     ljc
 * @describe   继承自ExtendedServletRequestDataBinder，主要用来自定义数据绑定处理
 * @createTime 2018-6-16 13:15:02
 */
public class EmptyStringToNullRequestDataBinder extends ExtendedServletRequestDataBinder {
    public EmptyStringToNullRequestDataBinder(Object target, String objectName) {
        super(target, objectName);
    }

    protected void addBindValues(MutablePropertyValues mpvs, ServletRequest request) {
        super.addBindValues(mpvs, request);
        for (PropertyValue propertyValue : mpvs.getPropertyValueList()) {
            if (propertyValue.getValue().equals(""))
                propertyValue.setConvertedValue(null);
        }
    }
}

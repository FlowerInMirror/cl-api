package com.thinkgem.jeesite.common.web.adapter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 空字符串到Null处理器注册器
 * @author     ljc
 * @describe   要从HandlerAdapter里系统自带的处理器说起。我这边系统默认带了24个处理器，其中有两个ServletModelAttributeMethodProcessor，也就是我们自定义处理器继承的系统处理器。SpringMVC处理请求参数是轮询每一个处理器，看是否支持，也就是supportsParameter方法， 如果返回true，就交给你出来，并不会问下面的处理器。这就导致了如果我们简单的把我们的自定义处理器加到HandlerAdapter的Resolver列中是不行的，需要加到第一个去。
 * @createTime 2018-6-16 13:20:19
 */
public abstract class EmptyStringToNullProcessorRegistry implements ApplicationContextAware, BeanFactoryPostProcessor {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);

        List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter.getArgumentResolvers();


        List<HandlerMethodArgumentResolver> newResolvers = new ArrayList<HandlerMethodArgumentResolver>();

        for (HandlerMethodArgumentResolver resolver : resolvers) {
            newResolvers.add(resolver);
        }
        EmptyStringToNullModelAttributeMethodProcessor processor = new EmptyStringToNullModelAttributeMethodProcessor(true);
        processor.setApplicationContext(applicationContext);
        newResolvers.add(0, processor);
        requestMappingHandlerAdapter.setArgumentResolvers(Collections.unmodifiableList(newResolvers));
    }
}

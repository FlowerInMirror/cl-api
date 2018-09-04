package com.thinkgem.jeesite.common.swagger;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Bean;  
  
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;  
import com.mangofactory.swagger.models.dto.ApiInfo;  
import com.mangofactory.swagger.plugin.EnableSwagger;  
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;  
  
@EnableSwagger  
public class SwaggerConfig {  
  
    private SpringSwaggerConfig springSwaggerConfig;  
  
    /** 
     * Required to autowire SpringSwaggerConfig 
     * 要求自动测试SpringSwaggerConfig
     */  
    @Autowired  
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)  
    {  
        this.springSwaggerConfig = springSwaggerConfig;  
    }  
  
    /** 
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc 
     * 每个 Swagger SpringMvcPlugin bean都是由swagger-mvc选择的。
     * framework - allowing for multiple swagger groups i.e. same code base 
     * 框架-允许多个swargger，即相同的代码基
     * multiple swagger resource listings. 
     * 多个swargger资源列表。
     */  
    @Bean  
    public SwaggerSpringMvcPlugin customImplementation()  
    {  
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)  
                .apiInfo(apiInfo()) 
                .includePatterns(".*-.*");
//                .includePatterns(".*?");  
    }  
  
    private ApiInfo apiInfo()  
    {  
        ApiInfo apiInfo = new ApiInfo(  
                "材料子库",  
                "API测试平台",  
                "My Apps API terms of service",  
                "http://10.10.3.167:3110/Login/jumpLogin?CardNo=00007983&PassWord=999999999",  
                "瑞祥佳艺材料子库",  
                "京ICP备20140191号");  
        return apiInfo;  
    }  
}  
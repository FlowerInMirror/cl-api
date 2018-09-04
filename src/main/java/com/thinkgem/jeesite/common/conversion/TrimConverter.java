package com.thinkgem.jeesite.common.conversion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 空串转换器
 * S: 传递过来的数据类型
 * T：转换后的类型
 * @author ljc
 * @version 1.0
 * @createTime 2018-5-10 16:20:43
 */
public class TrimConverter implements Converter<String, String> {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String convert(String source) {
        //去掉前后空格 如果本身就是空格 返回NUll
        try {
            if (null != source) {
                source = source.trim();
                if (!"".equals(source))
                    return source;
            }
        } catch (Exception e) {
            logger.error("TrimConverter,转化异常.", e);
        }
        return null;
    }

}

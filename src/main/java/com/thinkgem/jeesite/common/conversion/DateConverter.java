package com.thinkgem.jeesite.common.conversion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
/**
 * 日期转换器
 * @author     ljc
 * @version    1.0
 * @createTime 2018-4-11 13:55:54
 */
public class DateConverter implements Converter<String, Date>{
	
	//	  implements Converter<S, T>
	//	  S:source,需要转换的源的类型
	//	  T:target,需要转换的目标类型

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Date convert(String source) {
		try {
			// 把字符串转换为日期类型
			DateFormat df;
			if(source.length()==10){
				df = new SimpleDateFormat("yyyy-MM-dd");
			}else{
				df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			return df.parse(source);
		} catch (Exception e) {
			logger.error("自定义-日期类型Converter,转化异常.", e);
		}
		// 如果转换异常则返回空
		return null;
	}

}

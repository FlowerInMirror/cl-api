package com.thinkgem.jeesite.modules.api.jmat.pojo.commons;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * API调用的返回信息POJO
 * 
 * @author  ljc
 * @version 2018-03-15
 *
 */
@ApiModel(value="API调用的返回信息POJO")
public class InformationBody implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * API调用返回的状态码
	 * 0:成功
	 * -1:失败
	 */
	@ApiModelProperty("状态码:0 成功（默認）/-1 失败")
	private Integer statusCode = 0;
	/**
	 * API调用返回的状态信息
	 * success:成功
	 * fail:失败
	 * error:错误
	 */
	@ApiModelProperty("状态信息（默認：success）")

	private Object statusMsg = "success";
	/**
	 * API调用返回的响应原始内容，可为空
	 */
	@ApiModelProperty("响应体")
	private Object body;

	public InformationBody() {
		super();
	}


	public Object getBody() {
		return body;
	}


	public InformationBody setBody(Object body) {
		this.body = body;
		return this;
	}


	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Object getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(Object statusMsg) {
		this.statusMsg = statusMsg;
	}


}

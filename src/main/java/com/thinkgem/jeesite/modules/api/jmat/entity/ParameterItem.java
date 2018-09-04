/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料参数项Entity
 * @author ljc
 * @version 2018-05-29
 */
public class ParameterItem extends DataEntity<ParameterItem> {
	
	private static final long serialVersionUID = 1L;
	private Integer paraitemId;		// ID
	private Integer paraitemParaid;		// 参数ID
	private String paraitemName;		// 名称
	private Integer paraitemSort;		// 排序
	private Integer paraitemJincheng;		// 进程：0正常、1删除  （默认0）
	private Date paraitemCreatetime;		// 创建时间    （默认getdate()）
	private Date paraitemUpdatetime;		// 更新时间
	
	public ParameterItem() {
		super();
	}

	public ParameterItem(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getParaitemId() {
		return paraitemId;
	}

	public void setParaitemId(Integer paraitemId) {
		this.paraitemId = paraitemId;
	}
	
	public Integer getParaitemParaid() {
		return paraitemParaid;
	}

	public void setParaitemParaid(Integer paraitemParaid) {
		this.paraitemParaid = paraitemParaid;
	}
	
	@Length(min=0, max=20, message="名称长度必须介于 0 和 20 之间")
	public String getParaitemName() {
		return paraitemName;
	}

	public void setParaitemName(String paraitemName) {
		this.paraitemName = paraitemName;
	}
	
	public Integer getParaitemSort() {
		return paraitemSort;
	}

	public void setParaitemSort(Integer paraitemSort) {
		this.paraitemSort = paraitemSort;
	}
	
	public Integer getParaitemJincheng() {
		return paraitemJincheng;
	}

	public void setParaitemJincheng(Integer paraitemJincheng) {
		this.paraitemJincheng = paraitemJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getParaitemCreatetime() {
		return paraitemCreatetime;
	}

	public void setParaitemCreatetime(Date paraitemCreatetime) {
		this.paraitemCreatetime = paraitemCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getParaitemUpdatetime() {
		return paraitemUpdatetime;
	}

	public void setParaitemUpdatetime(Date paraitemUpdatetime) {
		this.paraitemUpdatetime = paraitemUpdatetime;
	}
	
}
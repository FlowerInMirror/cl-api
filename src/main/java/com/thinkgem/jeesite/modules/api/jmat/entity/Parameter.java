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
 * 材料参数Entity
 * @author ljc
 * @version 2018-03-23
 */
public class Parameter extends DataEntity<Parameter> {
	
	private static final long serialVersionUID = 1L;
	private Integer paraId;		// ID
	private String paraName;		// 名称
	private String paraCode;		// 参数表示：
	private Integer paraUnit;		// 单位ID
	private String paraUnitname;		// 单位名称
	private Integer paraType;		// 类型：1文本参数，2下拉参数
	private Integer paraFixedstate;		// 是否固定：1固定项，2固定项&amp;对比项  （默认0）
	private Integer paraMuststate;		// 是否必填：1必填  （默认0）
	private Integer paraJincheng;		// 进程：0正常、1删除  （默认0）
	private Date paraCreatetime;		// 创建时间    （默认getdate()）
	private Date paraUpdatetime;		// 更新时间
	
	public Parameter() {
		super();
	}

	public Parameter(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getParaId() {
		return paraId;
	}

	public void setParaId(Integer paraId) {
		this.paraId = paraId;
	}
	
	@Length(min=0, max=20, message="名称长度必须介于 0 和 20 之间")
	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	
	@Length(min=0, max=20, message="参数表示：长度必须介于 0 和 20 之间")
	public String getParaCode() {
		return paraCode;
	}

	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	
	public Integer getParaUnit() {
		return paraUnit;
	}

	public void setParaUnit(Integer paraUnit) {
		this.paraUnit = paraUnit;
	}
	
	@Length(min=0, max=10, message="单位名称长度必须介于 0 和 10 之间")
	public String getParaUnitname() {
		return paraUnitname;
	}

	public void setParaUnitname(String paraUnitname) {
		this.paraUnitname = paraUnitname;
	}
	
	public Integer getParaType() {
		return paraType;
	}

	public void setParaType(Integer paraType) {
		this.paraType = paraType;
	}
	
	public Integer getParaFixedstate() {
		return paraFixedstate;
	}

	public void setParaFixedstate(Integer paraFixedstate) {
		this.paraFixedstate = paraFixedstate;
	}
	
	public Integer getParaMuststate() {
		return paraMuststate;
	}

	public void setParaMuststate(Integer paraMuststate) {
		this.paraMuststate = paraMuststate;
	}
	
	public Integer getParaJincheng() {
		return paraJincheng;
	}

	public void setParaJincheng(Integer paraJincheng) {
		this.paraJincheng = paraJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getParaCreatetime() {
		return paraCreatetime;
	}

	public void setParaCreatetime(Date paraCreatetime) {
		this.paraCreatetime = paraCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getParaUpdatetime() {
		return paraUpdatetime;
	}

	public void setParaUpdatetime(Date paraUpdatetime) {
		this.paraUpdatetime = paraUpdatetime;
	}
	
}
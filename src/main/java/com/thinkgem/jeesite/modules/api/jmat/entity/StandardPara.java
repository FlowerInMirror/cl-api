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
 * 材料标准参数Entity
 * @author ljc
 * @version 2018-03-21
 */
public class StandardPara extends DataEntity<StandardPara> {
	
	private static final long serialVersionUID = 1L;
	private Integer spId;		// ID
	private String spTreefour;		// 四级ID（规格）
	private String spTsid;		// 材料母库ID|材料ID(子库)
	private Integer spClass;		// 分类：1母库，2子库
	private Integer spParaid;		// 参数ID
	private String spParavalue;		// 参数值
	private Integer spJincheng;		// 进程：0正常、1删除  （默认0）
	private Date spCreatetime;		// 创建时间    （默认getdate()）
	private Date spUpdatetime;		// 更新时间
	private Integer spMatlevel;		// 档次（1:A档，2:B档，4:C档（可位运算|）
	private Integer spApppush;		// 是否推送：1推送   （默认0）
	
	public StandardPara() {
		super();
	}

	public StandardPara(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	
	public String getSpTreefour() {
		return spTreefour;
	}

	public void setSpTreefour(String spTreefour) {
		this.spTreefour = spTreefour;
	}
	
	public String getSpTsid() {
		return spTsid;
	}

	public void setSpTsid(String spTsid) {
		this.spTsid = spTsid;
	}
	
	public Integer getSpClass() {
		return spClass;
	}

	public void setSpClass(Integer spClass) {
		this.spClass = spClass;
	}
	
	public Integer getSpParaid() {
		return spParaid;
	}

	public void setSpParaid(Integer spParaid) {
		this.spParaid = spParaid;
	}
	
	@Length(min=0, max=20, message="参数值长度必须介于 0 和 20 之间")
	public String getSpParavalue() {
		return spParavalue;
	}

	public void setSpParavalue(String spParavalue) {
		this.spParavalue = spParavalue;
	}
	
	public Integer getSpJincheng() {
		return spJincheng;
	}

	public void setSpJincheng(Integer spJincheng) {
		this.spJincheng = spJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpCreatetime() {
		return spCreatetime;
	}

	public void setSpCreatetime(Date spCreatetime) {
		this.spCreatetime = spCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSpUpdatetime() {
		return spUpdatetime;
	}

	public void setSpUpdatetime(Date spUpdatetime) {
		this.spUpdatetime = spUpdatetime;
	}
	
	public Integer getSpMatlevel() {
		return spMatlevel;
	}

	public void setSpMatlevel(Integer spMatlevel) {
		this.spMatlevel = spMatlevel;
	}
	
	public Integer getSpApppush() {
		return spApppush;
	}

	public void setSpApppush(Integer spApppush) {
		this.spApppush = spApppush;
	}
	
}
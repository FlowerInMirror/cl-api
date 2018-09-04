/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 科目树设置Entity
 * 
 * @author ljc
 * @version 2018-03-17
 */
public class TreeSst extends DataEntity<TreeSst> {

	private static final long serialVersionUID = 1L;
	private String tsId; // 主键
	private String tsTreeid; // 科目树ID
	private Integer tsTreelevel; // 树等级
	private Integer tsType; // 设定类型：1加工费无限制，2报价推送（不取舍小数），4报价推送（直接进位）,8电线标识 ,16成品安装 （|位运算））
	private Integer tsTypestate; // 设定状态（16|1成品，2非成品） （默认0）
	private Double tsFixedvalue; // 加工费：限制倍值
	private String tsAppcitys; // 加工费：适用地区 格式 |12|16|
	private Date tsCreatetime; // 创建时间 （默认getdate()）
	private Date tsUpdatetime; // 更新时间
	private Integer tsJincheng; // 进程：0正常、1删除 （默认0）

	public TreeSst() {
		super();
	}

	public TreeSst(String id) {
		super(id);
	}

	public String getTsId() {
		return tsId;
	}

	public void setTsId(String tsId) {
		this.tsId = tsId;
	}

	public String getTsTreeid() {
		return tsTreeid;
	}

	public void setTsTreeid(String tsTreeid) {
		this.tsTreeid = tsTreeid;
	}

	public Integer getTsTreelevel() {
		return tsTreelevel;
	}

	public void setTsTreelevel(Integer tsTreelevel) {
		this.tsTreelevel = tsTreelevel;
	}

	public Integer getTsType() {
		return tsType;
	}

	public void setTsType(Integer tsType) {
		this.tsType = tsType;
	}

	public Integer getTsTypestate() {
		return tsTypestate;
	}

	public void setTsTypestate(Integer tsTypestate) {
		this.tsTypestate = tsTypestate;
	}

	public Double getTsFixedvalue() {
		return tsFixedvalue;
	}

	public void setTsFixedvalue(Double tsFixedvalue) {
		this.tsFixedvalue = tsFixedvalue;
	}

	@Length(min = 0, max = 2000, message = "加工费：适用地区  格式 |12|16|长度必须介于 0 和 2000 之间")
	public String getTsAppcitys() {
		return tsAppcitys;
	}

	public void setTsAppcitys(String tsAppcitys) {
		this.tsAppcitys = tsAppcitys;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTsCreatetime() {
		return tsCreatetime;
	}

	public void setTsCreatetime(Date tsCreatetime) {
		this.tsCreatetime = tsCreatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTsUpdatetime() {
		return tsUpdatetime;
	}

	public void setTsUpdatetime(Date tsUpdatetime) {
		this.tsUpdatetime = tsUpdatetime;
	}

	public Integer getTsJincheng() {
		return tsJincheng;
	}

	public void setTsJincheng(Integer tsJincheng) {
		this.tsJincheng = tsJincheng;
	}

}
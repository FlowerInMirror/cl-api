/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 信息审核验收表Entity
 * @author ljc
 * @version 2018-06-16
 */
public class Infocheckandaccept extends DataEntity<Infocheckandaccept> {
	
	private static final long serialVersionUID = 1L;
	private Integer icaId;		// ID
	private String icaTreeid;		// 树ID
	private Integer icaCityid;		// 地区ID
	private String icaMatid;		// 材料ID
	private Integer icaObject;		// 对象：1一审（照片），2二审（品牌材料），4三审（质量标准），8四审（材料商）
	private Integer icaType;		// 类型：100完结 （集团工程 1品牌、2型号、3约定） （集团主案  1材料、10平台-平台、11平台-照片、12平台对比）
	private Integer icaState;		// 状态：0未审核，2不合格，3合格  （默认0）
	private Date icaTime;		// 提交时间（重新开始审核时，更新时间）（完结时间的时间小于其他类型时间表示需要重新完结） （默认getdate()）
	private Date icaChecktime;		// 审核时间
	private String icaCheckremark;		// 审核备注
	
	public Infocheckandaccept() {
		super();
	}

	public Infocheckandaccept(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getIcaId() {
		return icaId;
	}

	public void setIcaId(Integer icaId) {
		this.icaId = icaId;
	}
	
	public String getIcaTreeid() {
		return icaTreeid;
	}

	public void setIcaTreeid(String icaTreeid) {
		this.icaTreeid = icaTreeid;
	}
	
	public Integer getIcaCityid() {
		return icaCityid;
	}

	public void setIcaCityid(Integer icaCityid) {
		this.icaCityid = icaCityid;
	}
	
	public String getIcaMatid() {
		return icaMatid;
	}

	public void setIcaMatid(String icaMatid) {
		this.icaMatid = icaMatid;
	}
	
	public Integer getIcaObject() {
		return icaObject;
	}

	public void setIcaObject(Integer icaObject) {
		this.icaObject = icaObject;
	}
	
	public Integer getIcaType() {
		return icaType;
	}

	public void setIcaType(Integer icaType) {
		this.icaType = icaType;
	}
	
	public Integer getIcaState() {
		return icaState;
	}

	public void setIcaState(Integer icaState) {
		this.icaState = icaState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIcaTime() {
		return icaTime;
	}

	public void setIcaTime(Date icaTime) {
		this.icaTime = icaTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIcaChecktime() {
		return icaChecktime;
	}

	public void setIcaChecktime(Date icaChecktime) {
		this.icaChecktime = icaChecktime;
	}
	
	@Length(min=0, max=2000, message="审核备注长度必须介于 0 和 2000 之间")
	public String getIcaCheckremark() {
		return icaCheckremark;
	}

	public void setIcaCheckremark(String icaCheckremark) {
		this.icaCheckremark = icaCheckremark;
	}
	
}
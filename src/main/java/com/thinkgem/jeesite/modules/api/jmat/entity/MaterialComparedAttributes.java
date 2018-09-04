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
 * 属性对比（对比图）Entity
 * @author ljc
 * @version 2018-03-19
 */
public class MaterialComparedAttributes extends DataEntity<MaterialComparedAttributes> {
	
	private static final long serialVersionUID = 1L;
	private Integer mcaId;		// ID
	private String mcaTreeid;		// 科目树ID
	private String mcaName;		// 对比名称
	private Integer mcaType;		// 对比类型（1标准，2材料参数）
	private String mcaStandardid;		// 标准ID
	private Integer mcaParaid;		// 材料参数ID
	private Integer mcaStatus;		// 进程：0正常、1删除  （默认0）
	private Date mcaTime;		// 时间（可用作排序）    （默认getdate()）
    private String mcaKey; // KEY（用于审核存储）

    public String getMcaKey() {
        return mcaKey;
    }

    public void setMcaKey(String mcaKey) {
        this.mcaKey = mcaKey;
    }

    public MaterialComparedAttributes() {
		super();
	}

	public MaterialComparedAttributes(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getMcaId() {
		return mcaId;
	}

	public void setMcaId(Integer mcaId) {
		this.mcaId = mcaId;
	}
	
	public String getMcaTreeid() {
		return mcaTreeid;
	}

	public void setMcaTreeid(String mcaTreeid) {
		this.mcaTreeid = mcaTreeid;
	}
	
	@Length(min=0, max=50, message="对比名称长度必须介于 0 和 50 之间")
	public String getMcaName() {
		return mcaName;
	}

	public void setMcaName(String mcaName) {
		this.mcaName = mcaName;
	}
	
	public Integer getMcaType() {
		return mcaType;
	}

	public void setMcaType(Integer mcaType) {
		this.mcaType = mcaType;
	}
	
	public String getMcaStandardid() {
		return mcaStandardid;
	}

	public void setMcaStandardid(String mcaStandardid) {
		this.mcaStandardid = mcaStandardid;
	}
	
	public Integer getMcaParaid() {
		return mcaParaid;
	}

	public void setMcaParaid(Integer mcaParaid) {
		this.mcaParaid = mcaParaid;
	}
	
	public Integer getMcaStatus() {
		return mcaStatus;
	}

	public void setMcaStatus(Integer mcaStatus) {
		this.mcaStatus = mcaStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMcaTime() {
		return mcaTime;
	}

	public void setMcaTime(Date mcaTime) {
		this.mcaTime = mcaTime;
	}
	
}
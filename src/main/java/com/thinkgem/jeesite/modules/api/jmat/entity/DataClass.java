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
 * 数据行为分类Entity
 * @author ljc
 * @version 2018-04-09
 */
public class DataClass extends DataEntity<DataClass>  {
	
	private static final long serialVersionUID = 1L;
	private Integer dcId;		// 分类ID
	private String dcClasscode;		// 分类Code（已此做查询条件）
	private String dcClassname;		// 分类名称
	private Integer dcParentid;		// 父级ID
	private Integer dcJincheng;		// 进程：0正常、1删除  （默认0）
	private Date dcCreatetime;		// 创建时间    （默认getdate()）
	private Date dcUpdatetime;		// 更新时间
	
	public DataClass() {
		super();
	}

	public DataClass(String id){
		super(id);
	}

	@NotNull(message="分类ID不能为空")
	public Integer getDcId() {
		return dcId;
	}

	public void setDcId(Integer dcId) {
		this.dcId = dcId;
	}
	
	@Length(min=0, max=50, message="分类Code（已此做查询条件）长度必须介于 0 和 50 之间")
	public String getDcClasscode() {
		return dcClasscode;
	}

	public void setDcClasscode(String dcClasscode) {
		this.dcClasscode = dcClasscode;
	}
	
	@Length(min=0, max=50, message="分类名称长度必须介于 0 和 50 之间")
	public String getDcClassname() {
		return dcClassname;
	}

	public void setDcClassname(String dcClassname) {
		this.dcClassname = dcClassname;
	}
	
	public Integer getDcParentid() {
		return dcParentid;
	}

	public void setDcParentid(Integer dcParentid) {
		this.dcParentid = dcParentid;
	}
	
	public Integer getDcJincheng() {
		return dcJincheng;
	}

	public void setDcJincheng(Integer dcJincheng) {
		this.dcJincheng = dcJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDcCreatetime() {
		return dcCreatetime;
	}

	public void setDcCreatetime(Date dcCreatetime) {
		this.dcCreatetime = dcCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDcUpdatetime() {
		return dcUpdatetime;
	}

	public void setDcUpdatetime(Date dcUpdatetime) {
		this.dcUpdatetime = dcUpdatetime;
	}
	
}
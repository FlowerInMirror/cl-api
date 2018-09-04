/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 材料小样表Entity
 *
 * @author ljc
 * @version 2018-07-06
 */
public class MaterialSmallSample extends DataEntity<MaterialSmallSample> {

    private static final long serialVersionUID = 1L;
    private Integer mssId;        // ID
    private String mssMatid;        // 材料ID
    private Integer mssSamplestatus;        // 小样状态：0未,1有，2无  （默认0）
    private Integer mssStatus;        // 进程：0正常、1删除  （默认0）
    private Date mssCreatetime;        // 创建时间   （默认getdate()）
    private Date mssUpdatetime;        // 更新时间
    private Integer mssGroundingStatus;        // 上架状态: 0未,1已上架,2未上架 (默认0)

    public Integer getMssGroundingStatus() {
        return mssGroundingStatus;
    }

    public void setMssGroundingStatus(Integer mssGroundingStatus) {
        this.mssGroundingStatus = mssGroundingStatus;
    }

    public MaterialSmallSample() {
        super();
    }

    public MaterialSmallSample(String id) {
        super(id);
    }

    @NotNull(message = "ID不能为空")
    public Integer getMssId() {
        return mssId;
    }

    public void setMssId(Integer mssId) {
        this.mssId = mssId;
    }

    public String getMssMatid() {
        return mssMatid;
    }

    public void setMssMatid(String mssMatid) {
        this.mssMatid = mssMatid;
    }

    public Integer getMssSamplestatus() {
        return mssSamplestatus;
    }

    public void setMssSamplestatus(Integer mssSamplestatus) {
        this.mssSamplestatus = mssSamplestatus;
    }

    public Integer getMssStatus() {
        return mssStatus;
    }

    public void setMssStatus(Integer mssStatus) {
        this.mssStatus = mssStatus;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getMssCreatetime() {
        return mssCreatetime;
    }

    public void setMssCreatetime(Date mssCreatetime) {
        this.mssCreatetime = mssCreatetime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getMssUpdatetime() {
        return mssUpdatetime;
    }

    public void setMssUpdatetime(Date mssUpdatetime) {
        this.mssUpdatetime = mssUpdatetime;
    }

}
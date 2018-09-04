package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.status;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 材料处理记录模型
 * @author ljc
 * @createTime 2018-7-26 13:40:10
 */
@ApiModel(value = "材料处理记录模型包装类")
public class MatHandleVo implements Serializable {

    private static final long serialVersionUID = 1755516776879887281L;
    @ApiModelProperty("ID")
    private Integer vmhId;
    @ApiModelProperty("处理主对象")
    private String vmhMainobject;
    @ApiModelProperty("处理辅助对象")
    private String vmhAuxiliaryobject;
    @ApiModelProperty("处理内容")
    private String vmhContent;
    @ApiModelProperty("处理类型：1正常，2问题，3异常,4.下架")
    private Integer vmhType;
    @ApiModelProperty("处理人卡号")
    private String vmhUsercard;
    @ApiModelProperty("处理人类型")
    private Integer vmhUsertype;
    @ApiModelProperty("处理城市ID")
    private Integer vmhCityid;
    @ApiModelProperty("进程状态：0正常、1删除  （默认0）")
    private Integer vmhStatus;
    @ApiModelProperty("创建时间    （默认getdate()）")
    private Date vmhCreatetime;
    @ApiModelProperty("更新时间")
    private Date vmhUpdatetime;

    public MatHandleVo() {
        super();
    }

    public Integer getVmhId() {
        return vmhId;
    }

    public void setVmhId(Integer vmhId) {
        this.vmhId = vmhId;
    }

    public String getVmhMainobject() {
        return vmhMainobject;
    }

    public void setVmhMainobject(String vmhMainobject) {
        this.vmhMainobject = vmhMainobject;
    }

    public String getVmhAuxiliaryobject() {
        return vmhAuxiliaryobject;
    }

    public void setVmhAuxiliaryobject(String vmhAuxiliaryobject) {
        this.vmhAuxiliaryobject = vmhAuxiliaryobject;
    }

    public String getVmhContent() {
        return vmhContent;
    }

    public void setVmhContent(String vmhContent) {
        this.vmhContent = vmhContent;
    }

    public Integer getVmhType() {
        return vmhType;
    }

    public void setVmhType(Integer vmhType) {
        this.vmhType = vmhType;
    }

    public String getVmhUsercard() {
        return vmhUsercard;
    }

    public void setVmhUsercard(String vmhUsercard) {
        this.vmhUsercard = vmhUsercard;
    }

    public Integer getVmhUsertype() {
        return vmhUsertype;
    }

    public void setVmhUsertype(Integer vmhUsertype) {
        this.vmhUsertype = vmhUsertype;
    }

    public Integer getVmhCityid() {
        return vmhCityid;
    }

    public void setVmhCityid(Integer vmhCityid) {
        this.vmhCityid = vmhCityid;
    }

    public Integer getVmhStatus() {
        return vmhStatus;
    }

    public void setVmhStatus(Integer vmhStatus) {
        this.vmhStatus = vmhStatus;
    }

    public Date getVmhCreatetime() {
        return vmhCreatetime;
    }

    public void setVmhCreatetime(Date vmhCreatetime) {
        this.vmhCreatetime = vmhCreatetime;
    }

    public Date getVmhUpdatetime() {
        return vmhUpdatetime;
    }

    public void setVmhUpdatetime(Date vmhUpdatetime) {
        this.vmhUpdatetime = vmhUpdatetime;
    }

    // --- 附加属性 ---

    @ApiModelProperty("回访类型:回访类型：1项目，2材料商，3配送单,100材料子库,200集团主案")
    private Integer visitType;

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }
}

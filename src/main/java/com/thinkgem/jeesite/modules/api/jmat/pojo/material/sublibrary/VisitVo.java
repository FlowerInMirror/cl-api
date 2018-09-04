package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 回访包装类
 * @author ljc
 * @createTime 2018-7-25 13:10:29
 */
@ApiModel(value = "回访包装类")
public class VisitVo {

    @ApiModelProperty("ID")
    private Integer vId;
    @ApiModelProperty("回访类型：1项目，2材料商，3配送单,100材料子库,200集团主案")
    private Integer vType;
    @ApiModelProperty("操作人类型：1集团，2地方，3集团工程：>=10相减当二次类型")
    private Integer vOperattype = 1;
    @ApiModelProperty("项目ID,平台:四级科目ID")
    private String vPiid;
    @ApiModelProperty("材料商ID")
    private Integer vUserid;
    @ApiModelProperty("标记：1正常，2异常，3问题")
    private Integer vMark;
    @ApiModelProperty("回访内容")
    private String vContent;
    @ApiModelProperty("回访人")
    private String vKahao;
    @ApiModelProperty("进程：0正常、1删除  （默认0）")
    private Integer vJincheng;
    @ApiModelProperty("创建时间（格式:ms,默认getdate()）")
    private Date vCreatetime;
    @ApiModelProperty("更新时间(格式:ms)")
    private Date vUpdatetime;

    @ApiModelProperty("创建时间(格式:yyyy-MM-dd HH:mm:ss)")
    private String createTime;
    @ApiModelProperty("更新时间(格式:yyyy-MM-dd HH:mm:ss)")
    private String updateTime;

    // --- 构造方法开始 ---

    // 同步处理记录 类型/操作人类型/主ID/城市ID/标记/内容/卡号
    public VisitVo(Integer vType, Integer vOperattype, String vPiid, Integer vUserid, Integer vMark, String vContent, String vKahao) {
        this.vType = vType;
        this.vOperattype = vOperattype;
        this.vPiid = vPiid;
        this.vUserid = vUserid;
        this.vMark = vMark;
        this.vContent = vContent;
        this.vKahao = vKahao;
    }

    public VisitVo() {
        super();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getvOperattype() {
        return vOperattype;
    }

    public void setvOperattype(Integer vOperattype) {
        this.vOperattype = vOperattype;
    }

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Integer getvType() {
        return vType;
    }

    public void setvType(Integer vType) {
        this.vType = vType;
    }

    public String getvPiid() {
        return vPiid;
    }

    public void setvPiid(String vPiid) {
        this.vPiid = vPiid;
    }

    public Integer getvUserid() {
        return vUserid;
    }

    public void setvUserid(Integer vUserid) {
        this.vUserid = vUserid;
    }

    public Integer getvMark() {
        return vMark;
    }

    public void setvMark(Integer vMark) {
        this.vMark = vMark;
    }

    public String getvContent() {
        return vContent;
    }

    public void setvContent(String vContent) {
        this.vContent = vContent;
    }

    public String getvKahao() {
        return vKahao;
    }

    public void setvKahao(String vKahao) {
        this.vKahao = vKahao;
    }

    public Integer getvJincheng() {
        return vJincheng;
    }

    public void setvJincheng(Integer vJincheng) {
        this.vJincheng = vJincheng;
    }

    public Date getvCreatetime() {
        return vCreatetime;
    }

    public void setvCreatetime(Date vCreatetime) {
        this.vCreatetime = vCreatetime;
    }

    public Date getvUpdatetime() {
        return vUpdatetime;
    }

    public void setvUpdatetime(Date vUpdatetime) {
        this.vUpdatetime = vUpdatetime;
    }
}

package com.thinkgem.jeesite.modules.api.jmat.pojo.commons;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 子库最近操作数据(包含数据行为常量值)
 *
 * @author ljc
 * @version 1.0
 * @createTime 2018-4-9 20:50:08
 */
@ApiModel(value = "子库最近操作数据POJO")
public class SubLibraryRecentData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 城市ID
     */
    @ApiModelProperty("城市ID")
    private Integer cityID;
    /**
     * 开始行数(算法:每页显示记录数*当前页码数+1)
     */
    @ApiModelProperty("开始行数")
    private Integer beginRowNum;// 开始行数(算法:每页显示记录数*当前页码数+1)
    /**
     * 结束行数(算法:每页显示记录+(每页显示记录数*当前页码数))
     */
    @ApiModelProperty("结束行数")
    private Integer endRowNum;// 结束行数(算法:每页显示记录+(每页显示记录数*当前页码数))

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date terminalTime;


    /**
     * 添加品牌
     */
    public static final Integer M_A_SL_BRAND_ADD = 1;
    /**
     * 更新品牌
     */
    public static final Integer M_A_SL_BRAND_SAVE = 2;
    /**
     * 品牌照片操作
     */
    public static final Integer M_A_SL_BRAND_PHOTO = 3;
    /**
     * 价格调整操作
     */
    public static final Integer M_A_SL_BRAND_PRICE = 4;
    /**
     * 冻结操作
     */
    public static final Integer S_A_MG_FREEZE = 5;
    /**
     * 黑名单操作
     */
    public static final Integer S_A_MG_BLACKLIST = 6;
    /**
     * 状态操作
     */
    public static final Integer S_A_MG_STATE = 7;
    /**
     * 置顶材料
     */
    public static final Integer M_A_SL_MAT_TOP = 8;
    /**
     * 审核标记（材料树-规格级-地区）
     */
    public static final Integer M_A_TC_VERIFICATION_MARK = 9;
    /**
     * 搜索词 （材料树-规格级）
     */
    public static final Integer M_A_TS_MAT_SEARCH = 10;
    /**
     * 基础信息 （材料树-规格级）
     */
    public static final Integer M_A_TS_BASE_SAVE = 11;
    /**
     * 材料参数 （材料树-规格级）
     */
    public static final Integer M_A_TS_PARA_SAVE = 12;
    /**
     * 标准信息 （材料树-规格级）
     */
    public static final Integer M_A_TS_STANDARD_INFO_SAVE = 13;
    /**
     * 标准项 （材料树-规格级）
     */
    public static final Integer M_A_TS_STANDARD_ITEM_SAVE = 14;
    /**
     * 材料属性 （材料树-规格级）
     */
    public static final Integer M_A_TS_ATTR_ITEM = 15;

    //     * 添加小样
    public static final Integer M_B_SL_SAMPLE_ADD = 16;

    //     * 更新小样
    public static final Integer M_B_SL_SAMPLE_UPDATE = 17;

    //     * 删除小样
    public static final Integer M_B_SL_SAMPLE_DELETE = 18;

    //     * 处理下架（材料树-规格级）
    public static final Integer M_B_SL_TREE_DELETE = 19;

    //     * 专项产品添加
    public static final Integer M_B_ZX_PRO_ADD = 20;

    //     * 专项产品更新
    public static final Integer M_B_ZX_PRO_UPDATE = 21;

    //     * 专项产品删除
    public static final Integer M_B_ZX_PRO_DELETE = 22;

    /**
     *     类别添加(主营管理)
     */
    public static final Integer M_B_ZY_TYPE_ADD = 23;
    /**
     *     类别更新(主营管理)
     */
    public static final Integer M_B_ZY_TYPE_UPDATE = 24;
    /**
     *     类别更新(主营管理)
     */
    public static final Integer M_B_ZY_TYPE_DELETE = 25;
    /**
     *     类别项添加(主营管理)
     */
    public static final Integer M_B_ZY_ITEM_ADD = 26;
    /**
     *     类别项更新(主营管理)
     */
    public static final Integer M_B_ZY_ITEM_UPDATE = 27;
    /**
     *     类别项更新(主营管理)
     */
    public static final Integer M_B_ZY_ITEM_DELETE = 28;


    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getBeginRowNum() {
        return beginRowNum;
    }

    public void setBeginRowNum(Integer beginRowNum) {
        this.beginRowNum = beginRowNum;
    }

    public Integer getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(Integer endRowNum) {
        this.endRowNum = endRowNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getTerminalTime() {
        // 结束时间+1天
        Calendar c = Calendar.getInstance();
        c.setTime(terminalTime);
        // 利用Calendar 实现 Date日期+1天
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public void setTerminalTime(Date terminalTime) {
        this.terminalTime = terminalTime;
    }
}

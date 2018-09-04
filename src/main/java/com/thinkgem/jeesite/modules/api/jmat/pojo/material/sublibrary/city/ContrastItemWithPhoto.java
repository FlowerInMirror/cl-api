package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city;

import com.thinkgem.jeesite.common.utils.StringUtils;

import java.io.Serializable;

/**
 * 对比项和图 (子库地区二段档次)
 *
 * @author ljc
 * @version 2018-5-31 17:52:07
 */
public class ContrastItemWithPhoto implements Serializable {

    private static final long serialVersionUID = 1L;

    // 对比
    private Integer levelFlag;// 档次标识
    private Integer mcaID;// 对比ID
    private String mcaName;// 对比名称
    private String mcaKey;// KEY（用于审核存储）
    private String treeFourID; // 四级科目ID

    // 图
    private String tspID; // 材料照片ID
    private String tspCityID;        // 城市ID
    private String mcaPhotoURL;// 对比照片url
    private String consultPhotoURL; // 参考图片url
    private String mcaPara; //对比参数
    private String mcaDescribe; // 对比描述
    private String matID; // 材料ID

    // 回路
    private int loopStatus = 1;// 回路状态标识 1.完成(默认)0.未完成

    public int getLoopStatus() {
        // 校验设值:对比照片url未空时 或 对比描述为null时,设值回路状态标识为 0.未完成
        if (StringUtils.isBlank(mcaPhotoURL) || StringUtils.isBlank(mcaDescribe))
            loopStatus = 0;
        return loopStatus;
    }

    public Integer getLevelFlag() {
        return levelFlag;
    }

    public void setLevelFlag(Integer levelFlag) {
        this.levelFlag = levelFlag;
    }

    public Integer getMcaID() {
        return mcaID;
    }

    public void setMcaID(Integer mcaID) {
        this.mcaID = mcaID;
    }

    public String getMcaName() {
        return mcaName;
    }

    public void setMcaName(String mcaName) {
        this.mcaName = mcaName;
    }

    public String getMcaKey() {
        return mcaKey;
    }

    public void setMcaKey(String mcaKey) {
        this.mcaKey = mcaKey;
    }

    public String getTreeFourID() {
        return treeFourID;
    }

    public void setTreeFourID(String treeFourID) {
        this.treeFourID = treeFourID;
    }

    public String getTspID() {
        return tspID;
    }

    public void setTspID(String tspID) {
        this.tspID = tspID;
    }

    public String getTspCityID() {
        return tspCityID;
    }

    public void setTspCityID(String tspCityID) {
        this.tspCityID = tspCityID;
    }

    public String getMcaPhotoURL() {
        return mcaPhotoURL;
    }

    public void setMcaPhotoURL(String mcaPhotoURL) {
        this.mcaPhotoURL = mcaPhotoURL;
    }

    public String getConsultPhotoURL() {
        return consultPhotoURL;
    }

    public void setConsultPhotoURL(String consultPhotoURL) {
        this.consultPhotoURL = consultPhotoURL;
    }

    public String getMcaPara() {
        return mcaPara;
    }

    public void setMcaPara(String mcaPara) {
        this.mcaPara = mcaPara;
    }

    public String getMcaDescribe() {
        return mcaDescribe;
    }

    public void setMcaDescribe(String mcaDescribe) {
        this.mcaDescribe = mcaDescribe;
    }

    public String getMatID() {
        return matID;
    }

    public void setMatID(String matID) {
        this.matID = matID;
    }

    public void setLoopStatus(int loopStatus) {
        this.loopStatus = loopStatus;
    }
}

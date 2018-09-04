package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list;

import java.io.Serializable;

/**
 * 全国 - 材料回路列表
 *
 * @author ljc
 * @createTime 2018-8-6 16:32:19
 */
public class QGMatLoopList implements Serializable {

    private static final long serialVersionUID = -7319359341083667447L;

    private Integer cityID;
    private String cityName;

    private Integer totalNm;
    private Integer quanNm;

    private Integer loopTotalNmY;
    private Integer loopTotalNmN;

    private Integer loopPlatformNmY;
    private Integer loopPlatformNmN;

    private Integer loopAlevelNmY;
    private Integer loopAlevelNmN;
    private Integer loopBlevelNmY;
    private Integer loopBlevelNmN;
    private Integer loopClevelNmY;
    private Integer loopClevelNmN;

    private Integer handleTypeNot;
    private Integer handleTypeZ;
    private Integer handleTypeY;
    private Integer handleTypeW;

    public QGMatLoopList() {
        super();
    }

    public QGMatLoopList(Integer totalNm, Integer quanNm, Integer loopTotalNmY, Integer loopTotalNmN, Integer loopPlatformNmY, Integer loopPlatformNmN, Integer loopAlevelNmY, Integer loopAlevelNmN, Integer loopBlevelNmY, Integer loopBlevelNmN, Integer loopClevelNmY, Integer loopClevelNmN, Integer handleTypeNot, Integer handleTypeZ, Integer handleTypeY, Integer handleTypeW) {
        this.totalNm = totalNm;
        this.quanNm = quanNm;
        this.loopTotalNmY = loopTotalNmY;
        this.loopTotalNmN = loopTotalNmN;
        this.loopPlatformNmY = loopPlatformNmY;
        this.loopPlatformNmN = loopPlatformNmN;
        this.loopAlevelNmY = loopAlevelNmY;
        this.loopAlevelNmN = loopAlevelNmN;
        this.loopBlevelNmY = loopBlevelNmY;
        this.loopBlevelNmN = loopBlevelNmN;
        this.loopClevelNmY = loopClevelNmY;
        this.loopClevelNmN = loopClevelNmN;
        this.handleTypeNot = handleTypeNot;
        this.handleTypeZ = handleTypeZ;
        this.handleTypeY = handleTypeY;
        this.handleTypeW = handleTypeW;
    }

    public Integer getLoopTotalNmN() {
        return loopTotalNmN;
    }

    public void setLoopTotalNmN(Integer loopTotalNmN) {
        this.loopTotalNmN = loopTotalNmN;
    }

    public Integer getLoopPlatformNmN() {
        return loopPlatformNmN;
    }

    public void setLoopPlatformNmN(Integer loopPlatformNmN) {
        this.loopPlatformNmN = loopPlatformNmN;
    }

    public Integer getCityID() {
        return cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getTotalNm() {
        return totalNm;
    }

    public void setTotalNm(Integer totalNm) {
        this.totalNm = totalNm;
    }

    public Integer getQuanNm() {
        return quanNm;
    }

    public void setQuanNm(Integer quanNm) {
        this.quanNm = quanNm;
    }

    public Integer getLoopTotalNmY() {
        return loopTotalNmY;
    }

    public void setLoopTotalNmY(Integer loopTotalNmY) {
        this.loopTotalNmY = loopTotalNmY;
    }

    public Integer getLoopPlatformNmY() {
        return loopPlatformNmY;
    }

    public void setLoopPlatformNmY(Integer loopPlatformNmY) {
        this.loopPlatformNmY = loopPlatformNmY;
    }

    public Integer getLoopAlevelNmY() {
        return loopAlevelNmY;
    }

    public void setLoopAlevelNmY(Integer loopAlevelNmY) {
        this.loopAlevelNmY = loopAlevelNmY;
    }

    public Integer getLoopBlevelNmY() {
        return loopBlevelNmY;
    }

    public void setLoopBlevelNmY(Integer loopBlevelNmY) {
        this.loopBlevelNmY = loopBlevelNmY;
    }

    public Integer getLoopClevelNmY() {
        return loopClevelNmY;
    }

    public void setLoopClevelNmY(Integer loopClevelNmY) {
        this.loopClevelNmY = loopClevelNmY;
    }

    public Integer getLoopAlevelNmN() {
        return loopAlevelNmN;
    }

    public void setLoopAlevelNmN(Integer loopAlevelNmN) {
        this.loopAlevelNmN = loopAlevelNmN;
    }

    public Integer getLoopBlevelNmN() {
        return loopBlevelNmN;
    }

    public void setLoopBlevelNmN(Integer loopBlevelNmN) {
        this.loopBlevelNmN = loopBlevelNmN;
    }

    public Integer getLoopClevelNmN() {
        return loopClevelNmN;
    }

    public void setLoopClevelNmN(Integer loopClevelNmN) {
        this.loopClevelNmN = loopClevelNmN;
    }

    public Integer getHandleTypeNot() {
        return handleTypeNot;
    }

    public void setHandleTypeNot(Integer handleTypeNot) {
        this.handleTypeNot = handleTypeNot;
    }

    public Integer getHandleTypeZ() {
        return handleTypeZ;
    }

    public void setHandleTypeZ(Integer handleTypeZ) {
        this.handleTypeZ = handleTypeZ;
    }

    public Integer getHandleTypeY() {
        return handleTypeY;
    }

    public void setHandleTypeY(Integer handleTypeY) {
        this.handleTypeY = handleTypeY;
    }

    public Integer getHandleTypeW() {
        return handleTypeW;
    }

    public void setHandleTypeW(Integer handleTypeW) {
        this.handleTypeW = handleTypeW;
    }
}

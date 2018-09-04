package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list;

/**
 * 全国 - 材料回路列表合计
 *
 * @author ljc
 * @createTime 2018-8-6 17:31:20
 */
public class QGMatLoopListSUM extends QGMatLoopList{


    private static final long serialVersionUID = 3985950579332558696L;

    private Integer totalCities;

    public QGMatLoopListSUM() {
        super();
    }

    public QGMatLoopListSUM(Integer totalNm, Integer quanNm, Integer loopTotalNmY, Integer loopTotalNmN, Integer loopPlatformNmY, Integer loopPlatformNmN, Integer loopAlevelNmY, Integer loopAlevelNmN, Integer loopBlevelNmY, Integer loopBlevelNmN, Integer loopClevelNmY, Integer loopClevelNmN, Integer handleTypeNot, Integer handleTypeZ, Integer handleTypeY, Integer handleTypeW) {
        super(totalNm, quanNm, loopTotalNmY, loopTotalNmN, loopPlatformNmY, loopPlatformNmN, loopAlevelNmY, loopAlevelNmN, loopBlevelNmY, loopBlevelNmN, loopClevelNmY, loopClevelNmN, handleTypeNot, handleTypeZ, handleTypeY, handleTypeW);
    }

    public QGMatLoopListSUM(Integer totalNm, Integer quanNm, Integer loopTotalNmY, Integer loopTotalNmN, Integer loopPlatformNmY, Integer loopPlatformNmN, Integer loopAlevelNmY, Integer loopAlevelNmN, Integer loopBlevelNmY, Integer loopBlevelNmN, Integer loopClevelNmY, Integer loopClevelNmN, Integer handleTypeNot, Integer handleTypeZ, Integer handleTypeY, Integer handleTypeW, Integer totalCities) {
        super(totalNm, quanNm, loopTotalNmY, loopTotalNmN, loopPlatformNmY, loopPlatformNmN, loopAlevelNmY, loopAlevelNmN, loopBlevelNmY, loopBlevelNmN, loopClevelNmY, loopClevelNmN, handleTypeNot, handleTypeZ, handleTypeY, handleTypeW);
        this.totalCities = totalCities;
    }

    public Integer getTotalCities() {
        return totalCities;
    }

    public void setTotalCities(Integer totalCities) {
        this.totalCities = totalCities;
    }
}

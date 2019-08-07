package com.mhm.model.entity;

import java.io.Serializable;

public class Region implements Serializable {
    private Long regionId;

    private String regionName;

    private Integer regionKind;

    private String upRegionId;

    private String regionCode;

    private String areaCode;

    private Short industry;

    private Double measure;

    private String remark;

    private Integer sort;

    private Short regionLevel;

    private Short state;

    private String path;

    private static final long serialVersionUID = 1L;

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public Integer getRegionKind() {
        return regionKind;
    }

    public void setRegionKind(Integer regionKind) {
        this.regionKind = regionKind;
    }

    public String getUpRegionId() {
        return upRegionId;
    }

    public void setUpRegionId(String upRegionId) {
        this.upRegionId = upRegionId == null ? null : upRegionId.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Short getIndustry() {
        return industry;
    }

    public void setIndustry(Short industry) {
        this.industry = industry;
    }

    public Double getMeasure() {
        return measure;
    }

    public void setMeasure(Double measure) {
        this.measure = measure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Short getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Short regionLevel) {
        this.regionLevel = regionLevel;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}
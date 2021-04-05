package com.mhm.model.entity;

import java.util.Date;

public class Region {
    private Long regionId;

    private String regionName;

    private Integer upRegionId;

    private Integer modelId;

    private Integer projectId;

    private Short sort;

    private Byte regionLevel;

    private Byte state;

    private String path;

    //乐观锁@Version
    private Long version;

    private Integer userId;

    private String userName;

    private Date createTime;

    private Date updateTime;

    private String remark;

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
        this.regionName = regionName;
    }

    public Integer getUpRegionId() {
        return upRegionId;
    }

    public void setUpRegionId(Integer upRegionId) {
        this.upRegionId = upRegionId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Byte getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Byte regionLevel) {
        this.regionLevel = regionLevel;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
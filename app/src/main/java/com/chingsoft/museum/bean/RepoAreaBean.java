package com.chingsoft.museum.bean;

import com.chingsoft.museum.utils.JsonUtils;
import java.io.Serializable;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MuseumApp
 * Package com.chingsoft.museum.bean
 * Description:
 * Created by 师春雷
 * Created at 2019/2/22 2:39 PM
 */
public class RepoAreaBean implements Serializable{

    private int              id;
    private long             updateStamp;
    private int              organizationId;
    private String           name;
    private String           desp;
    private String           areaType;
    private int              userId;
    private String           build;
    private String           floor;
    private int              bundleCulturalRelic;
    private int              bundleDevice;
    private long             createdAt;
    private long             updatedAt;
    private String           currentMonitorValue;
    private int              status;
    private String           map;
    private int              parentId;
    private String           imageUrl;
    private SaveStandardBean saveStandard;
    private String           areaTypeKey;
    private String           areaTypeValue;
    private String           userName;
    private String           parentName;
    private String           organizationName;
//    @SerializedName("currentMonitorValue")
//    private List<String>     currentMonitorValueX;
//    private List<String>     currentMonitorKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUpdateStamp() {
        return updateStamp;
    }

    public void setUpdateStamp(long updateStamp) {
        this.updateStamp = updateStamp;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getBundleCulturalRelic() {
        return bundleCulturalRelic;
    }

    public void setBundleCulturalRelic(int bundleCulturalRelic) {
        this.bundleCulturalRelic = bundleCulturalRelic;
    }

    public int getBundleDevice() {
        return bundleDevice;
    }

    public void setBundleDevice(int bundleDevice) {
        this.bundleDevice = bundleDevice;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCurrentMonitorValue() {
        return currentMonitorValue;
    }

    public CurrentMonitorValueBean getCurrentMonitor() {
        return (CurrentMonitorValueBean) JsonUtils.fromJson(currentMonitorValue,
                                                            CurrentMonitorValueBean.class);
    }

    public void setCurrentMonitorValue(String currentMonitorValue) {
        this.currentMonitorValue = currentMonitorValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SaveStandardBean getSaveStandard() {
        return saveStandard;
    }

    public void setSaveStandard(SaveStandardBean saveStandard) {
        this.saveStandard = saveStandard;
    }

    public String getAreaTypeKey() {
        return areaTypeKey;
    }

    public void setAreaTypeKey(String areaTypeKey) {
        this.areaTypeKey = areaTypeKey;
    }

    public String getAreaTypeValue() {
        return areaTypeValue;
    }

    public void setAreaTypeValue(String areaTypeValue) {
        this.areaTypeValue = areaTypeValue;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

//    public List<String> getCurrentMonitorValueX() {
//        return currentMonitorValueX;
//    }
//
//    public void setCurrentMonitorValueX(List<String> currentMonitorValueX) {
//        this.currentMonitorValueX = currentMonitorValueX;
//    }
//
//    public List<String> getCurrentMonitorKey() {
//        return currentMonitorKey;
//    }
//
//    public void setCurrentMonitorKey(List<String> currentMonitorKey) {
//        this.currentMonitorKey = currentMonitorKey;
//    }
}

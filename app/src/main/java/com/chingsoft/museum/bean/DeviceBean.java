package com.chingsoft.museum.bean;

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
 * Created at 2019/2/22 2:41 PM
 */
public class DeviceBean implements Serializable {

    /**
     * id : 674
     * updateStamp : 1512907948016
     * organizationId : 1
     * repoArea : {"id":75,"updateStamp":1548232314684,"organizationId":1,"name":"炭化粟","desp":"","areaType":"CONSTANT_TH_DISPLAY","userId":73,"build":"","floor":"","bundleCulturalRelic":0,"bundleDevice":1,"createdAt":1512524398375,"updatedAt":1548232314666,"culturalRelics":[],"currentMonitorValue":"{\r\n  \"monitorType\" : [ \"TEMPERATURE\", \"HUMIDITY\" ]\r\n}","status":1,"map":"","parentId":94,"imageUrl":"http://cloud.chingsoft.com/190123163153508291.jpg","saveStandard":null}
     * name : 一类-C06F114DD219
     * desp :
     * isEnable : 1
     * isConnect : 0
     * marker :
     * deviceNo : C06F114DD219
     * power : 100
     * useStartTime : null
     * expiry : null
     * usedMonth : null
     * userId : null
     * maintenanceTime : null
     * maintenanceInfo :
     * signalCover : null
     * createdAt : 1512907907442
     * updatedAt : null
     * longitude : null
     * latitude : null
     * deviceStatus : WARN
     * currentPower : null
     * deviceAlarm : null
     * macAddr : C06F114DD219
     * ipAddr :
     * major : 1111
     * minor : FFFF
     * currentMonitorValue : null
     * deviceType : SENSOR
     * settingMonitorValue : null
     * extraMacAddr :
     * routingInfo : null
     * monitorType : {
     * "monitorType" : [ "TEMPERATURE", "HUMIDITY" ]
     * }
     * rfid :
     * recentReportTime : 1525684337942
     * bindStatus : null
     * devicePowerSupplyType : BATTERY_SUPPLY
     * reportCycle : 5
     * parentId : 19
     * alarmCategory : MONITORING_DEVICE
     * alarmType : DISCONNECT_ALARM
     * alarmDetails : 设备失去连接,请检查!
     * version : V1
     * isMaster : 1
     * xcoordinate : 272
     * ycoordinate : 194
     */

    private int          id;
    private long         updateStamp;
    private int          organizationId;
    private String       name;
    private String       desp;
    private int          isEnable;
    private int          isConnect;
    private String       marker;
    private String       deviceNo;
    private int          power;
    private long         useStartTime;
    private long         expiry;
    private int          usedMonth;
    private int          userId;
    private long         maintenanceTime;
    private String       maintenanceInfo;
    private Object       signalCover;
    private long         createdAt;
    private long         updatedAt;
    private Object       longitude;
    private Object       latitude;
    private String       deviceStatus;
    private Object       currentPower;
    private Object       deviceAlarm;
    private String       macAddr;
    private String       ipAddr;
    private String       major;
    private String       minor;
    private Object       currentMonitorValue;
    private String       deviceType;
    private Object       settingMonitorValue;
    private String       extraMacAddr;
    private Object       routingInfo;
    private String       monitorType;
    private String       rfid;
    private long         recentReportTime;
    private String       bindStatus;
    private String       devicePowerSupplyType;
    private int          reportCycle;
    private int          parentId;
    private String       alarmCategory;
    private String       alarmType;
    private String       alarmDetails;
    private String       version;
    private int          isMaster;
    private int          xcoordinate;
    private int          ycoordinate;

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

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getIsConnect() {
        return isConnect;
    }

    public void setIsConnect(int isConnect) {
        this.isConnect = isConnect;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public long getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(long useStartTime) {
        this.useStartTime = useStartTime;
    }

    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public int getUsedMonth() {
        return usedMonth;
    }

    public void setUsedMonth(int usedMonth) {
        this.usedMonth = usedMonth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(long maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public String getMaintenanceInfo() {
        return maintenanceInfo;
    }

    public void setMaintenanceInfo(String maintenanceInfo) {
        this.maintenanceInfo = maintenanceInfo;
    }

    public Object getSignalCover() {
        return signalCover;
    }

    public void setSignalCover(Object signalCover) {
        this.signalCover = signalCover;
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

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Object getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(Object currentPower) {
        this.currentPower = currentPower;
    }

    public Object getDeviceAlarm() {
        return deviceAlarm;
    }

    public void setDeviceAlarm(Object deviceAlarm) {
        this.deviceAlarm = deviceAlarm;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public Object getCurrentMonitorValue() {
        return currentMonitorValue;
    }

    public void setCurrentMonitorValue(Object currentMonitorValue) {
        this.currentMonitorValue = currentMonitorValue;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Object getSettingMonitorValue() {
        return settingMonitorValue;
    }

    public void setSettingMonitorValue(Object settingMonitorValue) {
        this.settingMonitorValue = settingMonitorValue;
    }

    public String getExtraMacAddr() {
        return extraMacAddr;
    }

    public void setExtraMacAddr(String extraMacAddr) {
        this.extraMacAddr = extraMacAddr;
    }

    public Object getRoutingInfo() {
        return routingInfo;
    }

    public void setRoutingInfo(Object routingInfo) {
        this.routingInfo = routingInfo;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public long getRecentReportTime() {
        return recentReportTime;
    }

    public void setRecentReportTime(long recentReportTime) {
        this.recentReportTime = recentReportTime;
    }

    public String getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

    public String getDevicePowerSupplyType() {
        return devicePowerSupplyType;
    }

    public void setDevicePowerSupplyType(String devicePowerSupplyType) {
        this.devicePowerSupplyType = devicePowerSupplyType;
    }

    public int getReportCycle() {
        return reportCycle;
    }

    public void setReportCycle(int reportCycle) {
        this.reportCycle = reportCycle;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getAlarmCategory() {
        return alarmCategory;
    }

    public void setAlarmCategory(String alarmCategory) {
        this.alarmCategory = alarmCategory;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmDetails() {
        return alarmDetails;
    }

    public void setAlarmDetails(String alarmDetails) {
        this.alarmDetails = alarmDetails;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(int isMaster) {
        this.isMaster = isMaster;
    }

    public int getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(int xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public int getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(int ycoordinate) {
        this.ycoordinate = ycoordinate;
    }
}

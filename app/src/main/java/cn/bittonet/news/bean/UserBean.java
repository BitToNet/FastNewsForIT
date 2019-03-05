package cn.bittonet.news.bean;

import java.io.Serializable;

/**
 * Created by Felone on 2019/2/20 0020 上午 10:16
 * description：
 * 50257836@qq.com
 */
public class UserBean  implements Serializable{
    /**
     * cityName : 南宁市
     * organization : {"children":[],"code":"001","desp":"","id":1,"isUnit":1,"name":"广西壮族自治区博物馆","systemAreaId":30243,"type":1}
     * provinceName : 广西
     * sessionInfo : {"expiresTime":1550631963056,"sessionId":"9","status":2,"timeStamp":1550628363056}
     * systemRole : {"code":"SuperUser","desp":"SuperUser","id":5,"name":"SuperUser ","roleType":"SUPER_ADMIN","status":0,"updatedAt":1474523436000}
     */

    private String             cityName;
    private OrganizationBean   organization;
    private String             provinceName;
    private SessionInfoBean    sessionInfo;
    private SystemRoleBean     systemRole;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public OrganizationBean getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationBean organization) {
        this.organization = organization;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public SessionInfoBean getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInfoBean sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public SystemRoleBean getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(SystemRoleBean systemRole) {
        this.systemRole = systemRole;
    }
}

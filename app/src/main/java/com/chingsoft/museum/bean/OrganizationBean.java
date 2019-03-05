package com.chingsoft.museum.bean;

import java.io.Serializable;
import java.util.List;

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
 * Created at 2019/2/20 3:30 PM
 */
public class OrganizationBean implements Serializable {
    /**
     * children : []
     * code : 001
     * desp :
     * id : 1
     * isUnit : 1
     * name : 广西壮族自治区博物馆
     * systemAreaId : 30243
     * type : 1
     */

    private String                 code;
    private String                 desp;
    private int                    id;
    private int                    isUnit;
    private String                 name;
    private int                    systemAreaId;
    private int                    type;
    private List<OrganizationBean> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsUnit() {
        return isUnit;
    }

    public void setIsUnit(int isUnit) {
        this.isUnit = isUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSystemAreaId() {
        return systemAreaId;
    }

    public void setSystemAreaId(int systemAreaId) {
        this.systemAreaId = systemAreaId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<OrganizationBean> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationBean> children) {
        this.children = children;
    }
}

package com.chingsoft.museum.bean;

/**
 * Created by Felone on 2019/2/28 0028 上午 10:21
 * description：
 * 50257836@qq.com
 */
public class ValueBean {

    /**
     * id : 6304585
     * updateStamp : 1545728551933
     * dateTime : 1545728551000
     * objectType : HUMIDITY
     * value : 20.03
     */
    private String name;
    private int id;
    private long   updateStamp;
    private long   dateTime;
    private String objectType;
    private float value;

    public ValueBean( int id, String name,
            float value,String objectType) {
        this.name = name;
        this.id = id;
        this.value = value;
        this.objectType=objectType;
    }

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

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

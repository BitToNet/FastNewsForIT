package com.chingsoft.museum.bean;

import java.io.Serializable;

/**
 * Created by Felone on 2019/2/28 0028 下午 3:00
 * description：
 * 50257836@qq.com
 */
public class DataBean implements Serializable {

    /**
     * value : 16.37
     * timestamp : 1551162309648
     */

    private float value;
    private long timestamp;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

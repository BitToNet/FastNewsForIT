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
 * Created at 2019/2/25 10:53 AM
 */
public class CurrentMonitorValueBean implements Serializable {
    private List<String> monitorType;

    public List<String> getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(List<String> monitorType) {
        this.monitorType = monitorType;
    }

    @Override
    public String toString() {
        return "{monitorType:" + monitorType + '}';
    }
}

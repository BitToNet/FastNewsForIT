package cn.bittonet.news.bean;

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
 * Created at 2019/2/25 10:40 AM
 */
public class DataListBean implements Serializable{

    private DeviceBean      device;
    private Co2Bean         CO2;
    private HumidityBean    HUMIDITY;
    private TemperatureBean TEMPERATURE;
    private UvBean          UV;
    private LightingBean    LIGHTING;

    public DeviceBean getDevice() {
        return device;
    }

    public void setDevice(DeviceBean device) {
        this.device = device;
    }

    public Co2Bean getCo2() {
        return CO2;
    }

    public void setCo2(Co2Bean CO2) {
        this.CO2 = CO2;
    }

    public HumidityBean getHumidity() {
        return HUMIDITY;
    }

    public void setHumidity(HumidityBean HUMIDITY) {
        this.HUMIDITY = HUMIDITY;
    }

    public TemperatureBean getTemperature() {
        return TEMPERATURE;
    }

    public void setTemperature(TemperatureBean TEMPERATURE) {
        this.TEMPERATURE = TEMPERATURE;
    }

    public UvBean getUv() {
        return UV;
    }

    public void setUv(UvBean UV) {
        this.UV = UV;
    }

    public LightingBean getLighting() {
        return LIGHTING;
    }

    public void setLighting(LightingBean LIGHTING) {
        this.LIGHTING = LIGHTING;
    }
}

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
 * Description: 规则实体类
 * Created by 师春雷
 * Created at 2019/2/22 2:44 PM
 */
public class SaveStandardBean implements Serializable{

    /**
     * id : 6
     * parentId : 1
     * parentType : null
     * type : 青铜器
     * temperatureMax : 30
     * temperatureMin : 20
     * humidityMax : 40
     * humidityMin : 0
     * so2 : 0.05
     * co2 : null
     * lighting : 0.08
     * tvoc : 4
     * uv : 0.12
     * pm2_5 : 0.12
     * ch2o : 0.09
     * organicPollutant : 0.2
     * inorganicPollutant : null
     * sulfurCompound : null
     * pm1_0 : 0.08
     */

    private int    id;
    private int    parentId;
    private Object parentType;
    private String type;
    private int    temperatureMax;
    private int    temperatureMin;
    private int    humidityMax;
    private int    humidityMin;
    private double so2;
    private double co2;
    private double lighting;
    private int    tvoc;
    private double uv;
    private double pm2_5;
    private double ch2o;
    private double organicPollutant;
    private double inorganicPollutant;
    private double sulfurCompound;
    private double pm1_0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Object getParentType() {
        return parentType;
    }

    public void setParentType(Object parentType) {
        this.parentType = parentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(int temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public int getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(int temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public int getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(int humidityMax) {
        this.humidityMax = humidityMax;
    }

    public int getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(int humidityMin) {
        this.humidityMin = humidityMin;
    }

    public double getSo2() {
        return so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getLighting() {
        return lighting;
    }

    public void setLighting(double lighting) {
        this.lighting = lighting;
    }

    public int getTvoc() {
        return tvoc;
    }

    public void setTvoc(int tvoc) {
        this.tvoc = tvoc;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public double getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(double pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public double getCh2o() {
        return ch2o;
    }

    public void setCh2o(double ch2o) {
        this.ch2o = ch2o;
    }

    public double getOrganicPollutant() {
        return organicPollutant;
    }

    public void setOrganicPollutant(double organicPollutant) {
        this.organicPollutant = organicPollutant;
    }

    public double getInorganicPollutant() {
        return inorganicPollutant;
    }

    public void setInorganicPollutant(double inorganicPollutant) {
        this.inorganicPollutant = inorganicPollutant;
    }

    public double getSulfurCompound() {
        return sulfurCompound;
    }

    public void setSulfurCompound(double sulfurCompound) {
        this.sulfurCompound = sulfurCompound;
    }

    public double getPm1_0() {
        return pm1_0;
    }

    public void setPm1_0(double pm1_0) {
        this.pm1_0 = pm1_0;
    }
}

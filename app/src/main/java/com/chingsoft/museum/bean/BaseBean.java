package com.chingsoft.museum.bean;

import java.io.Serializable;

public class BaseBean<T> implements Serializable {

    private int    code;
    private String message;
    private T      result;
    private int    total;

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    private float max;
    private float min;


    public BaseBean(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean success() {
        return 2001 == getCode();
    }

    @Override
    public String toString() {
        return "{"
                + "code="
                + code
                + ", message='"
                + message
                + '\''
                + ", result="
                + result
                + ", total="
                + total
                + ", max="
                + max
                + ", min="
                + min
                + '}';
    }
}

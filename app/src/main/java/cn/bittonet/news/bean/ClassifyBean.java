package cn.bittonet.news.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Felone on 2019/2/20 0020 上午 10:16
 * description：
 * 50257836@qq.com
 */
public class ClassifyBean implements Serializable {
    /**
     * id : 63
     * name : 瓷美如花-文物苑一楼展厅
     */

    private int                id;
    private String             name;
    public  List<ClassifyBean> list;
    public  List<ClassifyBean> monitorList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassifyBean> getList() {
        return list;
    }

    public void setList(List<ClassifyBean> list) {
        this.list = list;
    }

    public List<ClassifyBean> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<ClassifyBean> monitorList) {
        this.monitorList = monitorList;
    }
}

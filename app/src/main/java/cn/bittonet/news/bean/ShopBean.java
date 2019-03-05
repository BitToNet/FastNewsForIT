package cn.bittonet.news.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Felone on 2019/2/22 0022 上午 10:41
 * description：
 * 50257836@qq.com
 */
public class ShopBean implements Serializable{

    /**
     * repoArea : {}
     * dataList : []
     * presettedMonitorData : null
     */

    private RepoAreaBean       repoArea;
    private List<DataListBean> dataList;

    public RepoAreaBean getRepoArea() {
        return repoArea;
    }

    public void setRepoArea(RepoAreaBean repoArea) {
        this.repoArea = repoArea;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }
}

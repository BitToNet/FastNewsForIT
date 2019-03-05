package com.chingsoft.museum.http;

public class ApiConstant {

    //    static final String BASE_URL = "http://101.200.174.126:8088/v2/api/museum/";
//    static final String BASE_URL = "http://192.168.80.20:8081/v2/api/museum/";
    static final String BASE_URL = "http://xj-api-test.chingsoft.com:8081/v2/api/museum/";

    /** 登录 GET */
    static final String LOGIN     = "system_user/login";
    /** 区域列表 GET */
    static final String HALL_LIST = "repoarea/select_area_mobile";
    /** 区域展柜列表 GET */
    static final String HALL_SHOP = "repoarea/ordinaryCase";

    /** 区域展柜详情 GET */
    static final String HALL_INFO = "repoarea/ordinaryCaseDetail";

    /** 标准列表接口 GET */
    static final String SAVE_STANDARD_LIST = "saveStandard/page_list";

    /** 区域列表接口 GET */
    static final String REPOAREA_LIST = "repoarea/page_list";
    /** 设备历史数据接口 GET */
    static final String DEVICE_HISTORY_DATA = "network_topology/device_history_data";
}

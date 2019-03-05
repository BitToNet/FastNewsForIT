package cn.bittonet.news.http;


import cn.bittonet.news.bean.BaseBean;
import cn.bittonet.news.bean.ClassifyBean;
import cn.bittonet.news.bean.DataBean;
import cn.bittonet.news.bean.RepoAreaBean;
import cn.bittonet.news.bean.SaveStandardBean;
import cn.bittonet.news.bean.ShopBean;
import cn.bittonet.news.bean.UserBean;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 登录
     *
     * @param username  用户名
     * @param password  密码
     * @param token     token
     * @param sessionId sessionId
     * @return
     */
    @GET(ApiConstant.LOGIN)
    Observable<BaseBean<UserBean>> login(@Query("userName") String username,
            @Query("password") String password, @Query("token") String token,
            @Query("sessionId") String sessionId);

    /**
     * 区域列表
     *
     * @param token     token
     * @param sessionId sessionId
     * @return
     */
    @GET(ApiConstant.HALL_LIST)
    Observable<BaseBean<List<ClassifyBean>>> getHallList(@Query("token") String token,
            @Query("sessionId") String sessionId);

    /**
     * 设备历史数据接口
     *
     * @param token     token
     * @param sessionId sessionId
     * @return
     */
    @GET(ApiConstant.DEVICE_HISTORY_DATA)
    Observable<BaseBean<List<DataBean>>> getHistoryData(@Query("token") String token,
            @Query("endTime") long endTime, @Query("id") int id, @Query("startTime") long startTime,
            @Query("monitorType") String monitorType, @Query("sessionId") String sessionId);

    /**
     * 区域展柜列表
     *
     * @param token     token
     * @param sessionId sessionId
     * @param id        区域展柜ID
     * @return
     */
    @GET(ApiConstant.HALL_SHOP)
    Observable<BaseBean<List<ShopBean>>> getHallShop(@Query("token") String token,
            @Query("sessionId") String sessionId, @Query("id") int id);

    /**
     * 规则列表
     *
     * @param token     token
     * @param sessionId sessionId
     * @param page      页码
     * @param limit     每页条数
     * @return
     */
    @GET(ApiConstant.SAVE_STANDARD_LIST)
    Observable<BaseBean<List<SaveStandardBean>>> getSavaStandard(@Query("token") String token,
            @Query("sessionId") String sessionId, @Query("page") int page,
            @Query("limit") int limit);

    /**
     * 区域管理列表
     *
     * @param token     token
     * @param sessionId sessionId
     * @param page      页码
     * @param limit     每页条数
     * @return
     */
    @GET(ApiConstant.REPOAREA_LIST)
    Observable<BaseBean<List<RepoAreaBean>>> getRepoarea(@Query("token") String token,
            @Query("sessionId") String sessionId, @Query("page") int page,
            @Query("limit") int limit);
}

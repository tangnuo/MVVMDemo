package com.kedacom.mvvmdemo.net;

import com.kedacom.mvvmdemo.bean.ExpressInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/6/6 14:46
 */
public interface ApiService {
    /**
     * 获取快递信息
     * Rx方式
     *
     * @param type   快递类型
     * @param postid 快递单号
     * @return Observable<ExpressInfo>
     */
    @GET("query")
    Observable<ExpressInfo> getExpressInfoRx(@Query("type") String type, @Query("postid") String postid);

}

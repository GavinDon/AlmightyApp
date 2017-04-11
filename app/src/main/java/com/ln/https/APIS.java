package com.ln.https;

import com.ln.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by linan   on 2017/4/7.
 */

public interface APIS {
    @GET("toutiao/index")
    Observable<NewsBean> reqNews(@Query("key") String key,@Query("type") String type);

}

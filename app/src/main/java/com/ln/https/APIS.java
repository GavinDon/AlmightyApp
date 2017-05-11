package com.ln.https;

import com.ln.bean.NewsBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by linan   on 2017/4/7.
 */

public interface APIS {
    @GET("toutiao/index")
    Observable<NewsBean> reqNews(@Query("key") String key,@Query("type") String type);
    @Multipart
    @POST("uploade")
    retrofit2.Call<ResponseBody> reqUpload(@PartMap Map<String , String> usermaps, @PartMap Map<String , RequestBody>s);

}

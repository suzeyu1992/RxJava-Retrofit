package com.szysky.skillpractice.retorfit;

import com.szysky.skillpractice.data.PhoneResultBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by suzeyu on 16/7/15.
 */

public interface IPhoneServer {
    String BASE_URL = "http://apis.baidu.com/apistore/";

    @GET("mobilenumber/mobilenumber")
        //追加到域名后面的地址
    Call<PhoneResultBean> getResult(@Header("apikey") String apikey, @Query("phone") String phone);
}

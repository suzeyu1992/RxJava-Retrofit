package com.szysky.skillpractice.retorfit;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by suzeyu on 16/7/19.
 */
                                                public interface ITestAPI {
                                                    @FormUrlEncoded
                                                    @POST("demo/")
                                                    Call<RequestBody> send(@Field("name") String first,@Field("id") String id);
                                                }

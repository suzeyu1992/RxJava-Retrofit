package com.szysky.skillpractice;

import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.szysky.skillpractice.data.PhoneResultBean;
import com.szysky.skillpractice.retorfit.IPhoneServer;
import com.szysky.skillpractice.retorfit.ITestAPI;
import com.szysky.skillpractice.rxjava.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static  String BASE_URL = "http://apis.baidu.com/apistore/";
    private static final String API_KEY = "94bc47832d8a431552c98fe8f2ca5690";

    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.tv_result)
    TextView tvResult;
    @Bind(R.id.activity_main)
    ConstraintLayout activityMain;
    @Bind(R.id.btn_query)
    Button btn_Query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        JobScheduler job = null;


        test test = new test(getApplicationContext());



    }

    public void textAPI() throws IOException {
        // 0.创建okhttp的日志拦截器
        OkHttpClient okhttpClient = new OkHttpClient();
        //只有在debug调试环境中才会显示过滤日志
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            okhttpClient = okhttpClient.newBuilder().addInterceptor(logging).build();
        }

        HashMap<String, String> mMaps = new HashMap<>();
        mMaps.put("phone","18640029255");
        mMaps.put("name","suzeyu");
        mMaps.put("email","123@gmail.com");
        mMaps.put("language","java");


        // 1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())     //处理返回数据的解析方式
                .baseUrl("http://apis.baidu.com/")
                .build();

        ITestAPI iTestAPI = retrofit.create(ITestAPI.class);
        iTestAPI.send("master","1234").execute();
    }


    /**
     * 查询手机电话请求
     */
    @OnClick(R.id.btn_query)
    public void queryPhone() {

        // 0.创建okhttp的日志拦截器
        OkHttpClient okhttpClient = new OkHttpClient();
        //只有在debug调试环境中才会显示过滤日志
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpClient = okhttpClient.newBuilder().addInterceptor(logging).build();
        }
        // 1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())     //处理返回数据的解析方式
                .baseUrl(BASE_URL)
                .baseUrl("http://www.qq.com/")
                .build();

        //2.创建访问API的请求
        IPhoneServer IPhoneServer = retrofit.create(IPhoneServer.class);
        Call<PhoneResultBean> result = IPhoneServer.getResult(API_KEY, etPhone.getText().toString().trim());


        //3. 执行请求
        result.enqueue(new Callback<PhoneResultBean>() {
            @Override
            public void onResponse(Call<PhoneResultBean> call, Response<PhoneResultBean> response) {
                //处理结果
                if (response.isSuccessful()) {
                    PhoneResultBean body = response.body();
                    tvResult.setText( body.getRetData().getCity());
                }
            }


            @Override
            public void onFailure(Call<PhoneResultBean> call, Throwable t) {
                Log.e("szy",t.toString());
            }
        });


    }




}

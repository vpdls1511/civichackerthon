package com.ngyu.civihacker.lib.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("4670616c486e677937367268495350/json/MpDisinfectionInfo/1/10")
    Call<RetrofitRepo> getRowList();

}

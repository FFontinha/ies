package com.lab01;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * IPMA API service mapping
 */
public interface IpmaService2 {

    @GET("distrits-islands.json")
    Call<Regions> getForecastForACity2();

}

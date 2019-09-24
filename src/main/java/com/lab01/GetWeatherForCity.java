package com.lab01;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetWeatherForCity

{

    //public static final int CITY_ID_AVEIRO = 1010500;

    public static void main(String[] args) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(Integer.parseInt(args[0]));

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            System.out.println( "forecast is: " + forecast.getData().
                    listIterator().next().getTMax());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

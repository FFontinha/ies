package com.lab01;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class GetWeatherForCity

{

    //public static final int CITY_ID_AVEIRO = 1010500;

    public static void main(String[] args) {

        String code = "";
        String name = args[0];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        IpmaService2 service = retrofit.create(IpmaService2.class);
        Call<Regions> callSync = service.getForecastForACity2();

        try {
            Response<Regions> apiResponse = callSync.execute();
            Regions regions = apiResponse.body();
            List<Region> allRegions = regions.getData();
            for(Region r : allRegions){
                if(r.getLocal().equals(name)) {
                    code = Integer.toString(r.getGlobalIdLocal());
                    break;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        IpmaService service2 = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync2 = service2.getForecastForACity(Integer.parseInt(code));

        try {
            Response<IpmaCityForecast> apiResponse = callSync2.execute();
            IpmaCityForecast  forecast = apiResponse.body();
            CityForecast  cityForecast  = forecast.getData().listIterator().next();
            System.out.printf( "Data : %s \nTemperatura minima: %s \nTemperatura máxima: %s \nProb. Precipitação: %s\n",
                    cityForecast.getForecastDate(), cityForecast.getTMin(), cityForecast.getTMax(), cityForecast.getPrecipitaProb());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package com.example.genji.am104_gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by genji on 4/2/17. *** CONTROLLER ***
 */

interface RFService {

    public static final String BASE_URL = "https://raw.githubusercontent.com/";

    @GET("opendatajson/football.json/master/2016-17/it.1.json")
    Call<Pojo> getPojo(); //get the Pojo object

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

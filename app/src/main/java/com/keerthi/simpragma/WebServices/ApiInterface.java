package com.keerthi.simpragma.WebServices;

import com.keerthi.simpragma.Model.RecepieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


//    @GET("?i=onions,garlic&q=omelet&p=3")
//    Call<RecepieModel> getRecepie();

    @GET("?i=onions,garlic&q=omelet?")
    Call<RecepieModel> getRecepie(@Query("p") int page);

}

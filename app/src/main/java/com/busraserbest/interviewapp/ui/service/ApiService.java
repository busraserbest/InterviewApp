package com.busraserbest.interviewapp.ui.service;

import com.busraserbest.interviewapp.ui.utils.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

  private Retrofit retrofit = null;

  public PaymentApi getAPI() {

    if (retrofit == null) {
      retrofit = new Retrofit

          .Builder()
          .baseUrl(Config.API_HOST)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }

    return retrofit.create(PaymentApi.class);
  }
}

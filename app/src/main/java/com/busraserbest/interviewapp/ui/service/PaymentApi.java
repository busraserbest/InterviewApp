package com.busraserbest.interviewapp.ui.service;


import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PaymentApi {

  @Headers({
      "x-ibm-client-id: 98081ccf-59be-4e86-a617-5c50d928aca6",
      "x-ibm-client-secret: uF4sN7hC1aP2jQ1eO1sR6eS3uH2xC7hS7fT5cA1yH4tY8xY1fF",
      "Accept: application/json",
      "Content-Type: application/json",
  })
  @POST("payment")
  Call<JsonObject> payment(@Body JsonObject payment);


  @Headers({
      "x-ibm-client-id: 98081ccf-59be-4e86-a617-5c50d928aca6",
      "x-ibm-client-secret: uF4sN7hC1aP2jQ1eO1sR6eS3uH2xC7hS7fT5cA1yH4tY8xY1fF",
      "accept: application/json",
      "content-type: application/json",
  })
  @POST("get_qr_sale")
  Call<JsonObject> getQrSale(@Body JsonObject qr);
}
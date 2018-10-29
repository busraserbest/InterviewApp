package com.busraserbest.interviewapp.ui.ui;

import android.util.Log;
import com.busraserbest.interviewapp.ui.service.ApiService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentPresenter {

  private PaymentView paymentView;
  private ApiService apiService;

  public PaymentPresenter(MainActivity view) {
    this.paymentView = (PaymentView) view;

    if (this.apiService == null) {
      this.apiService = new ApiService();
    }
  }

  public void generateQrCodeWithRandomMoney(Integer totalMoney) throws JSONException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("totalReceiptAmount", totalMoney);
    apiService.getAPI()
        .getQrSale(jsonObject)
        .enqueue(new Callback<JsonObject>() {
          @Override
          public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Log.w("response", String.valueOf(response.body()));
            if (response.body() != null) {
              paymentView
                  .qrSale(response.body().get("returnCode"), response.body().get("returnDesc"),
                      response.body().get("QRdata"));
            }
          }

          @Override
          public void onFailure(Call<JsonObject> call, Throwable t) {
            try {
              throw new InterruptedException("Yalnış bir şeyler var");
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

  }

  public void postPayment(int totalMoney, CharSequence text) {

    JsonObject payment = new JsonObject();
    payment.addProperty("returnCode", 1000);
    payment.addProperty("returnDesc", "success");
    payment.addProperty("receiptMsgCustomer", "Büşra Serbest");
    payment.addProperty("receiptMsgMerchant", "Büşra Serbest Merchant");
    payment.addProperty("QRdata", text.toString());

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("paymentProcessorID", 67);

    JsonObject jsonObject1 = new JsonObject();
    jsonObject1.addProperty("paymentType", 3);
    jsonObject1.addProperty("amount", totalMoney);
    jsonObject1.addProperty("currencyID", 949);
    jsonObject1.addProperty("vatRate", 800);

    JsonArray paymentActionList = new JsonArray();
    paymentActionList.add(jsonObject1);
    jsonObject.addProperty("paymentActionList", String.valueOf(paymentActionList));

    JsonArray paymentInfoList = new JsonArray();
    paymentInfoList.add(jsonObject);

    payment.addProperty("paymentInfoList", String.valueOf(paymentInfoList));
    Log.w("response", String.valueOf(payment));
    apiService.getAPI()
        .payment(payment)
        .enqueue(new Callback<JsonObject>() {
          @Override
          public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Log.w("response", String.valueOf(response.body()));
            if (response.body() != null) {

            }
          }

          @Override
          public void onFailure(Call<JsonObject> call, Throwable t) {
            try {
              throw new InterruptedException("Yalnış bir şeyler var");
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });

  }
}

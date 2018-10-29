package com.busraserbest.interviewapp.ui.ui;

import com.google.gson.JsonElement;

public interface PaymentView {

  void qrSale(JsonElement returnCode, JsonElement returnDesc, JsonElement qrData);

}

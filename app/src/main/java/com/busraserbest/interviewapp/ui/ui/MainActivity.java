package com.busraserbest.interviewapp.ui.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.busraserbest.interviewapp.R;
import com.google.gson.JsonElement;
import java.util.Random;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements PaymentView {

  private PaymentPresenter paymentPresenter;
  private TextView tvQrdata;
  private TextView tvMoney;
  private Button btnSend;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tvMoney = findViewById(R.id.tvMoney);
    tvQrdata = findViewById(R.id.tvQrdata);
    btnSend = findViewById(R.id.btnSend);
    final PaymentPresenter paymentPresenter = new PaymentPresenter(this);
    Random r = new Random();
    final int totalMoney = r.nextInt(10000 - 1000 + 1) + 1000;
    tvMoney.setText(String.valueOf(totalMoney) + " TL");
    try {
      paymentPresenter.generateQrCodeWithRandomMoney(totalMoney);
    } catch (JSONException e) {
      e.printStackTrace();
    }

    btnSend.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Ödeme İşlemine Devam Etmek İster Misiniz ?")
            .setContentText("")
            .setConfirmText("Devam Et")
            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
              @Override
              public void onClick(SweetAlertDialog sDialog) {
                sDialog
                    .setTitleText("Tamamlandı")
                    .setContentText("Ödeme işleminiz başarıyla gerçeklemiştir.")
                    .setConfirmText("Tamam")
                    .setConfirmClickListener(null)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
              }
            })
            .show();
      }
    });

  }

  @Override
  public void qrSale(JsonElement returnCode, JsonElement returnDesc, JsonElement qrData) {
    tvQrdata.setText(qrData.toString());
  }

}
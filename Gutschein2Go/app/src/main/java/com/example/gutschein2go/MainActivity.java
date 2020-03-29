package com.example.gutschein2go;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    TextView textViewGueltigkeit;
    ImageView imageView;
    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewGueltigkeit = findViewById(R.id.textViewGueltigkeit);
        imageView = findViewById(R.id.imageView);

    }
    public void ButtonQR_Click (View v) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scannen Sie den Gutschein QR-Code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }

    public void ButtonCode_Click (View v) {
        Intent i = new Intent(this, WebViewActivity.class);
        this.startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result.getContents() != null) {
            String url = result.getContents();
            Intent i = new Intent(this, WebViewActivity.class);
            i.putExtra("url", url);
            this.startActivity(i);
        }
       else {
            Intent i = new Intent(this, MainActivity.class);
            this.startActivity(i);
        }
    }
}



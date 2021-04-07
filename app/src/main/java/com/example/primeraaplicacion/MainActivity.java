package com.example.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView tvTit;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciar widget
        tvTit = findViewById(R.id.tvTitulo);
        etEmail = findViewById(R.id.etCorreo);
    }

    public void clickBoton(View v){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        String correo = etEmail.getText().toString();

        boolean correoOK = pattern.matcher(correo).matches();

        etEmail.setError(null);

        if(correoOK){
            Log.d("TAG_", "Click botón");
            Toast.makeText(this, getString(R.string.enviar) + " " + correo , Toast.LENGTH_LONG).show();
            tvTit.setTextColor(Color.BLACK);
        }else{
            etEmail.setError(getString(R.string.error_correo));
            tvTit.setTextColor(Color.RED);
        }
    }

    public void clickSalir(View v){
        finish();
    }
}

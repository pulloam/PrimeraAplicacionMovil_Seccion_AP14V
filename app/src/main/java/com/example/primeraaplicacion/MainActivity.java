package com.example.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView tvTit;
    private EditText etEmail;
    private Button btnAceptar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referencias();
        eventos();

        Log.d("TAG_","Oncreate");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("TAG_", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG_", "onResume");
    }



    private void clickBoton(View queBoton){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        String correo = etEmail.getText().toString();

        if(queBoton.getId() == R.id.btnOK){
            boolean correoOK = pattern.matcher(correo).matches();

            etEmail.setError(null);

            if(correoOK){
                Log.d("TAG_", "Click botón");
                Toast.makeText(this, getString(R.string.enviar) + " " + correo , Toast.LENGTH_LONG).show();
                tvTit.setTextColor(Color.BLACK);

                Intent i = new Intent(this, SegundaActivity.class);
                i.putExtra("keyCorreo",correo);
                i.putExtra("keyEdad", 43);
                startActivity(i);
            }else{
                etEmail.setError(getString(R.string.error_correo));
                tvTit.setTextColor(Color.RED);
            }
        }else if(queBoton.getId() == R.id.btnSalir){
            finish();
        }

        /*switch (queBoton.getId()){
            case R.id.btnOK:

                break;
            case R.id.btnSalir:

                break;

            default
          }*/
    }

    private void eventos() {
        //Eventos
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBoton(v);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBoton(v);
            }
        });
    }

    private void referencias(){
        //Referenciar widget
        tvTit = findViewById(R.id.tvTitulo);
        etEmail = findViewById(R.id.etCorreo);

        btnAceptar = findViewById(R.id.btnOK);
        btnSalir = findViewById(R.id.btnSalir);
    }
}

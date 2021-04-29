package com.example.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity {
    private Button btnVolver;
    private TextView tvCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        //Referencia
        btnVolver = findViewById(R.id.btnVolver);
        tvCorreo = findViewById(R.id.tvCorreo);

        //Evento
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Recibir datos del intent
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            String correo = bundle.getString("keyCorreo", "");
            tvCorreo.setText(correo);
            int edad = bundle.getInt("keyEdad", -1);
            Log.d("TAG_", "Edad " + edad);
            if(edad < 0){
                Toast.makeText(this, "No ha ingresado edad, volviendo al formulario anterior", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }
}
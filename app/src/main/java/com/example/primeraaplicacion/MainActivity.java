package com.example.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView tvTit;
    private EditText etEmail;
    private Button btnAceptar, btnSalir;
    private TextInputLayout tilNombre;
    private  EditText etNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        referencias();
        eventos();

        Log.d("TAG_","Oncreate");

        trabajoConFechas();
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
        String nombre = "";

        nombre = tilNombre.getEditText().getText().toString();
        tilNombre.setError("error del nombre " + nombre);
        tilNombre.getEditText().setText("cambiar valor");

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

    }

    private void trabajoConFechas(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
        SimpleDateFormat sdfParse = new SimpleDateFormat("dd/MM/yyyy");


        Date fechaHoy = new Date();
        String fechaHoyStr = sdf.format(fechaHoy);


        Log.d("TAG_", "Fecha hoy Date " + fechaHoy.toString());
        Log.d("TAG_", "Fecha hoy string con formato de SimpleDateFormat " + fechaHoyStr);
        Log.d("TAG_", "Fecha de hoy en milisegundos desde el año 1970 " + fechaHoy.getTime());


        String fechaNac = "08/10/1977";
        Date fechaNacDate = null;
        int anio = 1977, mes = 10, dia = 8;
        //Parsear o convertir un string en fecha
        try{
            fechaNacDate = sdfParse.parse(fechaNac);
        }catch (Exception ex){
            Log.e("TAG_", "Fecha no corresponde");
        }
        Log.d("TAG_", "Fecha nacimiento String " + fechaNac);
        Log.d("TAG_", "Fecha nacimiento Date " + fechaNacDate.toString());

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fechaNacDate);
        calendar.add(Calendar.DAY_OF_YEAR, 30);
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

        etNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean tieneFoco) {
                if(tieneFoco)
                    tilNombre.setError(null);

            }
        });

        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("TAG_", "beforeTextChange " + s.toString());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("TAG_", "onTextChange " + s.toString());
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("TAG_", "afterTextChange " + s.toString());
            }
        });

    }

    private void referencias(){
        //Referenciar widget
        tvTit = findViewById(R.id.tvTitulo);
        etEmail = findViewById(R.id.etCorreo);

        btnAceptar = findViewById(R.id.btnOK);
        btnSalir = findViewById(R.id.btnSalir);

        tilNombre = findViewById(R.id.tilNombre);
        etNombre = findViewById(R.id.etNombre);
    }
}

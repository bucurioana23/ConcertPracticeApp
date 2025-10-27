package com.example.concertpracticeapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText numeTrupaET;
    EditText locatieET;
    EditText numarBileteET, dataET;
    RadioGroup radioGroup;
    Button button;
    //ListView listView;

    Button button2;
    Calendar calendar;

    ArrayList<Concert> listaConcerte;
    //ArrayAdapter<Concert> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numeTrupaET = findViewById(R.id.numeTrupaET);
        locatieET = findViewById(R.id.locatieET);
        numarBileteET = findViewById(R.id.numarBileteET);
        radioGroup = findViewById(R.id.radioGroup);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        dataET = findViewById(R.id.dataET);
        calendar = Calendar.getInstance();
        //listView = findViewById(R.id.listView);

        listaConcerte = new ArrayList<>();
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaConcerte);
        //listView.setAdapter(adapter);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String format = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
                dataET.setText(sdf.format(calendar.getTime()));
            }
        };

        dataET.setOnClickListener(v -> {
            new DatePickerDialog(MainActivity.this,dateSetListener,calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeTrupa = numeTrupaET.getText().toString().trim();
                String locatie = locatieET.getText().toString().trim();

                if(TextUtils.isEmpty(numeTrupa)){
                    numeTrupaET.setError("Introduceti un nume!");
                    numeTrupaET.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(locatie)) {
                    locatieET.setError("Introduceti o locatie!");
                    locatieET.requestFocus();
                    return;
                }

                int numarLocuri=0;
                try {
                    numarLocuri = Integer.parseInt(numarBileteET.getText().toString().trim());
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

                if(numarLocuri > 1000){
                    numarBileteET.setError("Numarul de bielete e prea mare!");
                    numarBileteET.requestFocus();
                    return;
                }

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                Concert.Gen gen;

                if(selectedRadioButtonId==R.id.rockRadioButton){
                    gen = Concert.Gen.Rock;
                } else
                {
                    gen = Concert.Gen.Pop;
                }

                String data = dataET.getText().toString().trim();

                String format = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.getDefault());

                Date date = null;


                try {
                    date = sdf.parse(data);
                } catch (ParseException e) {
                    Toast.makeText(MainActivity.this, "Data incorecta!", Toast.LENGTH_SHORT).show();
                }


                if(!numeTrupa.isEmpty() && !locatie.isEmpty() && numarLocuri < 1000 && date != null) {
                    Concert concert = new Concert(numeTrupa,locatie,numarLocuri,gen,date);

                    listaConcerte.add(concert);
                    //adapter.notifyDataSetChanged();

                    numeTrupaET.setText("");
                    locatieET.setText("");
                    numarBileteET.setText("");
                    dataET.setText("");
                    numeTrupaET.requestFocus();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listViewConcertsActivity.class);

                intent.putExtra("lista_concerte_key",listaConcerte);

                startActivity(intent);
            }
        });

    }
}
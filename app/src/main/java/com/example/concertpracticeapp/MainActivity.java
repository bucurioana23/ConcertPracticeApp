package com.example.concertpracticeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText numeTrupaET;
    EditText locatieET;
    EditText numarBileteET;
    RadioGroup radioGroup;
    Button button;
    //ListView listView;

    Button button2;

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
        //listView = findViewById(R.id.listView);

        listaConcerte = new ArrayList<>();
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaConcerte);
        //listView.setAdapter(adapter);

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

                if(!numeTrupa.isEmpty() && !locatie.isEmpty() && numarLocuri < 1000) {
                    Concert concert = new Concert(numeTrupa,locatie,numarLocuri,gen);

                    listaConcerte.add(concert);
                    //adapter.notifyDataSetChanged();

                    numeTrupaET.setText("");
                    locatieET.setText("");
                    numarBileteET.setText("");
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
package com.example.concertpracticeapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class listViewConcertsActivity extends AppCompatActivity {

    ArrayList<Concert> listaConcertePrimita;
    ArrayAdapter<Concert> adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_concerts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.concerteLV);

        listaConcertePrimita = (ArrayList<Concert>) getIntent().getSerializableExtra("lista_concerte_key");
        if (listaConcertePrimita == null) {
            listaConcertePrimita = new ArrayList<>();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaConcertePrimita);
        listView.setAdapter(adapter);
    }
}
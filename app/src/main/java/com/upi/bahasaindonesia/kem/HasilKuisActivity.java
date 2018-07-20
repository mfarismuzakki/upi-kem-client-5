package com.upi.bahasaindonesia.kem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.upi.bahasaindonesia.kem.models.Kuis;

public class HasilKuisActivity extends AppCompatActivity {

    private Kuis kuis = new Kuis();
    TextView jumlah_soal_benar;
    Button next, beranda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);

        Intent i = getIntent();
        kuis = (Kuis)i.getSerializableExtra("objKuis");

        jumlah_soal_benar = findViewById(R.id.jumlah_soal_benar);
        jumlah_soal_benar.setText(": " + kuis.getSoalBenar());

        beranda = findViewById(R.id.beranda);
        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HasilKuisActivity.this, BerandaActivity.class));
            }
        });
    }
}

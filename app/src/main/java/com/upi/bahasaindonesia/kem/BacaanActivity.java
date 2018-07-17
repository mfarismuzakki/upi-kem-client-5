package com.upi.bahasaindonesia.kem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.upi.bahasaindonesia.kem.models.BukuTeks;

public class BacaanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacaan);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        BukuTeks bukuTeks = (BukuTeks) getIntent().getSerializableExtra("bukuteks");

        TextView judul = findViewById(R.id.teks_buku_judul);
        TextView teks = findViewById(R.id.teks_buku_teks);
        Button tombolSelesai = findViewById(R.id.tombol_selesai_membaca);

        judul.setText(bukuTeks.getJudul());
        teks.setText(bukuTeks.getTeks());

        tombolSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}

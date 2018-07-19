package com.upi.bahasaindonesia.kem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.upi.bahasaindonesia.kem.models.Akun;
import com.upi.bahasaindonesia.kem.models.BukuTeks;

import java.util.ArrayList;

public class BerandaActivity extends AppCompatActivity {

    public static Akun akun;
    public static ArrayList<BukuTeks> bukuTeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        akun = (Akun) getIntent().getSerializableExtra("akun");
        bukuTeks = (ArrayList<BukuTeks>) getIntent().getSerializableExtra("bukuteks");

        Log.d("tes", String.valueOf(bukuTeks));

        LinearLayout tombolInfo = findViewById(R.id.tombol_informasi);
        LinearLayout tombolPetunjuk = findViewById(R.id.tombol_petunjuk);
        LinearLayout tombolLatihan = findViewById(R.id.tombol_latihan);
        LinearLayout tombolGrafik = findViewById(R.id.tombol_grafik);

        tombolInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InformasiActivity.class));
            }
        });
        tombolPetunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PetunjukActivity.class));
            }
        });
        tombolLatihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LatihanActivity.class));
            }
        });
        tombolGrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GrafikActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(BerandaActivity.this)
                .setMessage("Apakah kamu yakin ingin keluar dari aplikasi ini?")
                .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setCancelable(false)
                .show();
    }
}

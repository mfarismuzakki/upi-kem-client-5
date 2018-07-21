package com.upi.bahasaindonesia.kem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upi.bahasaindonesia.kem.models.Akun;
import com.upi.bahasaindonesia.kem.models.BukuTeks;

import java.util.ArrayList;

public class BerandaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Akun akun;
    public static ArrayList<BukuTeks> bukuTeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_navigation);

        akun = (Akun) getIntent().getSerializableExtra("akun");
        bukuTeks = (ArrayList<BukuTeks>) getIntent().getSerializableExtra("bukuteks");

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
                startActivity(new Intent(getApplicationContext(), DaftarLatihanActivity.class));
            }
        });
        tombolGrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GrafikActivity.class));
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    DrawerLayout drawerLayout = null;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profil) {

        } else if (id == R.id.tentang_kami) {

        } else if (id == R.id.reset) {

        } else if (id == R.id.logout) {

        }

        if (drawerLayout != null) {
            drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.closeDrawer(GravityCompat.START);

            TextView namaPeserta = findViewById(R.id.nama_profil);
            namaPeserta.setText(akun.getNamaLengkap());
        }

        return true;
    }
}

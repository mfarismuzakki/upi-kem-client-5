package com.upi.bahasaindonesia.kem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
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
    private TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_navigation);

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

        /*nama = findViewById(R.id.nama);
        nama.setText(akun.getNamaLengkap());*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_navigation, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.beranda) {
            // Handle the camera action
        } else if (id == R.id.profil) {

        } else if (id == R.id.tentang_kami) {

        } else if (id == R.id.reset) {

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

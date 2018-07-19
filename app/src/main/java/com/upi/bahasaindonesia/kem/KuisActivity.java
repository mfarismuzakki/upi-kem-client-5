package com.upi.bahasaindonesia.kem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.upi.bahasaindonesia.kem.globals.Variables;
import com.upi.bahasaindonesia.kem.models.Akun;
import com.upi.bahasaindonesia.kem.models.BukuTeks;
import com.upi.bahasaindonesia.kem.models.Kuis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KuisActivity extends AppCompatActivity {

    Button next;
    TextView soal;
    private Kuis kuis = new Kuis();
    String mJawaban;
    private int mNilai = 0;
    public List<String> allChoice = new ArrayList<>();
    public List<Integer> nomorUrut = new ArrayList<>();
    RadioGroup rg;
    RadioButton rb, rb1, rb2, rb3, rb4;
    String Jaw;
    Random r;
    int num = 0;
    int max = 0;
    int nilai = 0;
    int kode_pilihan_jawaban;
    String[] pilihan;
    String[] jawaban;
    Integer[] kode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);

        r = new Random();

        rg = findViewById(R.id.rbgroup);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        next = findViewById(R.id.next);
        soal = findViewById(R.id.soal);

        Intent i = getIntent();
        kuis = (Kuis)i.getSerializableExtra("objKuis");

        nomorUrut = kuis.getNomor();
        max = nomorUrut.size();

        updateSoal();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuis.setKodePilihanJawaban(kode_pilihan_jawaban);
                if (Jaw.equals(mJawaban)) {
                    mNilai = mNilai + nilai;
                }

                if (num < max) {
                    updateSoal();
                } else {

                    startActivity(new Intent(KuisActivity.this, HasilKuisActivity.class));
                }
            }
        });
    }

    public void rbclick (View v){
        int radioButton = rg.getCheckedRadioButtonId();
        rb = findViewById(radioButton);
        Jaw = rb.getText().toString();
        for (int i = 0; i < 4; i++){
            if(Jaw.equals(pilihan[i])){
                kode_pilihan_jawaban = kode[i];
            }
        }
    }

    private void updateSoal(){
        soal.setText(kuis.getTeks(nomorUrut.get(num)));

        nilai = kuis.getPoin(nomorUrut.get(num));
        allChoice.clear();
        String benar = "";
        pilihan = kuis.getChoiceTeks(nomorUrut.get(num));
        jawaban = kuis.getChoiceStatus(nomorUrut.get(num));
        kode = kuis.getChoiceKodePilihanJawaban(nomorUrut.get(num));

        for (int i = 0; i < 4; i++){
            allChoice.add(pilihan[i]);
            if (jawaban[i].equals("benar")){
                benar = pilihan[i];
            }
        }
        Collections.shuffle(allChoice);

        Log.d("jawaban", benar);

        rb1.setText(allChoice.get(0));
        rb2.setText(allChoice.get(1));
        rb3.setText(allChoice.get(2));
        rb4.setText(allChoice.get(3));

        mJawaban = benar;
        num++;
    }

    private void GameOver(){
        AlertDialog.Builder alert = new AlertDialog.Builder(KuisActivity.this);
        alert
                .setMessage("Nilai Adalah " + mNilai + ".")
                .setCancelable(false)
                .setPositiveButton("Lihat Jawaban",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), BacaanActivity.class));
                            }
                        })
                .setNegativeButton("Kembali",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    /*@SuppressLint("StaticFieldLeak")
    private class ProsesInputHasil extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            URL url = null;

            try {
                url = new URL(Variables.API + "Akun/login");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            HttpURLConnection httpURLConnection = null;

            try {
                assert url != null;
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                assert httpURLConnection != null;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }

            httpURLConnection.addRequestProperty("Accept", "application/json");
            httpURLConnection.addRequestProperty("Content-Type", "application/json");

            try {
                httpURLConnection.connect();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("nisn", masukanNisn.getText().toString());
                jsonObject.put("kata_sandi", masukanKataSandi.getText().toString());

                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jsonObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            try {
                int HttpResponse = httpURLConnection.getResponseCode();

                if (HttpResponse == HttpURLConnection.HTTP_OK) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                    String line;
                    StringBuilder stringBuilder = new StringBuilder("");

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();

                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    pesan = jsonObject.getString("pesan");

                    if (pesan.equals("OK")) {
                        akun = new Akun();
                        akun.setKode(jsonObject.getInt("kode_akun"));
                        akun.setNisn(jsonObject.getString("nisn"));
                        akun.setKataSandi(jsonObject.getString("kata_sandi"));
                        akun.setNamaLengkap(jsonObject.getString("nama_lengkap"));
                        akun.setSekolah(jsonObject.getString("sekolah"));
                        akun.setKelas(jsonObject.getInt("kelas"));
                        akun.setNomorTeksBacaan(jsonObject.getInt("nomor_buku_teks"));
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (pesan.equals("OK")) {
                Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
                intent.putExtra("akun", akun);
                intent.putExtra("bukuteks", bukuTeksArrayList);
                startActivity(intent);

                finish();
            } else {
                new AlertDialog.Builder(MasukActivity.this)
                        .setTitle("Gagal masuk!")
                        .setMessage("Pastikan masukan kamu benar.")
                        .setNegativeButton("Coba lagi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bilahKemajuan.setVisibility(View.GONE);
                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        }
    }*/
}

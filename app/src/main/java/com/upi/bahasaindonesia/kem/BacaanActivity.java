package com.upi.bahasaindonesia.kem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.upi.bahasaindonesia.kem.globals.Variables;
import com.upi.bahasaindonesia.kem.models.BukuTeks;
import com.upi.bahasaindonesia.kem.models.Kuis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BacaanActivity extends AppCompatActivity {

    private int max = 0;
    private int kd_soal = 0;
    private int count = 0;
    private List<Integer> allQuestion = new ArrayList<>();
    public Kuis kuis = new Kuis();
    private BukuTeks bukuTeks;
    public int tampKode = 0, j = 0, k = 0, l = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacaan);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        bukuTeks = (BukuTeks) getIntent().getSerializableExtra("bukuteks");

        TextView judul = findViewById(R.id.teks_buku_judul);
        TextView teks = findViewById(R.id.teks_buku_teks);
        Button tombolSelesai = findViewById(R.id.tombol_selesai_membaca);

        judul.setText(bukuTeks.getJudul());
        teks.setText(bukuTeks.getTeks());

        tombolSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BacaanActivity.this, KuisActivity.class);
                intent.putExtra("objKuis", kuis);
                startActivity(intent);
            }
        });

        new GetQuestion().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class GetQuestion extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            URL url = null;
            try {
                url = new URL(Variables.API + "Kuis/get_by_id/" + Integer.toString(bukuTeks.getKode()));
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
                httpURLConnection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            httpURLConnection.addRequestProperty("Accept", "application/json");
            httpURLConnection.addRequestProperty("Content-Type", "application/json");

            try {
                httpURLConnection.connect();
            } catch (IOException e) {
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

                    JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                    max = jsonArray.length();

                    allQuestion.clear();
                    kuis.reset();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject= jsonArray.getJSONObject(i);

                        if (i == 0){
                            tampKode = jsonObject.getInt("kode_soal_asal");
                            kuis.setKodeSoal(jsonObject.getInt("kode_soal_asal"));
                            kuis.setKodeBukuTeks(jsonObject.getInt("kode_buku_teks"));
                            kuis.setTeks(jsonObject.getString("teks_soal"));
                            kuis.setPoin(jsonObject.getInt("poin"));
                            allQuestion.add(j);
                            j++;
                            if (i > 0){
                                l++;
                            }
                            k = 0;
                        }

                        else if (tampKode != jsonObject.getInt("kode_soal_asal")) {
                            tampKode = jsonObject.getInt("kode_soal_asal");
                            kuis.setKodeSoal(jsonObject.getInt("kode_soal_asal"));
                            kuis.setKodeBukuTeks(jsonObject.getInt("kode_buku_teks"));
                            kuis.setTeks(jsonObject.getString("teks_soal"));
                            kuis.setPoin(jsonObject.getInt("poin"));
                            allQuestion.add(j);
                            j++;
                            if (i > 0){
                                l++;
                            }
                            k = 0;
                        }

                        kuis.setChoiceKodePilihanJawaban(jsonObject.getInt("kode_pilihan_jawaban"), l, k);
                        kuis.setChoiceKodeSoal(jsonObject.getInt("kode_soal"), l, k);
                        kuis.setChoiceTeks(jsonObject.getString("teks_pilihan"), l, k);
                        kuis.setChoiceStatus(jsonObject.getString("status"), l, k);
                        k++;
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
        protected void onPostExecute(final Boolean success) {
            super.onPostExecute(success);

            Log.d("sdksdj", Integer.toString(bukuTeks.getKode()));
            Collections.shuffle(allQuestion);
            kuis.setNomor(allQuestion);

            /*kd_soal = kuis.getKodeSoal(count);
            Toast.makeText(getApplicationContext(), Integer.toString(bukuTeks.getKode()), Toast.LENGTH_LONG);
            Log.d("sdksdj", Integer.toString(bukuTeks.getKode()));
            Log.d("one", Integer.toString(allQuestion.get(0)));
            Log.d("two", Integer.toString(kuis.getNomor().get(0)));
            new GetChoice().execute();*/
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

//    @Override
//    public void onBackPressed() {
//    }
}

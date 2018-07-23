package com.upi.bahasaindonesia.kem;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.upi.bahasaindonesia.kem.globals.Variables;

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
import java.util.Collections;

public class GrafikActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);

        /*GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 130),
                new DataPoint(1, 135),
                new DataPoint(2, 140),
                new DataPoint(3, 145),
                new DataPoint(4, 150),
                new DataPoint(5, 155),
                new DataPoint(6, 160),
                new DataPoint(7, 170),
                new DataPoint(8, 180),
                new DataPoint(9, 200),
                new DataPoint(10, 300),
                new DataPoint(11, 300)
        });
        graph.addSeries(series);*/



        GraphView graph2 = (GraphView) findViewById(R.id.graph2);
        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 130),
                new DataPoint(1, 135),
                new DataPoint(2, 140),
                new DataPoint(3, 145),
                new DataPoint(4, 150),
                new DataPoint(5, 155),
                new DataPoint(6, 160),
                new DataPoint(7, 170),
                new DataPoint(8, 180),
                new DataPoint(9, 200),
                new DataPoint(10, 300),
                new DataPoint(11, 300)
        });
        graph2.addSeries(series2);

        series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/11, (int) Math.abs(data.getY()*255/13), 100);
            }
        });

        series2.setSpacing(0);

        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.RED);
        /*series2.setValuesOnTopSize(50);*/



        /*GraphView graph3 = (GraphView) findViewById(R.id.graph3);
        BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -2),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph3.addSeries(series3);

        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 3),
                new DataPoint(1, 3),
                new DataPoint(2, 6),
                new DataPoint(3, 2),
                new DataPoint(4, 5)
        });
        graph3.addSeries(series4);*/



        ImageButton tombolKeluar = findViewById(R.id.tombol_keluar);

        tombolKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*@SuppressLint("StaticFieldLeak")
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

                    allQuestion.clear();
                    kuis.reset();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject= jsonArray.getJSONObject(i);

                        if (i == 0){
                            tampKode = jsonObject.getInt("kode_soal_asal");
                            kuis.setKodeSoal(jsonObject.getInt("kode_soal_asal"));
                            kuis.setTeks(jsonObject.getString("teks_soal"));
                            kuis.setPoin(jsonObject.getInt("poin"));
                            allQuestion.add(j);
                            j++;
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
                            l++;
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

            status = 1;
            Collections.shuffle(allQuestion);
            kuis.setNomor(allQuestion);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }*/

}

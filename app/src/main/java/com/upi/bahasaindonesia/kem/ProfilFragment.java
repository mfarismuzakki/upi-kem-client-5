package com.upi.bahasaindonesia.kem;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.upi.bahasaindonesia.kem.globals.Variables;

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
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    TextView restart, nisn, nama, sekolah, kelas;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);

        restart = v.findViewById(R.id.restart);
        if (BerandaActivity.akun.getNomorTeksBacaan() == 11) {
            restart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ProsesReset().execute();
                }
            });
        }

        nisn = v.findViewById(R.id.nisn);
        nisn.setText(BerandaActivity.akun.getNisn());

        nama = v.findViewById(R.id.nama_lengkap);
        nama.setText(BerandaActivity.akun.getNamaLengkap());

        sekolah = v.findViewById(R.id.sekolah);
        sekolah.setText(BerandaActivity.akun.getSekolah());

        kelas = v.findViewById(R.id.kelas);
        kelas.setText(Integer.toString(BerandaActivity.akun.getKelas()));

        return v;
    }

    @SuppressLint("StaticFieldLeak")
    private class ProsesReset extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            URL url = null;

            try {
                url = new URL(Variables.API + "Kuis/reset");
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
                jsonObject.put("kode_akun", BerandaActivity.akun.getKode());

                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(jsonObject.toString());
                dataOutputStream.flush();
                dataOutputStream.close();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            try {
                httpURLConnection.getResponseCode();
            } catch (IOException e) {
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

            startActivity(new Intent(getContext(), MasukActivity.class));

            Objects.requireNonNull(getActivity()).finish();

        }
    }

}

package com.upi.bahasaindonesia.kem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.upi.bahasaindonesia.kem.models.Kuis;

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
                if (Jaw.equals(mJawaban)) {
                    mNilai = mNilai + nilai;
                }

                if (num < max) {
                    updateSoal();
                } else {
                    GameOver();
                }
            }
        });
    }

    public void rbclick (View v){
        int radioButton = rg.getCheckedRadioButtonId();
        rb = findViewById(radioButton);
        Jaw = rb.getText().toString();
    }

    private void updateSoal(){
        soal.setText(kuis.getTeks(nomorUrut.get(num)));

        nilai = kuis.getPoin(nomorUrut.get(num));
        allChoice.clear();
        String[] pilihan;
        String[] jawaban;
        String benar = "";
        pilihan = kuis.getChoiceTeks(nomorUrut.get(num));
        jawaban = kuis.getChoiceStatus(nomorUrut.get(num));

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
}

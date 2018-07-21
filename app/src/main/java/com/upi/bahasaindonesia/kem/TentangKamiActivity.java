package com.upi.bahasaindonesia.kem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TentangKamiActivity extends AppCompatActivity {

    TextView tentang_kami;
    ImageButton tombol_keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);

        tombol_keluar = findViewById(R.id.tombol_keluar);
        tombol_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tentang_kami = findViewById(R.id.tentang_kami);
        tentang_kami.setText("\n" +
                "\n" +
                "Aplikasi ini dirancang dalam sistem Android untuk mengukur Kemampuan Efektif Membaca (KEM) kelas 5 SD. " +
                "\n" +
                "\n" +
                "Aplikasi ini dapat membantu siswa dan guru dalam proses pembelajaran. Selain itu, aplikasi ini dapat memudahkan siswa berlatih meningkatkan KEM secara mandiri di mana pun siswa berada." +
                "\n" +
                "\n" +
                "Aplikasi ini dirancang untuk setiap tingkatan kelas pada jenjang SD, SMP, dan SMA. " +
                "\n" +
                "\n" +
                "versi 1" +
                "\n" +
                "\n" +
                "Tim Perancang:" +
                "\n" +
                "1. Dewi Mustikaningsih" +
                "\n" +
                "2. Dr. Hj. Vismaia S. Damaianti, M.Pd." +
                "\n" +
                "3. Rosita Rahma, M.Pd." +
                "\n" +
                "4. Dr. Hj. Yeti Mulyati, M.Pd." +
                "\n" +
                "\n" +
                "Pengembang Aplikasi:" +
                "\n" +
                "1. Herbert Siregar, M.T." +
                "\n" +
                "2. Rizki Fauzi Rahman" +
                "\n" +
                "3. Muhammad Faris Muzakki" +
                "\n" +
                "4. Naufan Rusyda Faikar" +
                "\n" +
                "5. Yahya Firdaus" +
                "\n");
    }
}

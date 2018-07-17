package com.upi.bahasaindonesia.kem.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.upi.bahasaindonesia.kem.BacaanActivity;
import com.upi.bahasaindonesia.kem.BerandaActivity;
import com.upi.bahasaindonesia.kem.R;
import com.upi.bahasaindonesia.kem.models.BukuTeks;

import java.util.ArrayList;

public class LatihanAdapter extends RecyclerView.Adapter<LatihanAdapter.ViewHolder> {

    private ArrayList<BukuTeks> bukuTeks;
    private Context context;

    public LatihanAdapter(ArrayList<BukuTeks> bukuTeks, Context context) {
        this.bukuTeks = bukuTeks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.buku_teks, parent, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.setIsRecyclable(false);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BacaanActivity.class);
                intent.putExtra("bukuteks", bukuTeks.get(viewHolder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.judul.setText(bukuTeks.get(position).getJudul());
        holder.nomor.setText("NOMOR " + Integer.toString(position + 1));

        if (position == BerandaActivity.akun.getNomorTeksBacaan() - 1) {
            holder.gembok.setVisibility(View.GONE);
        } else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Bacaan ini terkunci", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (bukuTeks == null) ? 0 : bukuTeks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul;
        TextView nomor;
        LinearLayout wadah;
        ImageView gembok;

        ViewHolder(View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.buku_teks_judul);
            nomor = itemView.findViewById(R.id.buku_teks_nomor);
            wadah = itemView.findViewById(R.id.teks_buku_wadah);
            gembok = itemView.findViewById(R.id.buku_teks_gembok);
        }
    }

}

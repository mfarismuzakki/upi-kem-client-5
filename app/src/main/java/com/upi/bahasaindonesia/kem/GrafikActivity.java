package com.upi.bahasaindonesia.kem;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

// styling
        series2.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/11, (int) Math.abs(data.getY()*255/13), 100);
            }
        });

        series2.setSpacing(0);

// draw values on top
        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);



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
}

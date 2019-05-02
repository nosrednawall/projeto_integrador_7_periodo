package org.iel.codesimatic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;

import org.iel.codesimatic.R;

public class QtdaParadaMaquinaGraficoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtda_parada_maquina_grafico);

        LineChart chart = (LineChart) findViewById(R.id.grafico_qtda_vezes_maquina_parou);

        

    }
}

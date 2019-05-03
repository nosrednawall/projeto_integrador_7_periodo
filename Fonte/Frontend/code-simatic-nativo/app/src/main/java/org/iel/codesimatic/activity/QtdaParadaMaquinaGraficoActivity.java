package org.iel.codesimatic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.iel.codesimatic.R;
import org.iel.codesimatic.Rest.QtdaVezesMaquinaParouRest;
import org.iel.codesimatic.model.QtdaVezesMaquinaParou;

import java.util.ArrayList;
import java.util.List;

public class QtdaParadaMaquinaGraficoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtda_parada_maquina_grafico);

        List<QtdaVezesMaquinaParou> dados = new ArrayList<QtdaVezesMaquinaParou>();
        dados.addAll((List<QtdaVezesMaquinaParou>) new QtdaVezesMaquinaParouRest());

        LineChart chart = (LineChart) findViewById(R.id.grafico_qtda_vezes_maquina_parou);

        List<Entry> entries = new ArrayList<Entry>();
        for (QtdaVezesMaquinaParou data : dados) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getValueX(), data.getValueY()));
        }

    }
}

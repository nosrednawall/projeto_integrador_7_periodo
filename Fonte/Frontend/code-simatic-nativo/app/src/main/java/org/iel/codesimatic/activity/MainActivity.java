package org.iel.codesimatic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.iel.codesimatic.Controller.DadosMaquinaController;
import org.iel.codesimatic.R;
import org.iel.codesimatic.model.DadosMaquina;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List  <DadosMaquina> listagem;

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 6),
                new DataPoint(1, 1),
                new DataPoint(2, 6),
                new DataPoint(3, 1),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

//        DadosMaquinaController dados = new DadosMaquinaController();
//        dados.start();

//        if(!dados.getDados().isEmpty()){
//            listagem = new ArrayList<>();
//            listagem.addAll(dados.getDados());
//        }

    }
}

package org.iel.codesimatic.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.iel.codesimatic.R;

import org.iel.codesimatic.Rest.BuscaStatusMaquinaAsyncTask;
import org.iel.codesimatic.model.recebimento_rest.StatusMaquinaRecebimento;
import org.iel.codesimatic.util.Util;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import java.util.concurrent.TimeUnit;

public class StatusMaquinaActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private StatusMaquinaRecebimento entidadeDados;

    private LineChart chart;
    private SeekBar seekBarX;
    private TextView tvX;
    private int count;
    private float range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //criação e configuração do layout do gráfico
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_status_maquina);

        //titulo do gráfico
        setTitle("Gráfico de Linhas");

        //barra de ampliacao e o label dela
        tvX = findViewById(R.id.valor_barra_ampliacao);
        seekBarX = findViewById(R.id.barra_ampliacao);
        seekBarX.setOnSeekBarChangeListener(this);

        //chamo o grafico
        chart = findViewById(R.id.grafico_status_maquina_line);

        //sem texto de descrição
        chart.getDescription().setEnabled(false);
        chart.getDescription().setText("Descricao do gráfico");

        //abilita o toque
        chart.setTouchEnabled(true);

        //boa pergunta
        chart.setDragDecelerationFrictionCoef(0.9f);

        //enable scaling and draggind or in other words abilita o scrol ????
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        //aplica uma palheta e cores alternativa no background
        chart.setBackgroundColor(Color.WHITE);
        chart.setViewPortOffsets(0f, 0f, 0f, 0f);

        //adiciona a data
        seekBarX.setProgress(100, true);

        //consegue a legenda da data (apenas depois de definido a data)
        Legend legenda = chart.getLegend();
        legenda.setEnabled(false);

        //pega o eixo do gráfico
        XAxis xAxis = chart.getXAxis();

        //seta a posicao da legenda
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        xAxis.setTextSize(10f); //tamanho
        xAxis.setTextColor(Color.WHITE); //cor

        //desenha as grades e centraliza a legenda dentro das grades
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(255, 192, 56));
        xAxis.setCenterAxisLabels(true);

        //a granualidade do gráfico
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);// one hour

        //formata a data que irá aparecer na legenda
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM HH:mm", Locale.getDefault());
            @Override
            public String getFormattedValue(float teste) {
                long millis = TimeUnit.HOURS.toMillis((long) teste);
                return mFormat.format(new Date(millis));
            }

        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        leftAxis.setAxisMinimum(-1f);
        leftAxis.setAxisMaximum(2f);
        leftAxis.setYOffset(0f);
        leftAxis.setTextColor(Color.rgb(255, 192, 56));

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
    }


    private void setData(int count, float range) {

        this.count = count;
        this.range = range;
        ArrayList<Entry> values = new ArrayList<>();

        // now in hours
        long now = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
        // count = hours
        float to = now + count;

        // increment by 1 hour
        for (float x = now; x < to; x++) {

            float y = Util.getRandom(range, 0);
            values.add(new Entry(x, y)); // add one entry per hour
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(values, "DataSet 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1f);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);

        // create a data object with the data sets
        LineData data = new LineData(set1);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);

        // set data - é aqui que a mágica acontece
        chart.setData(data);


//        StringBuilder json = new BuscaStatusMaquinaAsyncTask("2019-05-01","2019-05-09").execute().get();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        tvX.setText(String.valueOf(seekBarX.getProgress()));

        setData(seekBarX.getProgress(), 1  );

        // redraw
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    public StatusMaquinaRecebimento getEntidadeDados() {
        return entidadeDados;
    }

    public void setEntidadeDados(StatusMaquinaRecebimento entidadeDados) {
        this.entidadeDados.setDados(entidadeDados.getDados());
        this.entidadeDados.setDataInicial(entidadeDados.getDataInicial());
        this.entidadeDados.setDataFinal(entidadeDados.getDataFinal());
    }
}

//
//        try {
//            setEntidadeDados(new StatusMaquinaRest(dataInicialString,dataFinalString).execute().get());
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        String json = "{\"dataInicial\":{\"hour\":0,\"minute\":0,\"second\":0,\"dayOfYear\":91,\"dayOfWeek\":\"MONDAY\",\"dayOfMonth\":1,\"monthValue\":4,\"year\":2019,\"month\":\"APRIL\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}},\"dataFinal\":{\"hour\":23,\"minute\":59,\"second\":0,\"dayOfYear\":151,\"dayOfWeek\":\"FRIDAY\",\"dayOfMonth\":31,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}},\"dados\":[{\"id\":0,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":0,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":1,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":1,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":2,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":2,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":3,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":3,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":4,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":4,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":5,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":5,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":6,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":6,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":7,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":7,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":8,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":8,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":9,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":9,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":10,\"power\":1,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":10,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":11,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":11,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":12,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":12,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":13,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":13,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":14,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":14,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":15,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":15,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":16,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":16,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":17,\"power\":1,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":17,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":18,\"power\":1,\"noRun\":0,\"status\":1,\"dateTime\":{\"hour\":18,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":19,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":19,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":20,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":20,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":21,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":21,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":22,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":22,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}},{\"id\":23,\"power\":0,\"noRun\":1,\"status\":0,\"dateTime\":{\"hour\":23,\"minute\":1,\"second\":0,\"dayOfYear\":121,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfMonth\":1,\"monthValue\":5,\"year\":2019,\"month\":\"MAY\",\"nano\":0,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}}]}";

//        StatusMaquinaRecebimento status = gson.fromJson(json, StatusMaquinaRecebimento.class);
//
//        setEntidadeDados(gson.fromJson(json, StatusMaquinaRecebimento.class));

//instancia o array dos valores
//
//
//        //pego a variavel com a data inicial
//        long dataInicial = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
//
//        long dataFinal = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
//
//        for( float eixoX = dataInicial; eixoX < dataFinal; eixoX++){
//            int contador = 0;
////            values.add(new Entry(entidadeDados.getColecaoDados.getPosicao[0].getData, entidadeDados.getColecaoDados.getPosicao[0].getStatus));
//            values.add(new Entry(entidadeDados.getDados().get(contador).getDia(), entidadeDados.getDados().get(contador).getStatus()));
//            contador++;
//        }


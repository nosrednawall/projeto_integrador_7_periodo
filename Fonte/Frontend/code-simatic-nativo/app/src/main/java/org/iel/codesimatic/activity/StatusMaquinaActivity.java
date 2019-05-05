package org.iel.codesimatic.activity;

import android.graphics.Color;
import android.graphics.Typeface;
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
import org.iel.codesimatic.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class StatusMaquinaActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private LineChart chart;
    private SeekBar seekBarX;
    private TextView tvX;
    private int count;
    private float range;
    protected Typeface tfRegular;
    protected Typeface tfLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //criação e configuração do layout do gráfico
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_status_maquina);

        //fonts
        tfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

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
        xAxis.setTypeface(tfLight); //font
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
        leftAxis.setTypeface(tfLight);
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

        //essa é a ideia inicial

        //instancia o array dos valores
        ArrayList<Entry> values = new ArrayList<>();

        //pego a variavel com a data inicial
        long dataInicial = TimeUnit.MILLISECONDS.toHours(entidade.getDataInicial);

        long dataFinal = TimeUnit.MILLISECONDS.toHours(entidade.getDataFinal);

        for( float eixoX = dataInicial; eixoX < dataFinal; eixoX++){
            int contador = 0;
            values.add(new Entry(entidade.getColecaoDados.getPosicao[0].getData, entidade.getColecaoDados.getPosicao[0].getStatus));
            contador++;
        }



        // now in hours
//        long now = TimeUnit.MILLISECONDS.toHours(System.currentTimeMillis());
//        // count = hours
//        float to = now + count;
//
//        // increment by 1 hour
//        for (float x = now; x < to; x++) {
//
//            float y = Util.getRandom(range, 0);
//            values.add(new Entry(x, y)); // add one entry per hour
//        }

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



}

package org.iel.codesimatic.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.WindowManager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import org.iel.codesimatic.R;

import java.util.ArrayList;

public class PowerPorcentagemMaquinaActivity extends AppCompatActivity {

    private PieChart chart;
    private float valor100Porcento = 0;
    private float valor75Porcento = 0;
    private float valor50Porcento = 0;
    private float valor25Porcento = 0;


    public PowerPorcentagemMaquinaActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        Intent intent = getIntent();
        Bundle dados = new Bundle();
        dados = intent.getExtras();

        valor100Porcento = dados.getFloat("valor100Porcento");
        valor75Porcento = dados.getFloat("valor75Porcento");
        valor50Porcento = dados.getFloat("valor50Porcento");
        valor25Porcento = dados.getFloat("valor25Porcento");


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pie_chart);

        setTitle("Percentual da potência utilizada");

        chart = findViewById(R.id.grafico_pizza);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

        chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(38f);
        chart.setTransparentCircleRadius(38f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);

        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

        chart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTextSize(12f);

        setData();
    }

    private void setData() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        PieEntry valor100PorcentoPie = new PieEntry(valor100Porcento,"Funcionamento a 100 %");
        PieEntry valor75PorcentoPie = new PieEntry(valor75Porcento,"Funcionamento a 75 %");
        PieEntry valor50PorcentoPie = new PieEntry(valor50Porcento,"Funcionamento a 50 %");
        PieEntry valor25PorcentoPie = new PieEntry(valor25Porcento,"Funcionamento a 25 %");

        entries.add(valor100PorcentoPie);
        entries.add(valor75PorcentoPie);
        entries.add(valor50PorcentoPie);
        entries.add(valor25PorcentoPie);

        PieDataSet dataSet = new PieDataSet(entries, "Percentual");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c+2);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        dataSet.setSelectionShift(10f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);
        chart.invalidate();
    }

    /**
     * Essa função adiciona o texto no centro do gráfico, e estiliza ele também
     * @return
     */
    private SpannableString generateCenterSpannableText() {

        SpannableString s;
        s = new SpannableString("Code Simatic");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 12, 0);
        return s;
    }

}

package org.iel.codesimatic.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import org.iel.codesimatic.R;

import java.util.ArrayList;


public class PieChartActivity
        extends AppCompatActivity {

    private PieChart chart;
//    private SeekBar seekBarX, seekBarY;
//    private TextView tvX, tvY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pie_chart);

        setTitle("Gráfico de Torta");

//        tvX = findViewById(R.id.tvXMax);
//        tvY = findViewById(R.id.tvYMax);

//        seekBarX = findViewById(R.id.seekBar1);
//        seekBarY = findViewById(R.id.seekBar2);
//
//        seekBarX.setOnSeekBarChangeListener(this);
//        seekBarY.setOnSeekBarChangeListener(this);

        chart = findViewById(R.id.chart1);
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

        // add a selection listener
//        chart.setOnChartValueSelectedListener(this);

//        seekBarX.setProgress(4);
//        seekBarY.setProgress(10);

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

        PieEntry valor1 = new PieEntry((float)60.0,"dia tal");
        PieEntry valor2 = new PieEntry((float)40.0,"outro dia tal");

        entries.add(valor1);
        entries.add(valor2);

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

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
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
////        tvX.setText(String.valueOf(seekBarX.getProgress()));
////        tvY.setText(String.valueOf(seekBarY.getProgress()));
//
////        setData();
//    }

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

//    @Override
//    public void onValueSelected(Entry e, Highlight h) {
//
//        if (e == null)
//            return;
//        Log.i("VAL SELECTED",
//                "Value: " + e.getY() + ", index: " + h.getX()
//                        + ", DataSet index: " + h.getDataSetIndex());
//    }
//
//    @Override
//    public void onNothingSelected() {
//        Log.i("PieChart", "nothing selected");
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {}
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {}
}
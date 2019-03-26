package org.iel.codesimatic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;

public class Dashboard extends AppCompatActivity {

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}

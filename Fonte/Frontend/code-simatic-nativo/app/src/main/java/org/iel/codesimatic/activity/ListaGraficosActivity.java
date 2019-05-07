package org.iel.codesimatic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import org.iel.codesimatic.R;

public class ListaGraficosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_graficos);

        CardView cardGraficos = (CardView) findViewById(R.id.lista_graficos_status_maquina);

        cardGraficos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statusMaquinaIntent = new Intent(getApplicationContext(), StatusMaquinaActivity.class);
                startActivity(statusMaquinaIntent);
            }
        });

    }
}

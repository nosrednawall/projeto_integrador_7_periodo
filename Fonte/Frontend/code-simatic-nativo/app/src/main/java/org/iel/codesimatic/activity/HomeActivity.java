package org.iel.codesimatic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import org.iel.codesimatic.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView cardGraficos = (CardView) findViewById(R.id.home_card_graficos);
        CardView cardConfiguracoes = (CardView) findViewById(R.id.home_card_configuracoes);
        CardView cardUsuarios = (CardView) findViewById(R.id.home_card_usuarios);
        CardView cardMaps = (CardView) findViewById(R.id.home_card_maps);
        CardView cardLogoff = (CardView) findViewById(R.id.home_card_logoff);

        cardLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Saindo...", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}

package org.iel.codesimatic.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

import org.iel.codesimatic.R;

/**
 * Autor:Anderson
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //instancia os cards da tela
        CardView cardGraficos = (CardView) findViewById(R.id.home_card_graficos);
        CardView cardConfiguracoes = (CardView) findViewById(R.id.home_card_configuracoes);
        CardView cardUsuarios = (CardView) findViewById(R.id.home_card_usuarios);
        CardView cardMaps = (CardView) findViewById(R.id.home_card_maps);
        CardView cardLogoff = (CardView) findViewById(R.id.home_card_logoff);

        //mostra o maps
        cardMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("geo:-25.514541, -49.179868");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        //efetua o logoff ao clicar no card
        cardLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Saindo...", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}

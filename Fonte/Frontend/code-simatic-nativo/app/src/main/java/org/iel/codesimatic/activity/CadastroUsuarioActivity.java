package org.iel.codesimatic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.iel.codesimatic.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //está criando uma instância para todos os componentes da tela
        setContentView(R.layout.activity_cadastro_usuario);

        Button botaoSalvar = (Button) findViewById(R.id.cadastro_usuario_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroUsuarioActivity.this, "Botão clicado", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

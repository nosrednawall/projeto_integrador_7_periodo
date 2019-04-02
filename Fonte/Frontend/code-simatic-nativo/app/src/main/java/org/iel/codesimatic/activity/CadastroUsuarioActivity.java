package org.iel.codesimatic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.iel.codesimatic.R;
import org.iel.codesimatic.model.SexoEnum;
import org.iel.codesimatic.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private static SexoEnum sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //está criando uma instância para todos os componentes da tela
        setContentView(R.layout.activity_cadastro_usuario);

        Button botaoSalvar = (Button) findViewById(R.id.cadastro_usuario_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(CadastroUsuarioActivity.this, "Usuário salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.cadastro_usuario_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.cadastro_usuario_feminino:
                        if(checkedId==1){
                           sexo = SexoEnum.FEMININO;
                        }
                    case R.id.cadastro_usuario_masculino:
                        if(checkedId==1){
                            sexo = SexoEnum.MASCULINO;
                        }
                }
            }
        });

    }

    private Usuario instanciaDadosTelaERetornaUsuario(){
        EditText nomeCompletoEditText = (EditText) findViewById(R.id.cadastro_usuario_nome);
        EditText cpfEditText = (EditText) findViewById(R.id.cadastro_usuario_cpf);
        EditText setorEditText = (EditText) findViewById(R.id.cadastro_usuario_setor);
        EditText ramalEditText = (EditText) findViewById(R.id.cadastro_usuario_ramal);
        EditText emailEditText = (EditText) findViewById(R.id.cadastro_usuario_email);
        EditText senhaEditText = (EditText) findViewById(R.id.cadastro_usuario_senha);




        return null;
    }
}

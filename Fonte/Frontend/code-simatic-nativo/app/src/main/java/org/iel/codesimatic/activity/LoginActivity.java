package org.iel.codesimatic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import org.iel.codesimatic.R;

import static org.iel.codesimatic.util.ValidacoesUtil.validaEmail;
import static org.iel.codesimatic.util.ValidacoesUtil.validaSenha;

/**
 * Classe que controla a tela de login
 */
public class LoginActivity extends AppCompatActivity {

    //variáveis que serão utilizadas
    private String email;
    private String senha;

    /**
     * Executado ao criar uma tela
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        AutoCompleteTextView emailTextView = (AutoCompleteTextView)findViewById(R.id.email);
        EditText senhaEditText = (EditText) findViewById(R.id.password);
        Button efetuaLoginOuRegistro = (Button) findViewById(R.id.email_sign_in_button);

        efetuaLoginOuRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                email = emailTextView.getText();
                Intent formulario = new Intent(getApplicationContext(),CadastroUsuarioActivity.class);
                startActivity(formulario);
            }
        });
    }

    /**
     * executado ao destruir uma tela
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }






    /**
     * valida os inputs
     * @param email
     * @param senha
     * @return boolean
     */
    private boolean validaInputs(String email, String senha){
        if(validaSenha(senha) && validaEmail(email)){
            return true;
        }else{
            return false;
        }
    }
}


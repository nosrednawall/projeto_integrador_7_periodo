package org.iel.codesimatic.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private String email = "";
    private String senha = "";

    public static final String PREFS_NAME = "code-simatic-file";

    /**
     * Executado ao criar uma tela
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //obtem a instancia da tela, eu acho
        super.onCreate(savedInstanceState);

        //recupera a instancia dos objetos
        if(savedInstanceState != null){
            //recupera os dados
        }

        //seleciona a activity s instancia ela
        setContentView(R.layout.activity_login);

        //pego a instancia do textview de email
        AutoCompleteTextView emailTextView = (AutoCompleteTextView)findViewById(R.id.email);

        //pego a instancia de valores de senha
        EditText senhaEditText = (EditText) findViewById(R.id.password);

        //pego a instancia do botão de login
        Button efetuaLoginOuRegistro = (Button) findViewById(R.id.email_sign_in_button);

        //pego as shared preferences, no modo privado
        SharedPreferences dadosLoginSalvo = getSharedPreferences(PREFS_NAME, 0);

        //verifico se possui dados salvos no sharedpreferences
        boolean possuiEmailLoginSalvo = dadosLoginSalvo.contains("email_login");

        //faco a recuperação desses dados
        if(possuiEmailLoginSalvo){
            setEmail(dadosLoginSalvo.getString("email_login",""));
            emailTextView.setText(getEmail());
        }

        //crio uma função anônima para tratar os eventos do botão
        efetuaLoginOuRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setEmail(emailTextView.getText().toString());
                //se o email não estiver vazio, salve-o no shared preferences
                if(!getEmail().isEmpty()) {
                    salvaEmailNoSharedPreferences(emailTextView.getText().toString());
                }
//                email = emailTextView.getText();
                Intent formulario = new Intent(getApplicationContext(),CadastroUsuarioActivity.class);
                startActivity(formulario);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        //meu código aqui

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //meu código aqui
    }

    /**
     * Efetua ações quando a aplicação é finalizada
     */
    @Override
    protected void onStop() {
        super.onStop();
        salvaEmailNoSharedPreferences(getEmail());
    }

    /**
     * executado ao destruir uma tela
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        salvaEmailNoSharedPreferences(getEmail());
    }

    /**
     * Funcao salva email no sharedpreferences
     * @param email
     */
    public void salvaEmailNoSharedPreferences(String email){
        SharedPreferences dadosLoginSalvo = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = dadosLoginSalvo.edit();
        editor.putString("email_login", email);

        //salva
        editor.commit();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


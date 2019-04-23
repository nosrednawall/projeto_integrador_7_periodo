package org.iel.codesimatic.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.iel.codesimatic.Dao.UsuarioDao;
import org.iel.codesimatic.R;
import org.iel.codesimatic.model.SexoEnum;
import org.iel.codesimatic.model.StatusEnum;
import org.iel.codesimatic.model.Usuario;

import java.time.LocalDateTime;

import static org.iel.codesimatic.util.ValidacoesUtil.validaUsuarioAntesdeInstanciar;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private SexoEnum sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //está criando uma instância para todos os componentes da tela
        setContentView(R.layout.activity_cadastro_usuario);

        Button botaoSalvar = (Button) findViewById(R.id.cadastro_usuario_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = instanciaDadosTelaERetornaUsuario();

                UsuarioDao dao = new UsuarioDao(CadastroUsuarioActivity.this);

                String retorno = dao.salva(usuario);

                Toast.makeText(CadastroUsuarioActivity.this, retorno, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //salva o valor do button de sexo em uma variável
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.cadastro_usuario_radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) radioGroup.findViewById(checkedId);
                String resposta = button.getText().toString();
                if(resposta.equals("Feminino")){
                    sexo = SexoEnum.FEMININO;
                }else{
                    sexo = SexoEnum.MASCULINO;
                }

            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


        super.onCreateContextMenu(menu, v, menuInfo);
    }


    private Usuario instanciaDadosTelaERetornaUsuario(){
        EditText nomeCompletoEditText = (EditText) findViewById(R.id.cadastro_usuario_nome);
        EditText cpfEditText = (EditText) findViewById(R.id.cadastro_usuario_cpf);
        EditText setorEditText = (EditText) findViewById(R.id.cadastro_usuario_setor);
        EditText ramalEditText = (EditText) findViewById(R.id.cadastro_usuario_ramal);
        EditText emailEditText = (EditText) findViewById(R.id.cadastro_usuario_email);
        EditText senhaEditText = (EditText) findViewById(R.id.cadastro_usuario_senha);


        //todo desenvolver a validacao
        if(validaUsuarioAntesdeInstanciar()){

            Usuario usuario = new Usuario();
            usuario.setCpf(cpfEditText.toString());
            usuario.setSetor(setorEditText.toString());
            usuario.setNomeCompleto(nomeCompletoEditText.toString());
            usuario.setRamal(ramalEditText.toString());
            usuario.setEmail(emailEditText.toString());
            usuario.setSenha(senhaEditText.toString());

            usuario.setStatus(StatusEnum.ATIVO);
            usuario.setSexo(sexo);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                usuario.setDataCriacao(LocalDateTime.now());
            }

            return usuario;
        }else{
            return null;
        }
    }


}

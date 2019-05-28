package org.iel.codesimatic.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.JsonParseException;

import org.iel.codesimatic.R;
import org.iel.codesimatic.model.FuncionamentoMaquinaPorcentagem;
import org.iel.codesimatic.model.LigadaDesligadaMaquinaPorcentagem;
import org.iel.codesimatic.model.PowerMaquinaPorcentagem;
import org.iel.codesimatic.util.ConexaoUtil;
import org.iel.codesimatic.util.DeserializarJsonUtil;
import org.iel.codesimatic.util.SharedPreferencesUtil;
import org.iel.codesimatic.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * Classe reponsalvel por instanciar os elementos da activity lista de graficos e buscar os relatorios
 */
public class ListaGraficosActivity extends AppCompatActivity{

    String enderecoServidor = "";
    EditText dataInicial;
    EditText dataLimite;
    Boolean dataInicialOuFinal;
    ProgressBar mProgressBar;
    boolean funcionamentoMaquina = false;


    BuscaRelatoriosMaquinaAsyncTask buscadorAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_graficos);

        //busco o ultimo endereco de servidor no shared preferences
        EditText servidor = (EditText) findViewById(R.id.lista_graficos_servidor);
        enderecoServidor = SharedPreferencesUtil.getUltimoServidorFromSharedPreferences(this);
        servidor.setText(enderecoServidor);

        Button salvarEndereco = (Button) findViewById(R.id.listgraficos_salvar_endereco);

        CardView cardGraficosStatusLigadoDesligadoPie = (CardView) findViewById(R.id.lista_graficos_status_maquina);
        CardView cardGraficoFuncionamentoPie = (CardView) findViewById(R.id.lista_graficos_torta);
        CardView cardGraficoPowerPie = (CardView) findViewById(R.id.lista_graficos_power_porcentagem);

        mProgressBar = (ProgressBar) findViewById(R.id.lista_graficos_progress_bar);

        dataInicial = (EditText) findViewById(R.id.data_inicial);
        dataLimite = (EditText) findViewById(R.id.data_limite);

        dataInicial.setText(getDataAtual(false));
        dataLimite.setText(getDataAtual(true));

        salvarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enderecoServidor.equals(servidor.getText().toString())){
                    enderecoServidor = servidor.getText().toString();
                    SharedPreferencesUtil.salvaEnderecoServidorNoSharedPreferences(getApplicationContext(),enderecoServidor);
                }
            }
        });

        dataInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInicialOuFinal = true;
                selectDate();
            }
        });

        dataLimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataInicialOuFinal = false;
                selectDate();
            }
        });

        cardGraficosStatusLigadoDesligadoPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.loggerAsyncTask("Iniciando metodo busca relatorio Status Maquina");
                //instancia o buscador asyncrono
                buscadorAsync = new BuscaRelatoriosMaquinaAsyncTask();
                //insere os dados de qual relatorio aqui
                buscadorAsync.execute(getDataInicialToString(),getDataLimiteToString(),"1");
            }
        });

        cardGraficoFuncionamentoPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.loggerAsyncTask("Iniciando metodo busca relatorio Funcionamento Maquina");
                buscadorAsync = new BuscaRelatoriosMaquinaAsyncTask();
                buscadorAsync.execute(getDataInicialToString(),getDataLimiteToString(),"0");
            }
        });

        cardGraficoPowerPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.loggerAsyncTask("Iniciando metodo busca relatorio Power Maquina");
                buscadorAsync = new BuscaRelatoriosMaquinaAsyncTask();
                buscadorAsync.execute(getDataInicialToString(),getDataLimiteToString(),"2");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferencesUtil.salvaEnderecoServidorNoSharedPreferences(this,enderecoServidor);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EditText servidor = (EditText) findViewById(R.id.lista_graficos_servidor);
        enderecoServidor = SharedPreferencesUtil.getUltimoServidorFromSharedPreferences(this);
        servidor.setText(enderecoServidor);
    }

    private void exibirProgress(boolean exibir) {
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }

    private String getDataInicialToString(){
        dataInicial = (EditText) findViewById(R.id.data_inicial);
        return dataInicial.getText().toString();
    }

    private String getDataLimiteToString(){
        dataLimite = (EditText) findViewById(R.id.data_limite);
        return dataLimite.getText().toString();
    }

    void relatorioGraficoPizza(FuncionamentoMaquinaPorcentagem dados){
        cancelarTask();

        Bundle bundle = new Bundle();
        bundle.putFloat("somaAutoMan", dados.getSomaAutoMan());
        bundle.putFloat("somaRunCmd", dados.getSomaRunCmd());

        Intent graficoPizzaFuncionamento = new Intent(getApplicationContext(), FuncionamentoMaquinaPizzaActivity.class);
        graficoPizzaFuncionamento.putExtras(bundle);

        startActivity(graficoPizzaFuncionamento);
    }

    void relatorioLigadoDeslgadoPizza(LigadaDesligadaMaquinaPorcentagem dados){
        cancelarTask();

        Bundle bundle = new Bundle();
        bundle.putFloat("somaLigado", dados.getSomaLigada());
        bundle.putFloat("somaDesligado", dados.getSomaDesligada());

        Intent maquinaLigadaDesligadaActivity = new Intent(getApplicationContext(), MaquinaLigadaDesligadaActivity.class);
        maquinaLigadaDesligadaActivity.putExtras(bundle);

        startActivity(maquinaLigadaDesligadaActivity);
    }


    private void relatorioPowerPorcentagem(PowerMaquinaPorcentagem dados) {
        cancelarTask();

        Bundle bundle = new Bundle();
        bundle.putFloat("valor100Porcento", dados.getSoma100Porcento());
        bundle.putFloat("valor75Porcento", dados.getSoma75Porcento());
        bundle.putFloat("valor50Porcento", dados.getSoma50Porcento());
        bundle.putFloat("valor25Porcento", dados.getSoma25Porcento());
        bundle.putString("dataInicial", getDataInicialToString());
        bundle.putString("dataLimite", getDataLimiteToString());

        Intent intent = new Intent(getApplicationContext(), PowerPorcentagemMaquinaActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    /**
     * Classe responsável por instanciar o datapicker e pegar a data selecionado pelo usuário
     */
    public static class SelectDateFragment  extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        EditText data;

        public SelectDateFragment(EditText data) {
            this.data = data;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar calendario = Calendar.getInstance();

            int yy = calendario.get(Calendar.YEAR);
            int mm =  calendario.get(Calendar.MONTH);
            int dd = calendario.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(data, yy, mm+1, dd);
        }
    }

    /**
     * funcao que instancia o fragmento e seleciona o label???
     */
    public void selectDate() {
        DialogFragment newFragment;
        if(dataInicialOuFinal) {
            newFragment = new SelectDateFragment(dataInicial);
        }else{
            newFragment = new SelectDateFragment(dataLimite);
        }
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    /**
     * Funcao que atualiza o label
     * @param mEditText
     * @param year
     * @param month
     * @param day
     */
    public static void populateSetDate(EditText mEditText, int year, int month, int day) {
        mEditText.setText(day+"/"+month+"/"+year);
    }

    /**
     * funcao retorna a data atual
     * @param dataFinal
     * @return
     */
    public static String getDataAtual(boolean dataFinal){

        final Calendar calendario = Calendar.getInstance();

        int yy = calendario.get(Calendar.YEAR);
        int mm =  calendario.get(Calendar.MONTH);
        int dd = calendario.get(Calendar.DAY_OF_MONTH);
        mm++;

        if(dataFinal) {
            return dd + "/" + mm + "/" + yy;
        }else{
            dd--;
            return dd + "/" + mm + "/" + yy;
        }
    }


    public void cancelarTask()
    {
        buscadorAsync.cancel(true);
    }

    /**
     * Classe responsável por efetuar as requisições rest
     */
    class BuscaRelatoriosMaquinaAsyncTask extends AsyncTask<String, Integer, String> {

        //objeto conexao
        private HttpURLConnection conexao;
        //objeto url
        private URL url = null;
        //código de resposta da conexao
        int codigo_resposta=404;
        //numero do relatorio selecionado
        int numeroRelatorio = 0;

        /**
         * Método que efetua as ações que devem ser feitas antes de iniciar a conexao
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mostra o pregress dialig na tela do usuário enquanto o processo está sendo executado
            exibirProgress(true);
            //log
            Util.loggerAsyncTask("Iniciando a classe asyncrona");
        }

        /**
         * Função efetua a requisição Asyncrona em outro Thread
         * @param parametros
         * @return
         */
        @Override
        protected String doInBackground(String... parametros) {

            //log
            Util.loggerAsyncTask("setando os parametros nas variaveis da classe");

            //pego os parametros e insiro em suas variaveis
            String dataInicio = parametros[0].toString();
            String dataLimite = parametros[1].toString();
            String tipoRelatorio = switchTipoRelatorio(parametros[2]);
            numeroRelatorio = Integer.parseInt(parametros[2]);

            //log
            Util.loggerAsyncTask("Montando a url");

            //Monta a URL
            try {
                url = new URL(enderecoServidor+ConexaoUtil.PORTA_SERVIDOR+"code-simatic/rest/dados-maquina/"+ tipoRelatorio +"?data_inicial="+ dataInicio +"&data_limite=" + dataLimite);
                //log
                Util.loggerAsyncTask("url formada: "+url);

            //caso dê alguma excessão
            } catch (MalformedURLException e) {
                Log.e("GET_status_maquina", "Erro  - " + e.getMessage());
                Util.loggerAsyncTask("erro ao pegar o status do servidor, mensagem de erro:  " + e.getMessage());
            } catch (Exception e) {
                Log.e("GET_status_maquina", "Exception - " + e.getMessage());
                Util.loggerAsyncTask("Excessao ao pegar status da maquina antes de abrir conexao, mensagem excessao: " + e.getMessage());
            }

            //Abre a conexão
            Util.loggerAsyncTask("Abrindo conexao");
            try {
                //abre a conexão
                conexao = (HttpURLConnection) url.openConnection();
                //timeout de conexao
                conexao.setConnectTimeout(ConexaoUtil.CONEXAO_TIMEOUT);
                //timeout de leitura
                conexao.setReadTimeout(ConexaoUtil.LEITURA_CEP_TIMEOUT);
                //insere o tipo de requisição http a ser efetuada
                conexao.setRequestMethod("GET");
                //tipo de codificação dos dados é utf-8
                conexao.setRequestProperty("charset", "utf-8");
                //espera retorno
                conexao.setDoInput(true);
                //tipo do retorno é json
                conexao.setRequestProperty("Accept", "application/json");
                //efetua a conexao
                conexao.connect();
            } catch (IOException e) {
                //log e exeção
                Util.loggerAsyncTask("Excessao ao pegar status da maquina depois de abrir conexao, mensagem excessao: " + e.getMessage());
            }
            //log
            Util.loggerAsyncTask("Abriu conexao, iniciando o download da requisicao");
            try {
                //pega o retorno da conexao
                codigo_resposta = conexao.getResponseCode();

                //caso seja 200 ok inicia a deserialização do texto em objeto json
                if (codigo_resposta == HttpURLConnection.HTTP_OK) {
                    //pega o texto inteiro da resposta
                    InputStream resposta_servidor = conexao.getInputStream();
                    //cria um buffer leitor
                    BufferedReader reader = new BufferedReader(new InputStreamReader(resposta_servidor));
                    //instancia um construtor de strings
                    StringBuilder construtor_resposta = new StringBuilder();
                    // string que recebera ca linha do while
                    String linha;
                    //faz um loop de todas as linhas, até chegar na linha nula, e vai construindo a string com o json
                    while ((linha = reader.readLine()) != null) {
                        construtor_resposta.append(linha);
                    }
                    //retorna a string com o json
                    return construtor_resposta.toString();
                } else {
                    //log ca excessão
                    Util.loggerAsyncTask("Erro ao deserializar a resposta em json");
                    return "erro";
                }

            } catch (IOException e) {
                //log
                Util.loggerAsyncTask("Excessao ao deserializar mensagem em json, mensagem excessao: " + e.getMessage());
                return e.toString();
            } finally {
                //depois de tudo desconecta
                conexao.disconnect();
            }
        }

        /**
         * Função efetua a deserialização do json para objeto e instancia a classe do relatório escolhido
         * @param resultado
         */
        @Override
        protected void onPostExecute(String resultado) {
            //encerra o progress dialog
            exibirProgress(false);
            //verifica se o código recebido foi por um 200
            if(codigo_resposta == HttpURLConnection.HTTP_OK) {
                //log
                Util.loggerAsyncTask("inicia o processo de deserializacao dos dados" + resultado);

                /**
                 * Inicia os if dos relatórios
                 */

                //relatório de funcionamento automatico e manual
                if(numeroRelatorio == 0) {
                    //objeto que receberá os dados do json
                    FuncionamentoMaquinaPorcentagem dados;
                    try {
                        //tenta deserializar o json
                        dados = DeserializarJsonUtil.jsonToRelatorioFuncionamento(resultado);
                        //chama o método que instancia a Activity do relatório
                        relatorioGraficoPizza(dados);
                    } catch (JsonParseException e) {
                        //log
                        Util.loggerAsyncTask("Execessao ao deserializar o java dentro de onpostexecute " + e.getMessage());
                    }
                    //relatorio porcentagem de tempo da maquina ligada e desligada no período selecionado
                }else if(numeroRelatorio == 1){
                    //objeto que receberá os dados do json
                    LigadaDesligadaMaquinaPorcentagem dados;
                    try {
                        //tenta deserializar o json
                        dados = DeserializarJsonUtil.jsonToRelatorioStatus(resultado);
                        //chama o método que instancia a Activity do relatório
                        relatorioLigadoDeslgadoPizza(dados);
                    } catch (JsonParseException e) {
                        //log
                        Util.loggerAsyncTask("Execessao ao deserializar o java dentro de onpostexecute " + e.getMessage());
                    }
                    //relatorio de porcentagem da portencia utilizada na maquina
                }else if(numeroRelatorio == 2){
                    //objeto que receberá os dados do json
                    PowerMaquinaPorcentagem dados;
                    try {
                        //tenta deserializar o json
                        dados = DeserializarJsonUtil.jsonToRelatorioPower(resultado);
                        //chama o método que instancia a Activity do relatório
                        relatorioPowerPorcentagem(dados);
                    } catch (JsonParseException e) {
                        //log
                        Util.loggerAsyncTask("Execessao ao deserializar o java dentro de onpostexecute " + e.getMessage());
                    }
                }
            }
        }


        private String switchTipoRelatorio(String tipoRelatorio){

            String relatorio = "";

            switch (tipoRelatorio){

                case "0":
                    relatorio = "funcionamento/porcentagem";
                    break;

                case "1":
                    relatorio = "status/porcentagem";
                    break;

                case "2":
                    relatorio = "power/porcentagem";
                    break;

                default:
                    relatorio = "funcionamento/porcentagem";
            }
            return relatorio;
        }
    }

}

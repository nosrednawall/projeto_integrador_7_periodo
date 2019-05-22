package org.iel.codesimatic.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.gson.JsonParseException;

import org.iel.codesimatic.R;
import org.iel.codesimatic.Rest.RelatorioFuncionamentoMaquinaRetorno;
import org.iel.codesimatic.util.ConexaoUtil;
import org.iel.codesimatic.util.DeserializarJsonUtil;

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

    EditText dataInicial;
    EditText dataLimite;
    Boolean dataInicialOuFinal;
    ProgressBar mProgressBar;

    boolean jaExecutou = false;
    boolean jaEfetuouDownloadDoRelatorio = false;

    BuscaRelatoriosMaquinaAsyncTask buscadorAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_graficos);

        CardView cardGraficos = (CardView) findViewById(R.id.lista_graficos_status_maquina);
        CardView cardGraficoPie = (CardView) findViewById(R.id.lista_graficos_torta);
        mProgressBar = (ProgressBar) findViewById(R.id.lista_graficos_progress_bar);


        dataInicial = (EditText) findViewById(R.id.data_inicial);
        dataLimite = (EditText) findViewById(R.id.data_limite);

        dataInicial.setText(getDataAtual(false));
        dataLimite.setText(getDataAtual(true));

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

        cardGraficos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent statusMaquinaIntent = new Intent(getApplicationContext(), StatusMaquinaActivity.class);
                startActivity(statusMaquinaIntent);
            }
        });

        cardGraficoPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggerAsyncTask("Iniciando metodo busca relatorio Funcionamento Maquina");
                //instancia a classe de
                buscadorAsync = new BuscaRelatoriosMaquinaAsyncTask();
                buscadorAsync.execute(getDataInicialToString(),getDataLimiteToString(),"0");
            }
        });
    }

    private void exibirProgress(boolean exibir) {
        mProgressBar.setVisibility(exibir ? View.VISIBLE : View.GONE);
    }


    private String getDataInicialToString(){
        dataInicial = (EditText) findViewById(R.id.data_inicial);
        return dataInicial.toString();
    }

    private String getDataLimiteToString(){
        dataLimite = (EditText) findViewById(R.id.data_limite);
        return dataLimite.toString();
    }

    void relatorioGraficoPizza(RelatorioFuncionamentoMaquinaRetorno dados){
        Intent graficoPizzaFuncionamento = new Intent(getApplicationContext(), FuncionamentoMaquinaPizzaActivity.class);
        graficoPizzaFuncionamento.putExtra("RelatorioFuncionamentoMaquinaRetorno", dados);

        startActivity(graficoPizzaFuncionamento);
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

    private static void loggerAsyncTask(String mensagem){
        Log.i("AsyncTask",mensagem+" Thread: " + Thread.currentThread().getName());
    }

    public void cancelarTask()
    {
        buscadorAsync.cancel(true);
    }

    class BuscaRelatoriosMaquinaAsyncTask extends AsyncTask<String, Integer, String> {

        private HttpURLConnection conexao;
        private URL url = null;
        int codigo_resposta=404;

        @Override
        protected void onPreExecute() {
            loggerAsyncTask("Iniciando a classe asyncrona");
        }

        @Override
        protected String doInBackground(String... parametros) {

            loggerAsyncTask("setando os parametros nas variaveis da classe");

            //pego os parametros e insiro em suas variaveis
            String dataInicio = parametros[0];
            String dataLimite = parametros[1];
            String tipoRelatorio = switchTipoRelatorio(parametros[2]);

            loggerAsyncTask("Montando a url");

            //Monta a URL
            try {
//                url = new URL(ConexaoUtil.CONEXAO_LOCAL+tipoRelatorio+"?data_inicio="+dataInicio+"&&data_limite="+dataLimite);
                url = new URL("http://192.168.9.26:8080/code-simatic/rest/dados-maquina/funcionamento/porcentagem");


            } catch (MalformedURLException e) {
                Log.e("GET_status_maquina", "Erro  - " + e.getMessage());
                loggerAsyncTask("erro ao pegar o status do servidor, mensagem de erro:  " + e.getMessage());
            } catch (Exception e) {
                Log.e("GET_status_maquina", "Exception - " + e.getMessage());
                loggerAsyncTask("Excessao ao pegar status da maquina antes de abrir conexao, mensagem excessao: " + e.getMessage());
            }

            //Abre a conexão
            loggerAsyncTask("Abrindo conexao");
            try {
                conexao = (HttpURLConnection) url.openConnection();
                conexao.setConnectTimeout(ConexaoUtil.CONEXAO_TIMEOUT);
                conexao.setReadTimeout(ConexaoUtil.LEITURA_CEP_TIMEOUT);
                conexao.setRequestMethod("GET");
                conexao.setRequestProperty("charset", "utf-8");

                conexao.setDoInput(true);
                conexao.setRequestProperty("Accept", "application/json");

                conexao.connect();
            } catch (IOException e) {
                Log.e("GET_status_maquina", "IOException - " + e.getMessage());
                loggerAsyncTask("Excessao ao pegar status da maquina depois de abrir conexao, mensagem excessao: " + e.getMessage());
            }
            loggerAsyncTask("Abriu conexao, iniciando o download da requisicao");
            try {
                codigo_resposta = conexao.getResponseCode();
                if (codigo_resposta == HttpURLConnection.HTTP_OK) {
                    InputStream resposta_servidor = conexao.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(resposta_servidor));
                    StringBuilder construtor_resposta = new StringBuilder();
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        construtor_resposta.append(linha);
                    }
                    return construtor_resposta.toString();
                } else {
                    loggerAsyncTask("Erro ao deserializar a resposta em json");
                    return "erro";
                }

            } catch (IOException e) {
                Log.e("GET_status_maquina", "IOException - " + e.getMessage());
                loggerAsyncTask("Excessao ao deserializar mensagem em json, mensagem excessao: " + e.getMessage());
                return e.toString();
            } finally {
                conexao.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String resultado) {
            if(codigo_resposta == HttpURLConnection.HTTP_OK) {
                loggerAsyncTask("O que será isso? " + resultado);
                RelatorioFuncionamentoMaquinaRetorno dados;
                try {
                    dados = DeserializarJsonUtil.jsonToRelatorioFuncionamento(resultado);
                    relatorioGraficoPizza(dados);

                } catch (JsonParseException e) {
                    loggerAsyncTask("Execessao ao deserializar o java dentro de onpostexecute " + e.getMessage());
                }
            }
        }

        private String switchTipoRelatorio(String tipoRelatorio){

            String relatorio = "";

            switch (tipoRelatorio){

                case "0":
                    relatorio = "funcionamento/porcentagem";
                    break;

                default:
                    relatorio = "funcionamento/porcentagem";
            }
            return relatorio;
        }
    }
}

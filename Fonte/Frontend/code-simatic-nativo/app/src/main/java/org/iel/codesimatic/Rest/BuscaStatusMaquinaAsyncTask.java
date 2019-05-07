package org.iel.codesimatic.Rest;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.iel.codesimatic.model.dimensao.StatusMaquina;
import org.iel.codesimatic.model.recebimento_rest.StatusMaquinaRecebimento;
import org.iel.codesimatic.util.ConexaoUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Classe que rodará o background
 */

public class BuscaStatusMaquinaAsyncTask extends AsyncTask<String, String, String> {

    private String dataInicio;
    private String dataLimite;
    private HttpURLConnection conexao;
    private URL url = null;
    private int cod_resposta;

    public BuscaStatusMaquinaAsyncTask(String dataInicio, String dataLimite) {
        this.dataInicio = dataInicio;
        this.dataLimite = dataLimite;
    }

    @Override
    protected String doInBackground(String... strings) {
        //Monta a URL
        try {
            url = new URL(
                    ConexaoUtil.CONEXAO_LOCAL+
                    "?data_inicio="+dataInicio+
                    "&data_limite="+dataLimite);
        } catch (MalformedURLException e) {
            Log.e("GET_status_maquina", "Erro  - " + e.getMessage());
        } catch (Exception e) {
            Log.e("GET_status_maquina", "Exception - " + e.getMessage());
        }

        //Abre a conexão
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
        }

        try {
            cod_resposta = conexao.getResponseCode();
            if (cod_resposta == HttpURLConnection.HTTP_OK) {
                InputStream resposta_servidor = conexao.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(resposta_servidor));
                StringBuilder construtor_resposta = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null) {
                    construtor_resposta.append(linha);
                }
                return (construtor_resposta.toString());
            } else {
                return "erro";
            }

        } catch (IOException e) {
            Log.e("GET_status_maquina", "IOException - " + e.getMessage());
            return e.toString();
        } finally {
            conexao.disconnect();
        }
    }
}


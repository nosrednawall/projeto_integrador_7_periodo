package org.iel.codesimatic.Rest;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.iel.codesimatic.model.QtdaVezesMaquinaParou;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que rodará o background
 */
public class StatusMaquinaRest extends AsyncTask<Void, Void, List<QtdaVezesMaquinaParou>> {


    @Override
    protected List<QtdaVezesMaquinaParou> doInBackground(Void... voids) {

        List<QtdaVezesMaquinaParou> lista = new ArrayList<QtdaVezesMaquinaParou>();
        Gson gson = new Gson();

        try{
            //url
            URL url = new URL("http://0.0.0.0:8080/code-simatic/rest/dados-maquina/maquina-parou?data_inicial=2019-04-01&data_limite=2019-05-02");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //cabeçalho e tempo de timeout
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            //efetua a conexao
            connection.connect();

            //captura os dados recebidos e salva na varialvel resposta
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                StringBuilder resposta = new StringBuilder();
                resposta.append(scanner.next());
               QtdaVezesMaquinaParou qtda = gson.fromJson(resposta.toString(), QtdaVezesMaquinaParou.class);
                lista.add(qtda);
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

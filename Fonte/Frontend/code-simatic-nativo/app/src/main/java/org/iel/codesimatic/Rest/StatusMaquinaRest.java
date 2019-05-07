package org.iel.codesimatic.Rest;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.iel.codesimatic.model.dimensao.StatusMaquina;
import org.iel.codesimatic.model.recebimento_rest.StatusMaquinaRecebimento;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Classe que rodará o background
 */
public class StatusMaquinaRest extends AsyncTask<Void, Void, StatusMaquinaRecebimento> {

    //construtor
    public StatusMaquinaRest(){

    }

    @Override
    protected StatusMaquinaRecebimento doInBackground(Void... voids) {

        Gson gson = new Gson();
        StringBuilder resposta = new StringBuilder();
        StatusMaquinaRecebimento status;

        try{
            //url
            URL url = new URL("http://0.0.0.0:8080/code-simatic/rest/dados-maquina/status?data_inicial=2019-04-01&data_limite=2019-05-02");
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

                resposta.append(scanner.next());
            }




        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        status = gson.fromJson(resposta.toString(), StatusMaquinaRecebimento.class);
        System.out.println(status);

        return status;
    }
}

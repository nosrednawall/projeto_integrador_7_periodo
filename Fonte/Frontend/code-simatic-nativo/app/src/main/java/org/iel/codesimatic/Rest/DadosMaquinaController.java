package org.iel.codesimatic.Rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.iel.codesimatic.model.DadosMaquina;
import org.iel.codesimatic.Interface.DadosMaquinaInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.converter.gson.GsonConverterFactory;

public class DadosMaquinaController implements Callback<List<DadosMaquina>> {

    static final String BASE_URL = "http://192.168.0.119:8080/";

    private List<DadosMaquina> dados = new ArrayList<>();

    public List<DadosMaquina> getDados() {
        return dados;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        DadosMaquinaInterface rest = retrofit.create(DadosMaquinaInterface.class);

        Call<List<DadosMaquina>> call = rest.listaDados();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<DadosMaquina>> call, Response<List<DadosMaquina>> response){
        if(response.isSuccessful()) {
            List<DadosMaquina> dadosMaquinaList = response.body();
            for(DadosMaquina dado: dadosMaquinaList){
                System.out.println(dado.toString());
            }
            this.dados.addAll(dadosMaquinaList);
        } else {
            System.out.println("Deu erro");
            System.out.println( response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<DadosMaquina>> call, Throwable t){
        t.printStackTrace();
    }
}

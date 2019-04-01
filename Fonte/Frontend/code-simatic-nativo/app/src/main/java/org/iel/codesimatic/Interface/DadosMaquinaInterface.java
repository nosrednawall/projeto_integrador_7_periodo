package org.iel.codesimatic.Interface;

import org.iel.codesimatic.model.DadosMaquina;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DadosMaquinaInterface {

    @GET("rest/dados-maquina")
    Call<List<DadosMaquina>> listaDados();

}

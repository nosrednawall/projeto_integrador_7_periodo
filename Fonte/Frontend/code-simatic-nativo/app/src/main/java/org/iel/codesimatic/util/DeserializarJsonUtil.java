package org.iel.codesimatic.util;

import com.google.gson.Gson;

import org.iel.codesimatic.Rest.RelatorioFuncionamentoMaquinaRetorno;

public class DeserializarJsonUtil {

    public static RelatorioFuncionamentoMaquinaRetorno getRelatorioFuncionamentoMaquina(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,RelatorioFuncionamentoMaquinaRetorno.class);
    }

}

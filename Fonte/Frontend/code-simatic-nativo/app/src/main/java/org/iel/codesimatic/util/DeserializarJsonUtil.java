package org.iel.codesimatic.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.iel.codesimatic.model.FuncionamentoMaquinaPorcentagem;

public class DeserializarJsonUtil {

    private static String tag_jsonToRelatorioFuncionamento = "Deserialiando json relatorio de funcionamento";

    public static FuncionamentoMaquinaPorcentagem jsonToRelatorioFuncionamento(String stringJson) {

        GsonBuilder builder = new GsonBuilder();
        FuncionamentoMaquinaPorcentagem relatorio = new FuncionamentoMaquinaPorcentagem();
        try {
            Gson gson = builder.create();
            relatorio = gson.fromJson(stringJson, FuncionamentoMaquinaPorcentagem.class);
        } catch (JsonSyntaxException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonSyntaxException - " + e.getMessage());
        } catch (JsonParseException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonParseException - " + e.getMessage());
        } catch (Exception e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "Exception - " + e.getMessage());
        }

        return relatorio;
    }

    public static String dadoToJson(String tipo_dado, String dado)
    {
        JsonObject dadoJson = new JsonObject();
        dadoJson.addProperty(tipo_dado,dado);
        return dadoJson.toString();
    }

}

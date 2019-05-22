package org.iel.codesimatic.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.iel.codesimatic.Rest.RelatorioFuncionamentoMaquinaRetorno;
import org.json.JSONException;
import org.json.JSONObject;

public class DeserializarJsonUtil {

    private static String tag_jsonToRelatorioFuncionamento = "Deserialiando json relatorio de funcionamento";

    public static RelatorioFuncionamentoMaquinaRetorno jsonToRelatorioFuncionamento(String stringJson) {

        GsonBuilder builder = new GsonBuilder();
        RelatorioFuncionamentoMaquinaRetorno relatorio = new RelatorioFuncionamentoMaquinaRetorno();
        try {
            Gson gson = builder.create();
            relatorio = gson.fromJson(stringJson, RelatorioFuncionamentoMaquinaRetorno.class);
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

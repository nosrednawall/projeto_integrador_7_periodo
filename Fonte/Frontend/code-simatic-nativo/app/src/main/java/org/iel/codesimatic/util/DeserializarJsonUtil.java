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

    public static RelatorioFuncionamentoMaquinaRetorno getRelatorioFuncionamentoMaquina(JSONObject jsonRelatorio){
        RelatorioFuncionamentoMaquinaRetorno relatorio = new RelatorioFuncionamentoMaquinaRetorno();
        try {
            if(!jsonRelatorio.has("erro"))
            {
                relatorio.setSomaAutoMan(jsonRelatorio.getString("somaAutoMan"));
                relatorio.setSomaRunCmd(jsonRelatorio.getString("somaRunCmd"));
            }
        } catch (JSONException e) {
            Log.e("ConvertJSONtoEndereco","JSONException - " + e.getMessage());
            relatorio=null;
        }
        return relatorio;
    }

    public static RelatorioFuncionamentoMaquinaRetorno jsonToRelatorioFuncionamento(String stringJson) {
        JsonParser conversor = new JsonParser();
        JsonArray json_relatorio = conversor.parse(stringJson).getAsJsonArray();

        //GsonBuilder é um construtor de instancia de Gson configuravel
        GsonBuilder builder = new GsonBuilder();
        RelatorioFuncionamentoMaquinaRetorno relatorio = new RelatorioFuncionamentoMaquinaRetorno();
        try {
            Gson gson = builder.create();
            relatorio = gson.fromJson(json_relatorio, RelatorioFuncionamentoMaquinaRetorno.class);
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

package org.iel.codesimatic.util;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.iel.codesimatic.model.FuncionamentoMaquinaPorcentagem;
import org.iel.codesimatic.model.LigadaDesligadaMaquinaPorcentagem;
import org.iel.codesimatic.model.PowerMaquinaPorcentagem;
import org.json.JSONObject;

public class DeserializarJsonUtil {

    private static String tag_jsonToRelatorioFuncionamento = "Deserialiando json relatorio de funcionamento";

    public static FuncionamentoMaquinaPorcentagem jsonToRelatorioFuncionamento(String stringJson) {

        FuncionamentoMaquinaPorcentagem relatorio = new FuncionamentoMaquinaPorcentagem();
        try {
            JSONObject dadoJson = new JSONObject(stringJson);
            relatorio.setSomaAutoMan(Float.parseFloat(dadoJson.getString("somaAutoMan")));
            relatorio.setSomaRunCmd(Float.parseFloat(dadoJson.getString("somaRunCmd")));

        } catch (JsonSyntaxException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonSyntaxException - " + e.getMessage());
        } catch (JsonParseException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonParseException - " + e.getMessage());
        } catch (Exception e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "Exception - " + e.getMessage());
        }

        return relatorio;
    }

    public static LigadaDesligadaMaquinaPorcentagem jsonToRelatorioStatus(String stringJson) {
        LigadaDesligadaMaquinaPorcentagem relatorio = new LigadaDesligadaMaquinaPorcentagem();
        try{
            JSONObject dadoJson = new JSONObject(stringJson);

            relatorio.setSomaLigada(Float.parseFloat(dadoJson.getString("somaLigado")));
            relatorio.setSomaDesligada(Float.parseFloat(dadoJson.getString("somaDesligado")));

        }catch (JsonSyntaxException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonSyntaxException - " + e.getMessage());
        } catch (JsonParseException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonParseException - " + e.getMessage());
        } catch (Exception e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "Exception - " + e.getMessage());
        }

        return relatorio;
    }

    public static PowerMaquinaPorcentagem jsonToRelatorioPower(String stringJson) {
        PowerMaquinaPorcentagem relatorio = new PowerMaquinaPorcentagem();
        try{
            JSONObject dadoJson = new JSONObject(stringJson);

            relatorio.setSoma100Porcento(Float.parseFloat(dadoJson.getString("soma100Porcento")));
            relatorio.setSoma75Porcento(Float.parseFloat(dadoJson.getString("soma75Porcento")));
            relatorio.setSoma50Porcento(Float.parseFloat(dadoJson.getString("soma50Porcento")));
            relatorio.setSoma25Porcento(Float.parseFloat(dadoJson.getString("soma25Porcento")));

        }catch (JsonSyntaxException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonSyntaxException - " + e.getMessage());
        } catch (JsonParseException e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "JsonParseException - " + e.getMessage());
        } catch (Exception e) {
            Log.e(tag_jsonToRelatorioFuncionamento, "Exception - " + e.getMessage());
        }

        return relatorio;

    }
}

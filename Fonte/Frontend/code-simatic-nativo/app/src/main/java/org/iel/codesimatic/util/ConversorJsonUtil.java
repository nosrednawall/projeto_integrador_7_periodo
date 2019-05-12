package org.iel.codesimatic.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.iel.codesimatic.model.dimensao.StatusMaquina;
import org.iel.codesimatic.model.recebimento_rest.StatusMaquinaRecebimento;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class ConversorJsonUtil {

    public static String dadoToJson(String tipo_dado, String dado)
    {
        JsonObject dadoJson = new JsonObject();
        dadoJson.addProperty(tipo_dado,dado);
        return dadoJson.toString();
    }

    public static StatusMaquinaRecebimento jsonToStatusMaquinaRecebimento(String stringJson){
        JsonParser conversor = new JsonParser();
        JsonArray jsonArray_dados = conversor.parse(stringJson).getAsJsonArray();

        //GsonBuilder é um construtor de instancia de Gson configuravel
        GsonBuilder builder = new GsonBuilder();

        //Registra um adaptador para converter a data em timestamp do Json para Date
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        StatusMaquinaRecebimento status = new StatusMaquinaRecebimento();
        try{
            Gson gson = builder.create();

            status = gson.fromJson(stringJson, StatusMaquinaRecebimento.class);


        }catch (JsonSyntaxException e)
        {
            Log.e("Conversao status","JsonSyntaxException - "+ e.getMessage());
        }
        catch (JsonParseException e)
        {
            Log.e("Conversao status maquina","JsonParseException - "+ e.getMessage());
        }catch (Exception e)
        {
            Log.e("COnversao status maquina","Exception - "+ e.getMessage());
        }

        return status;
    }

}

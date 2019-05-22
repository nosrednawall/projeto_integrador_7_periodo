package org.iel.codesimatic.util;

public final class ConexaoUtil {
//    public static String CONEXAO_LOCAL = "http://192.168.0.119:8080/code-simatic/rest/dados-maquina/";
    public static String CONEXAO_LOCAL = "http://192.168.9.26:8080/code-simatic/rest/dados-maquina/";

    //Milissegundos
    public static int CONEXAO_TIMEOUT = 10000;
    public static int ENVIO_TIMEOUT = 20000;
    public static int LEITURA_CEP_TIMEOUT = 5000;

    public static boolean servidor_cadastrado = false;
    public static String URL_WEB_SERVICE = "http://minhacasa:8080/code-simatic";
    public static String URL_CEP_WEB_SERVICE = "http://viacep.com.br/ws/";
}

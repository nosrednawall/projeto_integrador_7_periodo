package org.iel.code_sismatic.util;

import java.io.IOException;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.json.Json;
import javax.json.JsonObject;

import org.iel.code_sismatic.model.enuns.ErrosValidacaoRestEnum;
import org.jboss.logging.Logger;
import org.json.JSONObject;

/**
 * Classe Util, possui vários métodos e constantes utilizadas em todo o código
 * 
 * @author anderson
 *
 */
public class Util {

	private Util() {
		throw new IllegalStateException("Utility class");
	}

	private static final Logger LOGGER = Logger.getLogger(Util.class);

	public static final String ICONE_USUARIO_INATIVO = "fa fa-circle";
	public static final String ICONE_USUARIO_ATIVO = "fa fa-circle-o";
	public static final String ICONE_MASCULINO = "fa fa-mars ♂";
	public static final String ICONE_FEMININO = "fa fa-venus ♀";
	public static final String LEFT_JOIN_FETCH_USUARIO = "LEFT JOIN FETCH u.roles " + "LEFT JOIN FETCH u.lojas "
			+ "LEFT JOIN FETCH u.enderecos " + "LEFT JOIN FETCH u.metodosPagamentos";

	public static final String LEFT_JOIN_FETCH_LOJA = "LEFT JOIN FETCH l.representante LEFT JOIN FETCH l.endereco LEFT JOIN FETCH l.lojistas";

	public static final String CODE_ERROR = "CODE_ERROR";

	/**
	 * imprime uma mensagem salva no messages_pt.properties com ou sem parametros
	 * 
	 * @param chave
	 * @param parametro1
	 * @param parametro2
	 * @return
	 */
	public static String getProperty(String chave, String parametro1, String parametro2) {
		String result = "TEXTO_NAO_ENCONTRADO";

		Properties propriedades = new Properties();
		try {
			propriedades.load(Util.class.getClassLoader().getResourceAsStream("messages_pt.properties"));

			// se não possuir parametros
			if (parametro1 == null && parametro2 == null) {
				result = propriedades.getProperty(chave);
			} else
			// se possui apenas o primeiro
			if (parametro2 == null) {
				result = MessageFormat.format(propriedades.getProperty(chave), parametro1);
			} else {
				// ambos
				result = MessageFormat.format(propriedades.getProperty(chave), parametro1, parametro2);
			}
		} catch (IOException e) {
			LOGGER.log(null, "[ERRO] Não foi possível carregar properties. Chave: \"" + chave + "\"" + e);
		}

		return result;
	}

	/**
	 * Retorna o endereco da página que será acessada
	 * @param chave
	 * @return
	 */
	public static String getEnderecoPagina(String chave) {
		String result = "/login.xhtml";

		Properties propriedades = new Properties();
		try {
			propriedades.load(Util.class.getClassLoader().getResourceAsStream("hierarquia_paginas.properties"));

			if (chave == null) {
				result = propriedades.getProperty(chave);
			}
		} catch (IOException e) {
			LOGGER.log(null, "[ERRO] Não foi possível carregar properties. Chave: \"" + chave + "\"" + e);
		}

		return result;

	}

	public static JsonObject criaObjetoBooleanoJson(String chave, boolean valor) {
		return Json.createObjectBuilder().add(chave, valor).build();
	}

	public static JsonObject criaObjetoErroJson(String chave, String mensagem) {
		return Json.createObjectBuilder().add(chave, mensagem).build();
	}

	public static String geraErroValidacoesJson(boolean emailExistente, boolean telefoneExistente,
			boolean cpfCnpjExistente) {

		JSONObject errosEncontrados = new JSONObject();
		List<ErrosValidacaoRestEnum> erros = new ArrayList<>();

		if (emailExistente)
			erros.add(ErrosValidacaoRestEnum.EMAIL_EXISTENTE);

		if (telefoneExistente)
			erros.add(ErrosValidacaoRestEnum.TELEFONE_EXISTENTE);

		if (cpfCnpjExistente)
			erros.add(ErrosValidacaoRestEnum.CPF_CNPJ_EXISTENTE);

		errosEncontrados.put("ERROS", erros);

		return errosEncontrados.toString();
	}
	
    // Verifica se a String é null ou vazia ou só tem espaços em branco
    public static boolean isNullOrBlank(String s) {
        return (s == null || s.trim().equals(""));
    }

    // Verifica se a String é null ou vazia
    // Pode ser utilizado como suporte em APIs menores que 9 do android onde não está disponivel o metódo de String isEmpty()
    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.equals(""));
    }
    
	/**
	 * Função adiciona o padrão a uma estring especifica
	 * @param data
	 * @return
	 */
	public static String adicionaPattermDataInicial(String data) {
		return data + " 00:00";
	}
	public static String adicionaPattermDataFinal(String data) {
		return data + " 23:59";
	}
	
	/**
	 * @param data
	 * @return
	 */
	public static LocalDateTime converteStringEmData(String data) {
		try {
			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");	
		LocalDateTime dataConvertida = LocalDateTime.parse(data, formatter);
		
		return dataConvertida;
		
		}catch (DateTimeParseException e) {
            // Throw invalid date message
            System.out.println("Exception was thrown");
            return LocalDateTime.now();
        }	
	}
	
	/**
	 * Retorna a data de hoje no formato americano
	 * @return
	 */
	public static String dataHojeFormatoAmericano() {
		LocalDate hoje = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return hoje.format(formatter);
	}
	
	/**
	 * Calculo simples de regra de 3
	 * @param valorCemPorcento
	 * @param valorXPorcento
	 * @return resultado
	 */
	public static float retornaPorcentagemRegradeTres(float valorCemPorcento, float valorXPorcento) {
		return (valorXPorcento*100)/valorCemPorcento;
	}
	
	/**simples soma de valores biginteger
	 * @param valor1
	 * @param valor2
	 * @return
	 */
	public static float somaBigIntegersRetornaFloat(BigInteger valor1, BigInteger valor2) {
		return valor1.add(valor2).floatValue();
	}

	public static BigInteger somaBigIntegers(BigInteger valor1, BigInteger valor2) {
		return valor1.add(valor2);
	}
	/**
	 * Retorna uma porcentagem formatada
	 * @param valor
	 * @return
	 */
	public static String retornaPorcentagemFormatada(float valor) {
		DecimalFormat df = new DecimalFormat("###.##");
		df.setRoundingMode(RoundingMode.UP);
		return df.format(valor);
	}
	
	/**
	 * arredonta os valores melhor ainda que o metodo acima
	 * @param media
	 * @return
	 */
	public static float arredondar(float media) {
		return (float) (Math.round(media * 100.0)/100.0);
	}
	
}
package org.iel.projeto_integrador.util;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;

public class ValidacoesUtil {

	private ValidacoesUtil() {
		throw new IllegalStateException("Utility class");
	}

	private static final Logger LOGGER = Logger.getLogger(ValidacoesUtil.class);

	/**
	 * valida cpf e cnpj
	 * 
	 * @param cpfCnpj
	 * @return
	 */
	public static boolean validaCpfCnpj(String cpfCnpj) {

		if (cpfCnpj.matches(RegexUtil.REGEX_CPFCNPJ)) {
			// verifica se é um cpf e valida ele
			if (cpfCnpj.length() == 11) {

				if (ValidarCpfCnpjUtil.isValidCPF(cpfCnpj)) {
					return true;
				} else {
					imprimeLog("CPF inválido");
				}
			} else {
				if (ValidarCpfCnpjUtil.isValidCNPJ(cpfCnpj)) {
					return true;
				} else {
					imprimeLog("CNPJ inválido");
				}
			}
		}
		return false;
	}

	/**
	 * Valida cep
	 * 
	 * @param cep
	 * @return boolean
	 */
	public static boolean validaCep(String cep) {

		return (formataDadosAceitandoApenasNumeros(cep).matches(RegexUtil.REGEX_CEP)) ? true : false;
	}

	/**
	 * Valida nome
	 * 
	 * @param nome
	 * @return boolean
	 */
	public static boolean validaNome(String nome) {
		boolean validado = true;

		if (nome != null) {

			if (nome.isEmpty()) {
				imprimeLog("O campo nome não pode ficar em branco!");
				validado = false;
			}

			Pattern padraoNumeros = Pattern.compile("[0-9]");
			Matcher farejador = padraoNumeros.matcher(nome);

			if (farejador.find()) {
				imprimeLog("O campo nome não deve conter números!");
				validado = false;
			}

			if (!nome.matches(RegexUtil.REGEX_NOME)) {
				imprimeLog("O Campo nome não é válido");
				validado = false;
			}

		} else {
			imprimeLog("O campo nome não pode ser nulo");
			validado = false;
		}
		return validado;
	}

	/**
	 * Método boolean que recebe um email e retorna se ele está validado ou não
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean validaEmail(String email) {

		boolean validado = true;

		// primeiro valida se não é null
		if (email != null) {

			// veirifica se não é uma string vazia
			if (email.isEmpty()) {
				imprimeLog("Email não pode ser vazio");
				validado = false;
			}

			/**
			 * Verifica se o email é válido, ex: possui \w@\w.\w
			 */
			if (!email.matches(RegexUtil.REGEX_EMAIL)) {
				imprimeLog(" Email informado não é um email válido");
				validado = false;
			}

			// caso seja nulo
		} else {
			imprimeLog("Email não pode ser nulo");
			validado = false;
		}

		return validado;
	}


	/**
	 * Método valida qualquer data sem hora
	 * 
	 * @param data
	 * @return
	 */
	public static boolean validaDataSemHora(Date data) {
		boolean validado = true;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// verifica se não é nulo
		if (data != null) {
			String dataTexto = dateFormat.format(data);

			// verifica se não está vazio
			if (dataTexto.isEmpty()) {
				imprimeLog("Data não pode ser vazio");
				validado = false;
			}

			/**
			 * tenta fazer um parse, caso esteja no formato errado lança excessão
			 */
			dateFormat.setLenient(false); // aqui o pulo do gato
			try {
				dateFormat.parse(dataTexto);
				// data válida
			} catch (ParseException ex) {
				imprimeLog("Data inválida " + ex);
				validado = false;
			}

		} else {
			imprimeLog("Data não pode ser nulo");
			validado = false;
		}
		return validado;
	}

	/**
	 * Método que remove os acentos de uma string
	 * http://www.guj.com.br/t/expressao-regular-alfanumerico-espaco-acentuacao/108978/8
	 * 
	 * @param acentuada
	 * @return
	 */
	public static String removerAcentos(String acentuada) {
		CharSequence cs = new StringBuilder(acentuada);
		return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	/**
	 * Método converte uma data de String para o formato Date
	 * 
	 * @param data
	 * @return
	 */
	public static Date getDataInDate(String data) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataParseada = new Date();
		try {
			dataParseada = dateFormat.parse(data);
		} catch (ParseException e) {
			imprimeLog("Data Inválida");
		}
		return dataParseada;
	}

	public static boolean validaTelefoneComMascara(String telefone) {
		return telefone.matches(RegexUtil.REGEX_TELEFONE);
	}

	public static boolean validaTelefoneSemMascara(String telefone) {
		return telefone.matches(RegexUtil.REGEX_TELEFONE_SEM_MASCARA);
	}

	public static boolean validaSenha(String senha) {
		return senha.matches(RegexUtil.REGEX_SENHA);
	}

	public static boolean validaNumero(String numero) {
		return numero.matches(RegexUtil.REGEX_NUMERO_CASA);
	}

	public static boolean validaBairro(String bairro) {
		return bairro.matches(RegexUtil.REGEX_BAIRRO);
	}

	public static boolean validaMunicipio(String municipio) {
		return municipio.matches(RegexUtil.REGEX_MUNICIPIO);
	}

	public static boolean validaApelido(String apelido) {
		return apelido.matches(RegexUtil.REGEX_APELIDO);
	}

	public static boolean validaLogradouro(String logradouro) {
		return logradouro.matches(RegexUtil.REGEX_LOGRADOURO);
	}

	public static String formataDadosAceitandoApenasNumeros(String dado) {
		return dado.replaceAll(RegexUtil.REGEX_ACEITA_APENAS_NUMEROS, "");
	}

	public static String removendoEspacosEmBranco(String texto) {
		return texto.replaceAll("\\s", "");
	}


	public static String removeMascarasTelefone(String tel) {
		tel = ValidacoesUtil.formataDadosAceitandoApenasNumeros(tel);
		tel = ValidacoesUtil.removendoEspacosEmBranco(tel);
		return tel;
	}

	/**
	 * Método imprime no log a mensagem e os parâmetros informados
	 * 
	 * @param mensagem
	 * @param parametro1
	 * @param parametro2
	 */
	public static void imprimeLog(String mensagem) {
		LOGGER.log(null, "[ERRO] " + mensagem);
	}

}

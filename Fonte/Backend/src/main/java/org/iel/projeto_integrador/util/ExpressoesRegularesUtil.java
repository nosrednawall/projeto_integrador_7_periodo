package org.iel.projeto_integrador.util;

public class ExpressoesRegularesUtil {

	/**
	 * Regex para textos em geral
	 */
	public static final String VALIDA_TEXTO_COM_ACENTUACAO_E_NOMES_EM_GERAL = "^[A-ZÀÁÂÃÄÈÉÊËÎÏĨÌÍÒÓÔÕÖÙÚÛÜÇ][a-zàáâãäèéêëîïĩìíòóôõöùúûüç]+((( |-)(das?|dos?|de|e))?(( |-)([A-ZÀÁÂÃÄÈÉÊËÎÏĨÌÍÒÓÔÕÖÙÚÛÜÇ][a-zàáâãäèéêëîïĩìíòóôõöùúûüç]+)))*$";
	public static final String VALIDA_TEXTO_COM_ACENTUACAO_E_NOMES_EM_GERAL_COM_NUMEROS = "^[A-ZÀÁÂÃÄÈÉÊËÎÏĨÌÍÒÓÔÕÖÙÚÛÜÇ][a-zàáâãäèéêëîïĩìíòóôõöùúûüç]+((( |-)(das?|dos?|de|e))?(( |-)(([A-ZÀÁÂÃÄÈÉÊËÎÏĨÌÍÒÓÔÕÖÙÚÛÜÇ][a-zàáâãäèéêëîïĩìíòóôõöùúûüç]+)|([0-9])+|([A-Z]){1,2})))*$";
	
	
	/**
	 * Patterns para usuários
	 */
	public static final String REGEX_EMAIL = "(?=^.{5,60}$)^[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]";
	public static final String REGEX_CPFCNPJ = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";
	public static final String REGEX_CPF = "^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}";
	public static final String REGEX_TELEFONE = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$";
	public static final String REGEX_TELEFONE_SEM_MASCARA = "^[1-9]{2}9[0-9]{8}$";
	public static final String REGEX_SENHA = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d@#$%&*()_\\-+?!<>;\\/=]{6,50}$";
	public static final String REGEX_NOME = VALIDA_TEXTO_COM_ACENTUACAO_E_NOMES_EM_GERAL;
	public static final String REGEX_NOME_FORTE = "^(?![ ])(?!.*[ ]{3})((?:e|da|do|das|dos|de|d'|D'|la|las|el|los)\\s*?|(?:[A-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð'][^\\s]*\\s*?)(?!.*[ ]$))+$";


	/**
	 * Patters para endereço
	 */
	public static final String REGEX_CEP = "^\\d{8}$";
	public static final String REGEX_LOGRADOURO = VALIDA_TEXTO_COM_ACENTUACAO_E_NOMES_EM_GERAL_COM_NUMEROS;
	public static final String REGEX_NUMERO_CASA = "^[0-9]{1,6}";
	public static final String REGEX_BAIRRO = VALIDA_TEXTO_COM_ACENTUACAO_E_NOMES_EM_GERAL_COM_NUMEROS;
	public static final String REGEX_MUNICIPIO = VALIDA_TEXTO_COM_ACENTUACAO_E_NOMES_EM_GERAL;
	public static final String REGEX_APELIDO = VALIDA_TEXTO_COM_ACENTUACAO_E_NOMES_EM_GERAL_COM_NUMEROS;

	public static final String REGEX_ACEITA_APENAS_NUMEROS = "[^0-9]";
	
	
	/**
	 * Patters para cpf e cnpj
	 */
	
	public static final String REGEX_BLACKLIST_CPF = "(?!(\\d)\\1{10})\\d{11}";
	public static final String REGEX_BLACKLIST_CNPJ = "(?!(\\d)\\1{13})\\d{14}";
	
	
}

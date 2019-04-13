package org.iel.codesimatic.util;

import org.iel.codesimatic.util.RegexUtil;
import org.iel.codesimatic.util.ValidarCpfCnpjUtil;

/**
 * Classe que contém todas as validações
 */
public class ValidacoesUtil {

    public static boolean validaSenha(String senha){



        return true;
    }

    public static boolean validaEmail(String email){
        return true;
    }

    public static boolean validaUsuarioAntesdeInstanciar() { return true; }

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
//                    imprimeLog("CPF inválido");
                }
            } else {
                if (ValidarCpfCnpjUtil.isValidCNPJ(cpfCnpj)) {
                    return true;
                } else {
//                    imprimeLog("CNPJ inválido");
                }
            }
        }
        return false;
    }
}

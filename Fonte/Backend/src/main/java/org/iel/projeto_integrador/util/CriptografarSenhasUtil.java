package  org.iel.projeto_integrador.util;

import java.security.MessageDigest;
import java.util.Base64;


public class CriptografarSenhasUtil {
	
    public static String generate(String senhaTexto) {

        try {
            byte[] digest = MessageDigest.getInstance("sha-256").digest(senhaTexto.getBytes());
            return Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

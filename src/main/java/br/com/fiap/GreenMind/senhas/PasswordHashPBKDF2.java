package br.com.fiap.GreenMind.senhas;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

//Gera um hash usando o algoritmo PBKDF2WithHmacSHA512
public class PasswordHashPBKDF2 {

    //Parâmetros: tamanho do salt, n. iterações, comprimento da chave (bits)
    private static final int SALT = 16;
    private static final int INTERACTIONS = 65536;
    private static final int KEY = 512;

    //Gera um salt seguro
    public static byte[] generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] newSalt = new byte[SALT];
        random.nextBytes(newSalt);
        return newSalt;
    }

    //Método cria o hash
    public static String hashPassword(String senha, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(senha.toCharArray(), salt, INTERACTIONS, KEY);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }

    //Método para verificar a senha
    public static boolean verificarSenha(String senha, String senhaHash, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String newHash  = hashPassword(senha, salt);
        return newHash.equals(senhaHash);
    }

}
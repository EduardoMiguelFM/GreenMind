package br.com.fiap.GreenMind.senhas;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class TesteBCryptHash {
    public static void main(String[] args) {
        String senhaOriginal = "Eduarda1234567";

        // Gera o hash da senha
        String senhaHash = BCrypt.hashpw(senhaOriginal, BCrypt.gensalt(10));
        System.out.println("Hash da Senha Gerado: " + senhaHash);

        // Verifica a senha usando o hash gerado
        boolean senhaValida = BCrypt.checkpw(senhaOriginal, senhaHash);
        System.out.println("A senha é válida: " + senhaValida);
    }
}

package br.com.fiap.GreenMind.senhas;

public class TesteBCrypt {
    public static void main(String[] args) {
        String senha = "minhaSenha123";
        String senhaHash = PasswordHash.hashPassoword(senha);
        System.out.println("Senha criptografada: " + senhaHash);

        // Verificação com a senha correta
        boolean senhaValida = PasswordHash.verificaSenha("minhaSenha123", senhaHash);
        System.out.println("A senha é válida: " + senhaValida); // Deve imprimir true
    }
}
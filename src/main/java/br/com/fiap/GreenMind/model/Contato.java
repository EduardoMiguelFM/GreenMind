package br.com.fiap.GreenMind.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Contato {
    private Long idContato;
    private String nomeContato;
    private String emailContato;
    private String telefoneContato;
    private String mensagem;
    private Date dataEnvio; // Incluído campo dataEnvio

    public Contato(Long idContato, String nomeContato, String emailContato, String telefoneContato, String mensagem, Date dataEnvio) {
        this.idContato = idContato;
        this.nomeContato = nomeContato;
        this.emailContato = emailContato;
        this.telefoneContato = telefoneContato;
        this.mensagem = mensagem;
        this.dataEnvio = dataEnvio;
    }

    public Contato() {}
}

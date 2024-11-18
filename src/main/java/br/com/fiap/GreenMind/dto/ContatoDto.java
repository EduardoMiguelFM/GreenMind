package br.com.fiap.GreenMind.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ContatoDto {
    private String nomeContato;
    private String emailContato;
    private String telefoneContato;
    private String mensagem;
}
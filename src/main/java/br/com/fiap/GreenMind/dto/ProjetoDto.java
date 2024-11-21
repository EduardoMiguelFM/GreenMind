package br.com.fiap.GreenMind.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjetoDto {
    private String nomeProj;
    private String descricao;
    private String detalhes;
    private String imagemUrl;
    private String categoriaId;
    private String nomeAutor;
    private String emailAutor;
}
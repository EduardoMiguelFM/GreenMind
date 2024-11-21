package br.com.fiap.GreenMind.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Projeto {
    private Long idProj;
    private String nomeProj;
    private String descricao;
    private String detalhes;
    private String imagemUrl;
    private String categoriaId;
    private String nomeAutor;
    private String emailAutor;
    private Date dataCriacao;

    public Projeto(Long idProj, String nomeProj, String descricao, String detalhes, String imagemUrl, String categoriaId, String nomeAutor, String emailAutor, Date dataCriacao) {
        this.idProj = idProj;
        this.nomeProj = nomeProj;
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.imagemUrl = imagemUrl;
        this.categoriaId = categoriaId;
        this.nomeAutor = nomeAutor;
        this.emailAutor = emailAutor;
        this.dataCriacao = dataCriacao;
    }

    public Projeto() {}
}

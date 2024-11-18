package br.com.fiap.GreenMind.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categoria {
    private Long idCat;
    private String nomeCat;

    public Categoria(Long id, String nome) {
        this.idCat = id;
        this.nomeCat = nome;
    }

    public Categoria() {}
}

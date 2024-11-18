package br.com.fiap.GreenMind.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categoria {
    private Long id;
    private String nome;

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria() {}
}

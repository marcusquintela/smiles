package br.com.modelo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EntidadeModelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    private EntidadeModeloRelacionada entidadeModeloRelacionada;

    public EntidadeModelo() {
    }

    public EntidadeModelo(String descricao, EntidadeModeloRelacionada entidadeModeloRelacionada) {
        this.descricao = descricao;
        this.entidadeModeloRelacionada = entidadeModeloRelacionada;
    }
}

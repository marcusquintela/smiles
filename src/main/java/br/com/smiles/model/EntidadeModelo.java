package br.com.smiles.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class EntidadeModelo {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
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

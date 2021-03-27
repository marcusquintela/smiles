package br.com.smiles.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Usuario usuario;

    private Calendar dataInicio;
    private Calendar dataFim;
    private String local;


}

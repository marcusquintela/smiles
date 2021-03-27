package br.com.smiles.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Usuario usuario;

    private Calendar dataInicio;
    private Calendar dataFim;
    private String local;



}

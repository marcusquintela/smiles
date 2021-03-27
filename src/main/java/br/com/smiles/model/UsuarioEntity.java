package br.com.smiles.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigoSmiles;

    @OneToMany(mappedBy = "usuarioEntity")
    private List<WishListEntity> wishListEntity;
}

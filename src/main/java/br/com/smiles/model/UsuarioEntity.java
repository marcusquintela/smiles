package br.com.smiles.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class UsuarioEntity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String nome;
    private String codigoSmiles;

    @OneToMany(mappedBy = "usuarioEntity")
    private List<WishListEntity> wishListEntity;
}

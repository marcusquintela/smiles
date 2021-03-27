package br.com.smiles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class WishListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioEntity usuarioEntity;

    private LocalDate dataIda;
    private LocalDate dataVolta;
    private String localOrigem;
    private String localDestino;
    private BigDecimal cotacaoAtual;

    public WishListEntity(UsuarioEntity usuarioEntity, LocalDate dataIda, LocalDate dataVolta, String localOrigem, String localDestino, BigDecimal cotacaoAtual) {
        this.usuarioEntity = usuarioEntity;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.cotacaoAtual = cotacaoAtual;
    }
}

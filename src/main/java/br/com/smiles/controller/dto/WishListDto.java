package br.com.smiles.controller.dto;

import br.com.smiles.model.WishListEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "wish-list-dto")
public class WishListDto {

    private String descricao;
    private LocalDate dataIda;
    private LocalDate dataVolta;
    private String localOrigem;
    private String localDestino;
    private BigDecimal cotacaoAtual;

    public WishListDto(WishListEntity wishListEntity) {
        this.descricao = wishListEntity.getDescricao();
        this.dataIda = wishListEntity.getDataIda();
        this.dataVolta = wishListEntity.getDataVolta();
        this.localOrigem = wishListEntity.getLocalOrigem();
        this.localDestino = wishListEntity.getLocalDestino();
        this.cotacaoAtual = wishListEntity.getCotacaoAtual();
    }

    public static List<WishListDto> toList(List<WishListEntity> wishListEntities) {
        return wishListEntities.stream().map(WishListDto::convertModeloToObjetoRetornoDto).collect(Collectors.toList());
    }

    private static WishListDto convertModeloToObjetoRetornoDto(WishListEntity wishListEntity) {
        if (wishListEntity != null) {
            return new WishListDto(
                    wishListEntity.getDescricao(),
                    wishListEntity.getDataIda(),
                    wishListEntity.getDataVolta(),
                    wishListEntity.getLocalOrigem(),
                    wishListEntity.getLocalDestino(),
                    wishListEntity.getCotacaoAtual()
            );
        }
        return null;
    }


}

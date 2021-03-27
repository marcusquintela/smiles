package br.com.smiles.controller.form;

import br.com.smiles.model.UsuarioEntity;
import br.com.smiles.model.WishListEntity;
import br.com.smiles.repository.UsuarioRepository;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@XmlRootElement(name = "parametro")
public class CadastrarWishListForm {
    private String codigoSmiles;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String localOrigem;
    private String localDestino;

    public WishListEntity convertToEntidadeWishList(UsuarioRepository usuarioRepository, BigDecimal valorCotacao) {

        UsuarioEntity usuario = usuarioRepository.findByCodigoSmiles(codigoSmiles);
        WishListEntity wishListEntity = new WishListEntity(usuario,
                dataInicio, dataFim, localOrigem, localDestino, valorCotacao);

        return wishListEntity;
    }
}

package br.com.smiles.controller;

import br.com.smiles.controller.dto.ObjetoRetornoDto;
import br.com.smiles.controller.dto.WishListDto;
import br.com.smiles.controller.form.BuscarWishListForm;
import br.com.smiles.controller.form.CadastrarWishListForm;
import br.com.smiles.controller.form.ObjetoParametroForm;
import br.com.smiles.excecoes.ParametrosInvalidoException;
import br.com.smiles.model.WishListEntity;
import br.com.smiles.repository.*;
import br.com.smiles.util.UtilValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smiles")
public class Controller {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UtilValidador utilValidador;

    /***
     * Smiles inicio
     */

    @GetMapping("/buscar/wish-list")
    public List<WishListDto> buscarWishList(@RequestBody BuscarWishListForm parametro) throws ParametrosInvalidoException {
        UtilValidador.validaBusca(parametro);

        List<WishListEntity> wishLists = wishListRepository.findByUsuarioEntityCodigoSmiles(parametro.getCodigoSmiles());

        if(UtilValidador.buscaPorData(parametro))
            wishLists = wishListRepository.findByUsuarioEntityCodigoSmilesAndDataIdaBetween(parametro.getCodigoSmiles(), parametro.getDataInicio(), parametro.getDataFim());

        if(UtilValidador.isNotEmpty(parametro.getDescricaoWish())){
            Optional<WishListEntity> any = wishLists.stream().filter(wishListEntity -> wishListEntity.getDescricao().equals(parametro.getDescricaoWish())).findAny();
            return WishListDto.toList(Arrays.asList(any.get()));
        }

        return WishListDto.toList(wishLists);
    }

    @PostMapping("/cadastrar/wish-list")
    public ResponseEntity<WishListDto> cadastrarWishList(@RequestBody CadastrarWishListForm parametro, UriComponentsBuilder uriComponentsBuilder) throws ParametrosInvalidoException {

        WishListEntity wishListEntity = parametro.convertToEntidadeWishList(usuarioRepository, new BigDecimal("1100"));
        wishListRepository.save(wishListEntity);

        URI uri = uriComponentsBuilder.path("/smiles/buscar/wish-list/{id}").buildAndExpand(wishListEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new WishListDto(wishListEntity));
    }

}

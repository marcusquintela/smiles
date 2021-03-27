package br.com.smiles.controller;

import br.com.smiles.controller.dto.ObjetoRetornoDto;
import br.com.smiles.controller.dto.WishListDto;
import br.com.smiles.controller.form.BuscarWishListForm;
import br.com.smiles.controller.form.CadastrarWishListForm;
import br.com.smiles.controller.form.ObjetoParametroForm;
import br.com.smiles.excecoes.ParametrosInvalidoException;
import br.com.smiles.model.EntidadeModelo;
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
    ModeloRepository modeloRepository;

    @Autowired
    ModeloRelacionadaRepository modeloRelacionadaRepository;

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UtilValidador utilValidador;

    @GetMapping
    public List<ObjetoRetornoDto> metodoModelo_getAll() throws ParametrosInvalidoException {
        List<EntidadeModelo> modelos = modeloRepository.findAll();
        return ObjetoRetornoDto.toList(modelos);
    }

    @GetMapping("/buscar/{id}")
    public ObjetoRetornoDto metodoModelo_ParametroId(Long id) throws ParametrosInvalidoException {
        utilValidador.validarParametroNulo(id);
        Optional<EntidadeModelo> modelo = modeloRepository.findById(id);
        return new ObjetoRetornoDto(modelo.get());
    }

    @GetMapping("/buscar/descricao")
    public List<ObjetoRetornoDto> metodoModelo_ParametroString(String descricao) throws ParametrosInvalidoException {
        utilValidador.validarParametroNulo(descricao);
        utilValidador.validarParametroVazio(descricao);
        List<EntidadeModelo> modelos = modeloRepository.findByDescricao(descricao);
        return ObjetoRetornoDto.toList(modelos);
    }

    @GetMapping("/buscar/objeto")
    public ObjetoRetornoDto metodoModelo_ParametroObjeto(@RequestBody ObjetoParametroForm parametro) throws ParametrosInvalidoException {
        ObjetoRetornoDto objetoRetornoDto = new ObjetoRetornoDto();
        objetoRetornoDto.setValorRetorno(parametro.getValorParametro() + " - Volta!");
        return objetoRetornoDto;
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<ObjetoRetornoDto> metodoModelo_cadastrar(@RequestBody ObjetoParametroForm parametro, UriComponentsBuilder uriComponentsBuilder) throws ParametrosInvalidoException {

        EntidadeModelo modelo = parametro.convertToEntidadeModelo(modeloRelacionadaRepository);
        modeloRepository.save(modelo);

        URI uri = uriComponentsBuilder.path("/metodo-modelo/modelo/{id}").buildAndExpand(modelo.getId()).toUri();
        return ResponseEntity.created(uri).body(new ObjetoRetornoDto(modelo));
    }
//////// FIM DO MODELO


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

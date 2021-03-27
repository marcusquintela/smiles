package br.com.modelo.controller;

import br.com.modelo.controller.dto.ObjetoRetornoDto;
import br.com.modelo.controller.form.ObjetoParametroForm;
import br.com.modelo.excecoes.ParametrosInvalidoException;
import br.com.modelo.model.EntidadeModelo;
import br.com.modelo.repository.ModeloRelacionadaRepository;
import br.com.modelo.repository.ModeloRepository;
import br.com.modelo.util.UtilValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    ModeloRepository modeloRepository;

    @Autowired
    ModeloRelacionadaRepository modeloRelacionadaRepository;

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
}

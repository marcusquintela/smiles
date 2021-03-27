package br.com.smiles.controller.form;

import br.com.smiles.model.EntidadeModelo;
import br.com.smiles.model.EntidadeModeloRelacionada;
import br.com.smiles.repository.ModeloRelacionadaRepository;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Optional;

@Data
@XmlRootElement(name = "parametro")
public class ObjetoParametroForm {
    private String valorParametro;
    private Long codigoRelacionamento;

    public EntidadeModelo convertToEntidadeModelo(ModeloRelacionadaRepository modeloRelacionadaRepository) {
        Optional<EntidadeModeloRelacionada> entidadeModeloRelacionada = modeloRelacionadaRepository.findById(codigoRelacionamento);
        return new EntidadeModelo(valorParametro, entidadeModeloRelacionada.get());
    }
}

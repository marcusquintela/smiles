package br.com.modelo.controller.form;

import br.com.modelo.model.EntidadeModelo;
import br.com.modelo.model.EntidadeModeloRelacionada;
import br.com.modelo.repository.ModeloRelacionadaRepository;
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

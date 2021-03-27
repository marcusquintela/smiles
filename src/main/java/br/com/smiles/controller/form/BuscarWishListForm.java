package br.com.smiles.controller.form;

import br.com.smiles.model.EntidadeModelo;
import br.com.smiles.model.EntidadeModeloRelacionada;
import br.com.smiles.repository.ModeloRelacionadaRepository;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Optional;

@Data
@XmlRootElement(name = "parametro")
public class BuscarWishListForm {
    private String codigoSmiles;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}

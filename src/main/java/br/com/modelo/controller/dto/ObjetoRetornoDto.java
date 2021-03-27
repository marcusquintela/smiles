package br.com.modelo.controller.dto;

import br.com.modelo.model.EntidadeModelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "retorno")
public class ObjetoRetornoDto {

    public ObjetoRetornoDto(EntidadeModelo modelo) {
        setValorRetorno(modelo.getDescricao());
    }

    private String valorRetorno;

    public static List<ObjetoRetornoDto> toList(List<EntidadeModelo> modelos) {
        return modelos.stream().map(ObjetoRetornoDto::convertModeloToObjetoRetornoDto).collect(Collectors.toList());
    }

    private static ObjetoRetornoDto convertModeloToObjetoRetornoDto(EntidadeModelo entidadeModelo) {
        return new ObjetoRetornoDto((entidadeModelo == null ? null : entidadeModelo.getDescricao()));
    }
}

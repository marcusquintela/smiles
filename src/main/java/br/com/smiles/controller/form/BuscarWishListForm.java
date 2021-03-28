package br.com.smiles.controller.form;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Data
@XmlRootElement(name = "parametro")
public class BuscarWishListForm {
    private String descricaoWish;
    private String codigoSmiles;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}

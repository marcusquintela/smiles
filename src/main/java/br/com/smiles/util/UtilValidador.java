package br.com.smiles.util;

import br.com.smiles.controller.form.BuscarWishListForm;
import br.com.smiles.excecoes.ParametrosInvalidoException;
import org.springframework.stereotype.Component;

@Component
public class UtilValidador {

    public static void validaBusca(BuscarWishListForm parametro) throws ParametrosInvalidoException {
        validarParametroNulo(parametro);
        validarParametroNulo(parametro.getCodigoSmiles());
        validarParametroVazio(parametro.getCodigoSmiles());
    }

    public static void validarParametroNulo(Object parametro) throws ParametrosInvalidoException {
        if (parametro == null)
            throw new ParametrosInvalidoException("Paramentro não pode ser nulo.");
    }

    public static void validarParametroVazio(String parametro) throws ParametrosInvalidoException {
        if (parametro.isEmpty())
            throw new ParametrosInvalidoException("Paramentro não pode ser vazio.");
    }

    public static boolean buscaPorData(BuscarWishListForm parametro) {
        return (parametro.getDataFim() != null || parametro.getDataFim() != null);
    }

    public static boolean isNotEmpty(String string) {
        return (string != null && !string.isEmpty());
    }
}

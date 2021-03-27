package br.com.smiles.util;

import br.com.smiles.excecoes.ParametrosInvalidoException;
import org.springframework.stereotype.Component;

@Component
public class UtilValidador {
    public void validarParametroNulo(Object parametro) throws ParametrosInvalidoException {
        if (parametro == null)
            throw new ParametrosInvalidoException("Paramentro não pode ser nulo.");
    }

    public void validarParametroVazio(String parametro) throws ParametrosInvalidoException {
        if (parametro.isEmpty())
            throw new ParametrosInvalidoException("Paramentro não pode ser vazio.");
    }
}

package br.com.smiles.repository;

import br.com.smiles.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByCodigoSmiles(String codigoSmiles);
}

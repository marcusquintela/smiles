package br.com.smiles.repository;

import br.com.smiles.model.EntidadeModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeloRepository extends JpaRepository<EntidadeModelo, Long> {
    List<EntidadeModelo> findByDescricao(String descricao);
}

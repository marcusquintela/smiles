package br.com.smiles.repository;

import br.com.smiles.model.EntidadeModeloRelacionada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRelacionadaRepository extends JpaRepository<EntidadeModeloRelacionada, Long> {
}

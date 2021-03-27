package br.com.modelo.repository;

import br.com.modelo.model.EntidadeModeloRelacionada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeloRelacionadaRepository extends JpaRepository<EntidadeModeloRelacionada, Long> {
}

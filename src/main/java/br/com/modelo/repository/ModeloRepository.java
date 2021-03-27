package br.com.modelo.repository;

import br.com.modelo.model.EntidadeModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModeloRepository extends JpaRepository<EntidadeModelo, Long> {
    List<EntidadeModelo> findByDescricao(String descricao);
}

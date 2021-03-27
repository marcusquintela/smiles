package br.com.smiles.repository;

import br.com.smiles.model.WishListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface WishListRepository extends JpaRepository<WishListEntity, Long> {

    List<WishListEntity> findByUsuarioEntityCodigoSmiles(String codigoSmiles);

    List<WishListEntity> findByUsuarioEntityCodigoSmilesAndDataIdaBetween(String codigoSmiles, LocalDate dataInicio, LocalDate dataFim);
}

package br.com.smiles.repository;

import br.com.smiles.controller.form.BuscarWishListForm;
import br.com.smiles.model.UsuarioEntity;
import br.com.smiles.model.WishListEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class WishListRepository {

    @Autowired
    private EntityManager entityManager;

    public List<WishListEntity> buscaWishList(BuscarWishListForm parametro) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WishListEntity> query = criteriaBuilder.createQuery(WishListEntity.class);

        Root<WishListEntity> wishListRoot = query.from(WishListEntity.class);
        Join<WishListEntity, UsuarioEntity> wishListUsuarioJoin = wishListRoot.join("usuarioEntity");

        Predicate where = criteriaBuilder.equal(wishListUsuarioJoin.get("codigoSmiles"), parametro.getCodigoSmiles());
        if (parametro.getDataInicio() != null && parametro.getDataFim() != null) {
            Predicate date = criteriaBuilder.between(wishListRoot.get("dataIda"), parametro.getDataInicio(), parametro.getDataFim());
            where = criteriaBuilder.and(date);
        }

        query.where(where);
        return entityManager.createQuery(query).getResultList();
    }

    public void save(WishListEntity wishListEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(wishListEntity);
        entityManager.getTransaction().commit();
    }
}

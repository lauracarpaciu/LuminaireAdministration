package com.laura.carpaciu.dao.interfaces;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laura.carpaciu.entity.luminaire.Piece;

import javax.persistence.EntityManager;
import java.util.Optional;

@AllArgsConstructor
@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class PieceRepositoryImpl implements PieceRepository {
	
	 private final EntityManager entityManager;

	public PieceRepositoryImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
    public void createPiece(Piece part) {
        entityManager.persist(part);
    }

    @Override
    public Optional<Piece> findPieceByName(String partName){

        String jpql = "SELECT p FROM Part p WHERE p.partName =: partName";

        return entityManager.createQuery(jpql, Piece.class)
                            .setParameter("partName", partName)
                            .getResultStream()
                            .findFirst();
    }


    @Override
    public Optional<Piece> findPieceByPartNumber(String partNumber){

        String jpql = "SELECT p FROM Part p WHERE p.partNumber =: partNumber";

        return entityManager.createQuery(jpql, Piece.class)
                            .setParameter("partNumber", partNumber)
                            .getResultStream()
                            .findFirst();

    }


    @Override
    public int updatePieceCount(int increment, String partNumber){

        String jpql = "UPDATE Part p SET p.count = p.count + :increment WHERE p.partNumber =: partNumber";

        return entityManager.createQuery(jpql)
                            .setParameter("increment", increment)
                            .setParameter("partNumber", partNumber)
                            .executeUpdate();
    }

    @Override
    public int updatePieceCountAndPrice(int increment, double price, String partNumber){

        String jpql = "UPDATE Part p SET p.count = p.count + :increment, p.price =: price WHERE p.partNumber =: partNumber";

        return entityManager.createQuery(jpql)
                            .setParameter("increment", increment)
                            .setParameter("partNumber", partNumber)
                            .setParameter("price", price)
                            .executeUpdate();

    }


    @Override
    public int decreasePieceCount(int decrement, String partNumber){

        String jpql = "UPDATE Part p SET p.count = p.count - :decrement WHERE p.partNumber =: partNumber";

        return entityManager.createQuery(jpql)
                            .setParameter("decrement", decrement)
                            .setParameter("partNumber", partNumber)
                            .executeUpdate();
    }

	
}

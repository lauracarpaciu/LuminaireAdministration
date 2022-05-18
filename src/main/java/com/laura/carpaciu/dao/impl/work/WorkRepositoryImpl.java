package com.laura.carpaciu.dao.impl.work;

import java.util.List;
import java.util.Optional;

import com.laura.carpaciu.dao.interfaces.WorkRepository;
import com.laura.carpaciu.entity.work.Work;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public class WorkRepositoryImpl implements  WorkRepository {
	@PersistenceContext
	private final EntityManager entityManager;
	
	
	public WorkRepositoryImpl(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}

	@Override
    public void create(Work work){
        entityManager.persist(work);
    }


    @Override
    public List<Work> findByName(String workDescription){

        String jpql = "SELECT l FROM Labor l WHERE l.laborDescription  LIKE :laborDescription";

        return entityManager.createQuery(jpql, Work.class)
                            .setParameter("laborDescription", "%"   +workDescription + "%" )
                            .getResultList();

    }


    @Override
    public int updateWorkTimeAndDescription(double timedwork, String workDescription, Long id){

        String jpql = "UPDATE Work l SET l.timedWork =: timedWork, l.workDescription =:workDescription WHERE l.id =: id" ;


        return entityManager.createQuery(jpql)
                            .setParameter("timedWork", timedwork)
                            .setParameter("laborDescription", workDescription)
                            .setParameter("id", id)
                            .executeUpdate();

    }

    @Override
    public List<Work> findAllWorks(){

        String jpql = "SELECT l FROM Labor l";

        return entityManager.createQuery(jpql, Work.class)
                            .getResultList();

    }


    @Override
    public Optional<Work> findById(int id){

        String jpql = "SELECT l FROM Labor l WHERE l.id =: id";

        return entityManager.createQuery(jpql, Work.class)
                            .setParameter("id", id)
                            .getResultStream()
                            .findFirst();
    }


    @Override
    public int deleteWork(int id){

        String jpql = "DELETE FROM Labor l WHERE l.id =: id";

        return entityManager.createQuery(jpql)
                            .setParameter("id", id)
                            .executeUpdate();

    }

}
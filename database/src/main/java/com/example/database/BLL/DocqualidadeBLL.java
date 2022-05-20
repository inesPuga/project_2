package com.example.database.BLL;

import com.example.database.DAL.Docqualidade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class DocqualidadeBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert doc qualidade on data base
    public static void create(Docqualidade dq) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(dq);
        em.getTransaction().commit();
    }

    //read doc qualidade
    public static List<Docqualidade> readAll() {
        List<Docqualidade> listaDq = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Docqualidade.findAll");
        List<Object> result = q1.getResultList();

        for(Object dq : result){
            listaDq.add((Docqualidade)dq);
        }
        return listaDq;
    }

    //search doc qualidade by id
    public static Docqualidade readByCodDocQld(int idCodQld) {
        Docqualidade dq = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Docqualidade.findByCodcodqld");
        q1.setParameter("coddocqualidade", idCodQld);
        Object obj = q1.getSingleResult();

        if(obj != null){
            dq = ((Docqualidade)obj);
        }
        else {
            return null;
        }
        return dq;
    }

    //search doc qualidade by cod rq
    public static Docqualidade readByCodRQ(int codrq) {
        Docqualidade dq = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Docqualidade.findByCodrq");
        q1.setParameter("codrq", codrq);
        Object obj = q1.getSingleResult();

        if(obj != null){
            dq = ((Docqualidade)obj);
        }
        else {
            return null;
        }
        return dq;
    }

    //update doc qualidade
    public static void update(Docqualidade dq) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(dq);
        em.getTransaction().commit();
    }

    //delete doc qualidade
    public static void delete(Docqualidade dq) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(dq);
        em.getTransaction().commit();
    }
}

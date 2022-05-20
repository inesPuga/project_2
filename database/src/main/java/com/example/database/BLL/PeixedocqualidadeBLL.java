package com.example.database.BLL;

import com.example.database.DAL.Peixedocqualidade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PeixedocqualidadeBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadocompra on data base
    public static void create(Peixedocqualidade pd) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(pd);
        em.getTransaction().commit();
    }

    public static List<Peixedocqualidade> readAll(){
        List<Peixedocqualidade> listaPeixeDocQld = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixedocqualidade.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaPeixeDocQld.add((Peixedocqualidade) o);
        }
        return listaPeixeDocQld;
    }

    public static Peixedocqualidade readUserBypk(int codpeixe, int coddocqualidade) {
        Peixedocqualidade pd = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixedocqualidade.findByPk");
        q1.setParameter("codpeixe", codpeixe);
        q1.setParameter("coddocqualidade", coddocqualidade);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            pd = ((Peixedocqualidade)obj);
        }
        else {
            return null;
        }
        return pd;
    }

    public static Peixedocqualidade readByCoddocqld(int coddocqualidade) {
        Peixedocqualidade pd = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixedocqualidade.findByCoddocqld");
        q1.setParameter("coddocqualidade", coddocqualidade);
        Object obj = q1.getSingleResult();

        if(obj != null){
            pd = ((Peixedocqualidade)obj);
        }
        else {
            return null;
        }
        return pd;
    }

    public static Peixedocqualidade readByCodcompra(int codpeixe) {
        Peixedocqualidade pd = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixedocqualidade.findByCodpeixe");
        q1.setParameter("codpeixe", codpeixe);
        Object obj = q1.getSingleResult();

        if(obj != null){
            pd = ((Peixedocqualidade)obj);
        }
        else {
            return null;
        }
        return pd;
    }

    public static void update(Peixedocqualidade pd) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(pd);
        em.getTransaction().commit();
    }

    public static void delete(Peixedocqualidade pd) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(pd);
        em.getTransaction().commit();
    }
}

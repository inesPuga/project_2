package com.example.database.BLL;

import com.example.database.DAL.Peixe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PeixeBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    public static void create(Peixe p) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public static List<Peixe> readAll(){
        List<Peixe> listaPeixe = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixe.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaPeixe.add((Peixe) o);
        }
        return listaPeixe;
    }

    public static Peixe readByCodpeixe(int codpeixe) {
        Peixe p = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixe.findByCodpeixe");
        q1.setParameter("codpeixe", codpeixe);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            p = ((Peixe)obj);
        }
        else {
            return null;
        }
        return p;
    }

    public static Peixe readByNome(String nome) {
        Peixe p = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixe.findByNomepeixe");
        q1.setParameter("nome", nome);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            p = ((Peixe)obj);
        }
        else {
            return null;
        }
        return p;
    }

    public static void update(Peixe p) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }

    public static void delete(Peixe p) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }
}

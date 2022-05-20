package com.example.database.BLL;

import com.example.database.DAL.Cpostal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CPostalBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert cpostal on data base
    public static void create(Cpostal cp) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(cp);
        em.getTransaction().commit();
    }

    public static List<Cpostal> readAll(){
        List<Cpostal> listaCpostal = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Cpostal.findByCodPostal");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaCpostal.add((Cpostal)o);
        }
        return listaCpostal;
    }

    //search cpostal by id(=codpostal)
    public static Cpostal readUserById(int idcp) {
        Cpostal cp = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Cpostal.findByCodPostal");
        q1.setParameter("codpostal", idcp);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            cp = ((Cpostal)obj);
        }
        else {
            return null;
        }
        return cp;
    }

    //update cpostal
    public static void update(Cpostal cp) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(cp);
        em.getTransaction().commit();
    }

    //delete cpostal
    public static void delete(Cpostal cp) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(cp);
        em.getTransaction().commit();
    }

}

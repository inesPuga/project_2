package com.example.database.BLL;

import com.example.database.DAL.Estadoc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EstadocBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadoc on data base
    public static void create(Estadoc ec) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(ec);
        em.getTransaction().commit();
    }

    public static List<Estadoc> readAll() {
        List<Estadoc> listEc = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoc.findAll");
        List<Object> result = q1.getResultList();

        for(Object e : result){
            listEc.add((Estadoc)e);
        }
        return listEc;
    }

    //search encomenda by id
    public static Estadoc readById(int idc) {
        Estadoc ec = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoc.findByIdc");
        q1.setParameter("idc", idc);
        Object obj = q1.getSingleResult();

        if(obj != null){
            ec = ((Estadoc)obj);
        }
        else {
            return null;
        }
        return ec;
    }

    public static void update(Estadoc ec) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(ec);
        em.getTransaction().commit();
    }

    public static void delete(Estadoc ec) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(ec);
        em.getTransaction().commit();
    }
}

package com.example.database.BLL;

import com.example.database.DAL.Estadoe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EstadoeBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadoc on data base
    public static void create(Estadoe ec) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(ec);
        em.getTransaction().commit();
    }

    public static List<Estadoe> readAll() {
        List<Estadoe> listEe = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoe.findAll");
        List<Object> result = q1.getResultList();

        for(Object e : result){
            listEe.add((Estadoe)e);
        }
        return listEe;
    }

    //search encomenda by id
    public static Estadoe readById(int ide) {
        Estadoe ee = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoe.findByIde");
        q1.setParameter("ide", ide);
        Object obj = q1.getSingleResult();

        if(obj != null){
            ee = ((Estadoe)obj);
        }
        else {
            return null;
        }
        return ee;
    }

    public static void update(Estadoe ee) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(ee);
        em.getTransaction().commit();
    }

    public static void delete(Estadoe ee) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(ee);
        em.getTransaction().commit();
    }
}

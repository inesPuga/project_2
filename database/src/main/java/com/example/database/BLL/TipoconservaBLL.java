package com.example.database.BLL;

import com.example.database.DAL.Tipoconserva;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TipoconservaBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    public static void create(Tipoconserva tc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(tc);
        em.getTransaction().commit();
    }

    public static List<Tipoconserva> readAll(){
        List<Tipoconserva> listaTc = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Tipoconserva.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaTc.add((Tipoconserva) o);
        }
        return listaTc;
    }

    public static Tipoconserva readByCodpeixe(int codtipoconserva) {
        Tipoconserva tc = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Tipoconserva.findByIdtipoconserva");
        q1.setParameter("codtipoconserva", codtipoconserva);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            tc = ((Tipoconserva)obj);
        }
        else {
            return null;
        }
        return tc;
    }

    public static Tipoconserva readByNome(int codpeixe) {
        Tipoconserva tc = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Tipoconserva.findByCodpeixe");
        q1.setParameter("codpeixe", codpeixe);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            tc = ((Tipoconserva)obj);
        }
        else {
            return null;
        }
        return tc;
    }

    public static void update(Tipoconserva tc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(tc);
        em.getTransaction().commit();
    }

    public static void delete(Tipoconserva tc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(tc);
        em.getTransaction().commit();
    }
}

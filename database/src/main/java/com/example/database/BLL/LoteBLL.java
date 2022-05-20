package com.example.database.BLL;

import com.example.database.DAL.Lote;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LoteBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert cpostal on data base
    public static void create(Lote l) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
    }

    public static List<Lote> readAll(){
        List<Lote> listaLote = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Lote.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaLote.add((Lote) o);
        }
        return listaLote;
    }

    //search comprapeixe by codcompra
    public static Lote readByCodlote(int codlote) {
        Lote l = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Lote.findByCodlote");
        q1.setParameter("codlote", codlote);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            l = ((Lote)obj);
        }
        else {
            return null;
        }
        return l;
    }

    //search comprapeixe by codgc)
    public static Lote readByCodtpc(int codtpc) {
        Lote l = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Lote.findByCodtpconserva");
        q1.setParameter("codtipoconserva", codtpc);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            l = ((Lote)obj);
        }
        else {
            return null;
        }
        return l;
    }

    public static void update(Lote l) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(l);
        em.getTransaction().commit();
    }

    //delete cpostal
    public static void delete(Lote l) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(l);
        em.getTransaction().commit();
    }
}

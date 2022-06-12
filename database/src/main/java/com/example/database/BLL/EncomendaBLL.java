package com.example.database.BLL;

import com.example.database.DAL.Encomenda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EncomendaBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert encomenda on data base
    public static void create(Encomenda e) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    //read encomendas
    public static List<Encomenda> readAll() {
        List<Encomenda> listaEnc = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Encomenda.findAll");
        List<Object> result = q1.getResultList();

        for(Object e : result){
            listaEnc.add((Encomenda)e);
        }
        return listaEnc;
    }

    //search encomenda by id
    public static Encomenda readById(int codEnc) {
        Encomenda e = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Encomenda.findByCodEnc");
        q1.setParameter("codencomenda", codEnc);
        Object obj = q1.getSingleResult();

        if(obj != null){
            e = ((Encomenda)obj);
        }
        else {
            return null;
        }
        return e;
    }

    public static Double sumOrder() {
        Double i = Double.valueOf(0);
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Encomenda.sumprice");
        Object obj = q1.getSingleResult();

        if(obj != null){
            i = Double.valueOf(((Double) obj).intValue());
        }
        else {
            return Double.valueOf(0);
        }
        return i;
    }

    //search encomenda by id cliente
    public static Encomenda readByIdcliente(int idcliente) {
        Encomenda e = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Encomenda.findByCodcli");
        q1.setParameter("codcliente", idcliente);
        Object obj = q1.getSingleResult();

        if(obj != null){
            e = ((Encomenda)obj);
        }
        else {
            return null;
        }
        return e;
    }

    public static void update(Encomenda e) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }

    //delete client
    public static void delete(Encomenda e) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
    }
}

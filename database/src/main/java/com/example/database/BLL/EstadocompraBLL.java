package com.example.database.BLL;

import com.example.database.DAL.Estadocompra;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EstadocompraBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadocompra on data base
    public static void create(Estadocompra ec) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(ec);
        em.getTransaction().commit();
    }

    public static List<Estadocompra> readAll(){
        List<Estadocompra> listaEstadocompra = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadocompra.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaEstadocompra.add((Estadocompra) o);
        }
        return listaEstadocompra;
    }

    //search comprapeixe by codcompra
    public static Estadocompra readUserBypk(int codcompra, int idc) {
        Estadocompra ec = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadocompra.findByPk");
        q1.setParameter("codcompra", codcompra);
        q1.setParameter("idc", idc);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            ec = ((Estadocompra)obj);
        }
        else {
            return null;
        }
        return ec;
    }

    public static Estadocompra readByCodCompra(int codCodCompra) {
        Estadocompra ec = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadocompra.findByCodcompra");
        q1.setParameter("codcompra", codCodCompra);
        Object obj = q1.getSingleResult();

        if(obj != null){
            ec = ((Estadocompra)obj);
        }
        else {
            return null;
        }
        return ec;
    }

    public static Estadocompra readByIdc(int idc) {
        Estadocompra ec = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadocompra.findByIdc");
        q1.setParameter("idc", idc);
        Object obj = q1.getSingleResult();

        if(obj != null){
            ec = ((Estadocompra)obj);
        }
        else {
            return null;
        }
        return ec;
    }

    public static void update(Estadocompra ec) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(ec);
        em.getTransaction().commit();
    }

    //delete client
    public static void delete(Estadocompra ec) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(ec);
        em.getTransaction().commit();
    }

}

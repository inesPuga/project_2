package com.example.database.BLL;

import com.example.database.DAL.Estadoencomenda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EstadoencomendaBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadocompra on data base
    public static void create(Estadoencomenda ee) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(ee);
        em.getTransaction().commit();
    }

    public static List<Estadoencomenda> readAll(){
        List<Estadoencomenda> listaEstadoencomenda = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoencomenda.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaEstadoencomenda.add((Estadoencomenda) o);
        }
        return listaEstadoencomenda;
    }

    //search comprapeixe by codcompra
    public static Estadoencomenda readUserBypk(int codcompra, int idc) {
        Estadoencomenda ee = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoencomenda.findByPk");
        q1.setParameter("codencomenda", codcompra);
        q1.setParameter("ide", idc);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            ee = ((Estadoencomenda)obj);
        }
        else {
            return null;
        }
        return ee;
    }

    public static Estadoencomenda readByCodEncomenda(int codEncomenda) {
        Estadoencomenda ee = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoencomenda.findByCodencomenda");
        q1.setParameter("codencomenda", codEncomenda);
        Object obj = q1.getSingleResult();

        if(obj != null){
            ee = ((Estadoencomenda)obj);
        }
        else {
            return null;
        }
        return ee;
    }

    public static Estadoencomenda readByIdc(int ide) {
        Estadoencomenda ee = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Estadoencomenda.findByIde");
        q1.setParameter("ide", ide);
        Object obj = q1.getSingleResult();

        if(obj != null){
            ee = ((Estadoencomenda)obj);
        }
        else {
            return null;
        }
        return ee;
    }

    public static void update(Estadoencomenda ee) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(ee);
        em.getTransaction().commit();
    }

    public static void delete(Estadoencomenda ee) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(ee);
        em.getTransaction().commit();
    }
}

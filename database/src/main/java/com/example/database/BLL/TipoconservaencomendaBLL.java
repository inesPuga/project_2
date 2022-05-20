package com.example.database.BLL;

import com.example.database.DAL.Tipoconservaencomenda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TipoconservaencomendaBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadocompra on data base
    public static void create(Tipoconservaencomenda tce) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(tce);
        em.getTransaction().commit();
    }

    public static List<Tipoconservaencomenda> readAll(){
        List<Tipoconservaencomenda> listaTce = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Tipoconservaencomenda.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaTce.add((Tipoconservaencomenda) o);
        }
        return listaTce;
    }

    //search comprapeixe by codcompra
    public static Tipoconservaencomenda readUserBypk(int codtipoconserva, int codencomenda) {
        Tipoconservaencomenda tce = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Tipoconservaencomenda.findByPk");
        q1.setParameter("codtipoconserva", codtipoconserva);
        q1.setParameter("codencomenda", codencomenda);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            tce = ((Tipoconservaencomenda)obj);
        }
        else {
            return null;
        }
        return tce;
    }

    public static Tipoconservaencomenda readByCodpeixe(int codtipoconserva) {
        Tipoconservaencomenda tce = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Tipoconservaencomenda.findByCodtpconserva");
        q1.setParameter("codtipoconserva", codtipoconserva);
        Object obj = q1.getSingleResult();

        if(obj != null){
            tce = ((Tipoconservaencomenda)obj);
        }
        else {
            return null;
        }
        return tce;
    }

    public static Tipoconservaencomenda readByCodcompra(int codencomenda) {
        Tipoconservaencomenda tce = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Tipoconservaencomenda.findByCodencomenda");
        q1.setParameter("codencomenda", codencomenda);
        Object obj = q1.getSingleResult();

        if(obj != null){
            tce = ((Tipoconservaencomenda)obj);
        }
        else {
            return null;
        }
        return tce;
    }

    public static void update(Tipoconservaencomenda tce) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(tce);
        em.getTransaction().commit();
    }

    public static void delete(Tipoconservaencomenda tce) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(tce);
        em.getTransaction().commit();
    }
}

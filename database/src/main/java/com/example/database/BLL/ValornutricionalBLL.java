package com.example.database.BLL;

import com.example.database.DAL.Valornutricional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ValornutricionalBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadocompra on data base
    public static void create(Valornutricional vn) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(vn);
        em.getTransaction().commit();
    }

    public static List<Valornutricional> readAll(){
        List<Valornutricional> listaVN = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Valornutricional.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaVN.add((Valornutricional) o);
        }
        return listaVN;
    }

    //search comprapeixe by codcompra
    public static Valornutricional readUserBypk(int codtipoconserva, int idnutriente) {
        Valornutricional vn = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Valornutricional.findByPk");
        q1.setParameter("codtipoconserva", codtipoconserva);
        q1.setParameter("idnutriente", idnutriente);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            vn = ((Valornutricional)obj);
        }
        else {
            return null;
        }
        return vn;
    }

    public static Valornutricional readByCodpeixe(int codtipoconserva) {
        Valornutricional vn = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Valornutricional.findByCodtpconserva");
        q1.setParameter("codtipoconserva", codtipoconserva);
        Object obj = q1.getSingleResult();

        if(obj != null){
            vn = ((Valornutricional)obj);
        }
        else {
            return null;
        }
        return vn;
    }

    public static Valornutricional readByCodcompra(int idnutriente) {
        Valornutricional vn = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Valornutricional.findByIdnutriente");
        q1.setParameter("idnutriente", idnutriente);
        Object obj = q1.getSingleResult();

        if(obj != null){
            vn = ((Valornutricional)obj);
        }
        else {
            return null;
        }
        return vn;
    }

    public static void update(Valornutricional vn) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(vn);
        em.getTransaction().commit();
    }

    public static void delete(Valornutricional vn) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(vn);
        em.getTransaction().commit();
    }
}

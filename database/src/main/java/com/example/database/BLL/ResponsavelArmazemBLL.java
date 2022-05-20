package com.example.database.BLL;

import com.example.database.DAL.Responsavelarmazem;
import com.example.database.DAL.Utilizador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelArmazemBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert gestorcompra on data base
    public static void create(Responsavelarmazem ra) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(ra);
        em.getTransaction().commit();
    }

    public static List<Responsavelarmazem> readAll(){
        List<Responsavelarmazem> listaRA = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelarmazem.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaRA.add((Responsavelarmazem) o);
        }
        return listaRA;
    }

    public static Responsavelarmazem readById(int coda) {
        Responsavelarmazem ra = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelarmazem.findByIdresponsavelA");
        Object obj = q1.getSingleResult();

        q1.setParameter("coda", coda);
        if(obj != null){
            ra = ((Responsavelarmazem)obj);
        }
        else {
            return null;
        }
        return ra;
    }

    //search client by id
    public static Utilizador readUserById(int iduser) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelarmazem.findUser");
        q1.setParameter("iduser", iduser);
        Object obj = q1.getSingleResult();

        if(obj != null){
            u = ((Utilizador)obj);
        }
        else {
            return null;
        }
        return u;
    }

    //search client by username
    public static Responsavelarmazem readByUsername(String username) {
        Responsavelarmazem ra = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelarmazem.findByUsername");
        q1.setParameter("username", username);
        Object obj = q1.getSingleResult();

        if(obj != null){
            ra = ((Responsavelarmazem)obj);
        }
        else {
            return null;
        }
        return ra;
    }

    public static void update(Responsavelarmazem ra) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(ra);
        em.getTransaction().commit();
    }

    public static void delete(Responsavelarmazem ra) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(ra);
        em.getTransaction().commit();
    }
}

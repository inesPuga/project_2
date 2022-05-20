package com.example.database.BLL;

import com.example.database.DAL.Responsavelqualidade;
import com.example.database.DAL.Utilizador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ResponsavelQualidadeBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert gestorcompra on data base
    public static void create(Responsavelqualidade rq) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(rq);
        em.getTransaction().commit();
    }

    public static List<Responsavelqualidade> readAll(){
        List<Responsavelqualidade> listaRq = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelqualidade.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaRq.add((Responsavelqualidade) o);
        }
        return listaRq;   
    }

    public static Responsavelqualidade readById(int codrq) {
        Responsavelqualidade rq = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelqualidade.findByIdrq");
        Object obj = q1.getSingleResult();

        q1.setParameter("codrq", codrq);
        if(obj != null){
            rq = ((Responsavelqualidade)obj);
        }
        else {
            return null;
        }
        return rq;
    }

    //search client by id
    public static Utilizador readUserById(int iduser) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelqualidade.findUser");
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
    public static Responsavelqualidade readByUsername(String username) {
        Responsavelqualidade rq = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Responsavelqualidade.findByUsername");
        q1.setParameter("username", username);
        Object obj = q1.getSingleResult();

        if(obj != null){
            rq = ((Responsavelqualidade)obj);
        }
        else {
            return null;
        }
        return rq;
    }

    public static void update(Responsavelqualidade rq) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(rq);
        em.getTransaction().commit();
    }

    public static void delete(Responsavelqualidade rq) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(rq);
        em.getTransaction().commit();
    }
}

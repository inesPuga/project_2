package com.example.database.BLL;

import com.example.database.DAL.Gestorstock;
import com.example.database.DAL.Utilizador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class GestorstockBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert gestorcompra on data base
    public static void create(Gestorstock gs) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(gs);
        em.getTransaction().commit();
    }

    public static List<Gestorstock> readAll(){
        List<Gestorstock> listaGestorStock = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorstock.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaGestorStock.add((Gestorstock) o);
        }
        return listaGestorStock;
    }

    public static Gestorstock readById(int idgs) {
        Gestorstock gs = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorstock.findByIdgs");
        Object obj = q1.getSingleResult();

        q1.setParameter("codgs", idgs);
        if(obj != null){
            gs = ((Gestorstock)obj);
        }
        else {
            return null;
        }
        return gs;
    }

    //search client by id
    public static Utilizador readUserById(int iduser) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorstock.findUser");
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
    public static Gestorstock readByUsername(String username) {
        Gestorstock gs = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorstock.findByUsername");
        q1.setParameter("username", username);
        Object obj = q1.getSingleResult();

        if(obj != null){
            gs = ((Gestorstock)obj);
        }
        else {
            return null;
        }
        return gs;
    }

    public static void update(Gestorstock gs) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(gs);
        em.getTransaction().commit();
    }

    public static void delete(Gestorstock gs) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(gs);
        em.getTransaction().commit();
    }
}

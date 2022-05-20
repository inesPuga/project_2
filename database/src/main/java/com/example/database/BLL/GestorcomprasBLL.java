package com.example.database.BLL;

import com.example.database.DAL.Gestorcompras;
import com.example.database.DAL.Utilizador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class GestorcomprasBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert gestorcompra on data base
    public static void create(Gestorcompras gc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(gc);
        em.getTransaction().commit();
    }

    public static List<Gestorcompras> readAll(){
        List<Gestorcompras> listaGestorcompras = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorcompras.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaGestorcompras.add((Gestorcompras) o);
        }
        return listaGestorcompras;
    }

    public static Gestorcompras readById(int idgc) {
        Gestorcompras gc = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorcompras.findByIdgc");
        Object obj = q1.getSingleResult();

        q1.setParameter("codgc", idgc);
        if(obj != null){
            gc = ((Gestorcompras)obj);
        }
        else {
            return null;
        }
        return gc;
    }

    //search client by id
    public static Utilizador readUserById(int iduser) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorcompras.findUser");
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
    public static Gestorcompras readByUsername(String username) {
        Gestorcompras gc = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorcompras.findByUsername");
        q1.setParameter("username", username);
        Object obj = q1.getSingleResult();

        if(obj != null){
            gc = ((Gestorcompras)obj);
        }
        else {
            return null;
        }
        return gc;
    }

    public static void update(Gestorcompras gc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(gc);
        em.getTransaction().commit();
    }

    public static void delete(Gestorcompras gc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(gc);
        em.getTransaction().commit();
    }

}

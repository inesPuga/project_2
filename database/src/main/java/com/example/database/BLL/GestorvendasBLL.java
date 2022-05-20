package com.example.database.BLL;

import com.example.database.DAL.Gestorvendas;
import com.example.database.DAL.Utilizador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class GestorvendasBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert gestorcompra on data base
    public static void create(Gestorvendas gv) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(gv);
        em.getTransaction().commit();
    }

    public static List<Gestorvendas> readAll(){
        List<Gestorvendas> listaGv = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorvendas.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaGv.add((Gestorvendas) o);
        }
        return listaGv;
    }

    public static Gestorvendas readById(int idgv) {
        Gestorvendas gv = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorvendas.findByIdgv");
        Object obj = q1.getSingleResult();

        q1.setParameter("codgv", idgv);
        if(obj != null){
            gv = ((Gestorvendas)obj);
        }
        else {
            return null;
        }
        return gv;
    }

    //search client by id
    public static Utilizador readUserById(int iduser) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorvendas.findUser");
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
    public static Gestorvendas readByUsername(String username) {
        Gestorvendas gv = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Gestorvendas.findByUsername");
        q1.setParameter("username", username);
        Object obj = q1.getSingleResult();

        if(obj != null){
            gv = ((Gestorvendas)obj);
        }
        else {
            return null;
        }
        return gv;
    }

    public static void update(Gestorvendas gv) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(gv);
        em.getTransaction().commit();
    }

    public static void delete(Gestorvendas gv) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(gv);
        em.getTransaction().commit();
    }
}

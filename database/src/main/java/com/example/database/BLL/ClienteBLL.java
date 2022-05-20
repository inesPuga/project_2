package com.example.database.BLL;

import com.example.database.DAL.Cliente;
import com.example.database.DAL.Utilizador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ClienteBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert client on data base
    public static void create(Cliente cli) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(cli);
        em.getTransaction().commit();
    }

    //read clients
    public static List<Cliente> readAll() {
        List<Cliente> listaCli = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Cliente.findAll");
        List<Object> result = q1.getResultList();

        for(Object cli : result){
            listaCli.add((Cliente)cli);
        }
        return listaCli;
    }

    //search client by id
    public static Cliente readById(int idCliente) {
        Cliente cli = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Cliente.findByIdcliente");
        q1.setParameter("idcliente", idCliente);
        Object obj = q1.getSingleResult();

        if(obj != null){
            cli = ((Cliente)obj);
        }
        else {
            return null;
        }
        return cli;
    }

    //search client by id
    public static Utilizador readUserById(int iduser) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Cliente.findUser");
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
    public static Cliente readByUsername(String username) {
        Cliente cli = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Cliente.findByUsername");
        q1.setParameter("username", username);
        Object obj = q1.getSingleResult();

        if(obj != null){
            cli = ((Cliente)obj);
        }
        else {
            return null;
        }
        return cli;
    }

    public static void update(Cliente cli) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(cli);
        em.getTransaction().commit();
    }

    //delete client
    public static void delete(Cliente cli) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(cli);
        em.getTransaction().commit();
    }

}


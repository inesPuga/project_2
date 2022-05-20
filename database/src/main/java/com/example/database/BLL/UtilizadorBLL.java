package com.example.database.BLL;

import com.example.database.DAL.Utilizador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UtilizadorBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert user on data base
    public static void create(Utilizador u) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    //read users
    public static List<Utilizador> readAll(){
        List<Utilizador> listaUser = new ArrayList<>();

        System.out.println(listaUser);
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        System.out.println("factory" + factory);
        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Utilizador.findAll");
        System.out.println("LISTA " + q1);
        List<Object> result = q1.getResultList();

        for(Object u : result){
            listaUser.add((Utilizador)u);
        }
        return listaUser;
    }

    //verify data for login
    public static int verifyLogin(String tempUsername, String tempPassword) {
        for(Utilizador u : readAll()) {
            if(u.getUsername().equals(tempUsername) && u.getPassword().equals(tempPassword)) {
                return 0;   //logged in user exists
            }
        }
        return 1;   //logged in user does not exist
    }

    //check if username exists -> create user
    public static int checkUsername(Utilizador user) {
        boolean found = false;

        for(Utilizador u : readAll()) {
            if(user.getUsername().equals(u.getUsername())) {
                found = true;
            }
        }
        if(!found) create(user);
        if(found) {
            System.out.println("Error - username exists");
            return 1;   //username exists
        }

        return 0;
    }

    //search user by id
    public static Utilizador readById(int idUser) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Utilizador.findByIduser");
        q1.setParameter("iduser", idUser);
        Object obj = q1.getSingleResult();

        if(obj != null){
            u = ((Utilizador)obj);
        }
        else {
            return null;
        }
        return u;
    }

    //search user by username
    public static Utilizador readByUsername(String username) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Utilizador.findAllByUsername");
        q1.setParameter("username", username);
        Object obj = q1.getSingleResult();

        if(obj != null){
            u = ((Utilizador)obj);
        }
        else {
            return null;
        }
        return u;
    }

    //search user by username
    public static Utilizador readByEmail(String email) {
        Utilizador u = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Utilizador.findAllByEmail");
        q1.setParameter("email", email);
        Object obj = q1.getSingleResult();

        if(obj != null){
            u = ((Utilizador)obj);
        }
        else {
            return null;
        }
        return u;
    }

    public static void update(Utilizador u) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
    }

    public static void delete(Utilizador u){
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
    }

}

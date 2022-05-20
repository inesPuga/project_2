package com.example.database.BLL;

import com.example.database.DAL.Nutriente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class NutrienteBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    public static void create(Nutriente n) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(n);
        em.getTransaction().commit();
    }

    public static List<Nutriente> readAll(){
        List<Nutriente> listaNutriente = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Nutriente.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaNutriente.add((Nutriente) o);
        }
        return listaNutriente;
    }

    public static Nutriente readByCodnutriente(int idnutri) {
        Nutriente n = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Nutriente.findByIdnutriente");
        q1.setParameter("idnutriente", idnutri);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            n = ((Nutriente)obj);
        }
        else {
            return null;
        }
        return n;
    }

    public static Nutriente readByNome(String nome) {
        Nutriente n = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Nutriente.findByNomenutriente");
        q1.setParameter("nomenutriente", nome);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            n = ((Nutriente)obj);
        }
        else {
            return null;
        }
        return n;
    }

    public static void update(Nutriente n) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(n);
        em.getTransaction().commit();
    }

    public static void delete(Nutriente n) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(n);
        em.getTransaction().commit();
    }
}

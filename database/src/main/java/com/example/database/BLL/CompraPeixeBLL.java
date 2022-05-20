package com.example.database.BLL;

import com.example.database.DAL.Comprapeixe;
import com.example.database.DAL.Cpostal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CompraPeixeBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert cpostal on data base
    public static void create(Comprapeixe cp) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(cp);
        em.getTransaction().commit();
    }

    public static List<Comprapeixe> readAll(){
        List<Comprapeixe> listaComprapeixe = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Comprapeixe.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaComprapeixe.add((Comprapeixe) o);
        }
        return listaComprapeixe;
    }

    //search comprapeixe by codcompra
    public static Comprapeixe readByCodcompra(int codcompra) {
        Comprapeixe cp = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Comprapeixe.findByCodcompra");
        q1.setParameter("codcompra", codcompra);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            cp = ((Comprapeixe)obj);
        }
        else {
            return null;
        }
        return cp;
    }

    //search comprapeixe by codgc)
    public static Comprapeixe readByCodgc(int codgc) {
        Comprapeixe cp = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Comprapeixe.findByCodgc");
        q1.setParameter("codgc", codgc);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            cp = ((Comprapeixe)obj);
        }
        else {
            return null;
        }
        return cp;
    }

    //delete cpostal
    public static void delete(Cpostal cp) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(cp);
        em.getTransaction().commit();
    }

}

package com.example.database.BLL;

import com.example.database.DAL.Peixecomprapeixe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PeixecomprapeixeBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadocompra on data base
    public static void create(Peixecomprapeixe pc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(pc);
        em.getTransaction().commit();
    }

    public static List<Peixecomprapeixe> readAll(){
        List<Peixecomprapeixe> listaCompra = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixecomprapeixe.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaCompra.add((Peixecomprapeixe) o);
        }
        return listaCompra;
    }

    //search comprapeixe by codcompra
    public static Peixecomprapeixe readUserBypk(int codpeixe, int codcompra) {
        Peixecomprapeixe pc = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixecomprapeixe.findByPk");
        q1.setParameter("codpeixe", codpeixe);
        q1.setParameter("codcompra", codcompra);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            pc = ((Peixecomprapeixe)obj);
        }
        else {
            return null;
        }
        return pc;
    }

    public static Peixecomprapeixe readByCodpeixe(int codpeixe) {
        Peixecomprapeixe pc = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixecomprapeixe.findByCodpeixe");
        q1.setParameter("codpeixe", codpeixe);
        Object obj = q1.getSingleResult();

        if(obj != null){
            pc = ((Peixecomprapeixe)obj);
        }
        else {
            return null;
        }
        return pc;
    }

    public static Peixecomprapeixe readByCodcompra(int codcompra) {
        Peixecomprapeixe pc = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixecomprapeixe.findByCodcompra");
        q1.setParameter("codcompra", codcompra);
        Object obj = q1.getSingleResult();

        if(obj != null){
            pc = ((Peixecomprapeixe)obj);
        }
        else {
            return null;
        }
        return pc;
    }

    public static void update(Peixecomprapeixe pc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(pc);
        em.getTransaction().commit();
    }

    public static void delete(Peixecomprapeixe pc) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(pc);
        em.getTransaction().commit();
    }
}

package com.example.database.BLL;

import com.example.database.DAL.Peixerequisicao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class PeixerequisicaoBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    //insert estadocompra on data base
    public static void create(Peixerequisicao pr) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(pr);
        em.getTransaction().commit();
    }

    public static List<Peixerequisicao> readAll(){
        List<Peixerequisicao> listaPeixereq = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixerequisicao.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result) {
            listaPeixereq.add((Peixerequisicao) o);
        }
        return listaPeixereq;
    }

    public static Peixerequisicao readUserBypk(int codpeixe, int codrequisicao) {
        Peixerequisicao pr = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixerequisicao.findByPk");
        q1.setParameter("codpeixe", codpeixe);
        q1.setParameter("codrequisicao", codrequisicao);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            pr = ((Peixerequisicao)obj);
        }
        else {
            return null;
        }
        return pr;
    }

    public static Peixerequisicao readByCoddocqld(int codrequisicao) {
        Peixerequisicao pr = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixerequisicao.findByCodreq");
        q1.setParameter("codrequisicao", codrequisicao);
        Object obj = q1.getSingleResult();

        if(obj != null){
            pr = ((Peixerequisicao)obj);
        }
        else {
            return null;
        }
        return pr;
    }

    public static Peixerequisicao readByCodcompra(int codpeixe) {
        Peixerequisicao pr = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Peixerequisicao.findByCodpeixe");
        q1.setParameter("codpeixe", codpeixe);
        Object obj = q1.getSingleResult();

        if(obj != null){
            pr = ((Peixerequisicao)obj);
        }
        else {
            return null;
        }
        return pr;
    }

    public static void update(Peixerequisicao pr) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(pr);
        em.getTransaction().commit();
    }

    public static void delete(Peixerequisicao pr) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(pr);
        em.getTransaction().commit();
    }

}

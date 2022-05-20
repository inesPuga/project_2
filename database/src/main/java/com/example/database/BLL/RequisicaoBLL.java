package com.example.database.BLL;

import com.example.database.DAL.Requisicao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoBLL {
    private static final String PERSISTENCE_UNIT_NAME = "default";
    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    public static void create(Requisicao r) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }

    public static List<Requisicao> readAll(){
        List<Requisicao> listaRq = new ArrayList<>();
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Requisicao.findAll");
        List<Object> result = q1.getResultList();

        for(Object o : result){
            listaRq.add((Requisicao) o);
        }
        return listaRq;
    }

    public static Requisicao readByCodpeixe(int codrequisicao) {
        Requisicao r = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Requisicao.findByCodrequisicao");
        q1.setParameter("codrequisicao", codrequisicao);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            r = ((Requisicao)obj);
        }
        else {
            return null;
        }
        return r;
    }

    public static Requisicao readByNome(int codgs) {
        Requisicao r = null;
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        Query q1 = em.createNamedQuery("Requisicao.findByCodgs");
        q1.setParameter("codgs", codgs);
        Object obj = q1.getSingleResult();

        if(obj != null) {
            r = ((Requisicao)obj);
        }
        else {
            return null;
        }
        return r;
    }

    public static void update(Requisicao r) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(r);
        em.getTransaction().commit();
    }

    public static void delete(Requisicao r) {
        if(factory == null)
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        if (em == null) em = factory.createEntityManager();

        em.getTransaction().begin();
        em.remove(r);
        em.getTransaction().commit();
    }
}

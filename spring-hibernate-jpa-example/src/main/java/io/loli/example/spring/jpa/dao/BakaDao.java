package io.loli.example.spring.jpa.dao;

import io.loli.example.spring.jpa.entity.Baka;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class BakaDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Baka baka) {
        em.persist(baka);
    }

}

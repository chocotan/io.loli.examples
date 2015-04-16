package io.loli.example.spring.jpa.service;

import io.loli.example.spring.jpa.dao.BakaDao;
import io.loli.example.spring.jpa.entity.Baka;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
public class BakaService {
    @Inject
    private BakaDao bakaDao;

    @Transactional
    public void save(Baka baka) {
        bakaDao.save(baka);
    }
}

package io.loli.lightbbs.service;

import static org.junit.Assert.assertNotNull;
import io.loli.example.spring.jpa.entity.Baka;
import io.loli.example.spring.jpa.service.BakaService;
import io.loli.lightbbs.BaseTest;

import javax.inject.Inject;

import org.junit.Test;

public class BakaServiceTest extends BaseTest {

    @Inject
    private BakaService bakaService;

    @Test
    public void testSave() {
        Baka baka = new Baka();
        bakaService.save(baka);
        System.out.println(baka.getId());
        assertNotNull(baka.getId());
    }
}

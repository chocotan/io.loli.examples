package io.loli.example.ibatis.service;

import io.loli.example.ibatis.mapper.ClazzMapper;
import io.loli.example.ibatis.model.Clazz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Service
@CacheConfig(cacheNames = "clazz")
public class ClazzService implements IClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Transactional
    @CacheEvict(allEntries = true)
    public void insert(Clazz clazz) {
        clazzMapper.insert(clazz);
    }

    @Transactional
    // allEntries=true not working for redis
    @CacheEvict(allEntries = true, beforeInvocation = true)
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);
    }

    public Clazz findById(Long id) {
        return clazzMapper.findById(id);
    }

    public void deleteById(Long id) {
        clazzMapper.deleteById(id);
    }

    @Cacheable(key = "'findByName'.concat(#q).concat('-').concat(#page.page)")
    public List<Clazz> findByName(String q, PageBounds page) {
        return clazzMapper.findByName(q, page);
    }

    @Cacheable(key = "''.concat(#page).concat(#limit)")
    public List<Clazz> findAll(int page, int limit) {
        PageBounds p = new PageBounds(page, limit);

        return clazzMapper.findAll(p);
    }

    public List<String> findTest() {
        List<String> test = new ArrayList<String>();
        test.add("test1");
        test.add("test2");
        return test;
    }
}

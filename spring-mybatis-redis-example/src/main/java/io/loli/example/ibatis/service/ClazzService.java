package io.loli.example.ibatis.service;

import io.loli.example.ibatis.mapper.ClazzMapper;
import io.loli.example.ibatis.model.Clazz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    // public List<Clazz> findAll() {
    // return clazzMapper.findAll();
    // }

    /*
     * (non-Javadoc)
     * 
     * @see
     * io.hime.test.ibatis.service.IClazzService#insert(io.hime.test.ibatis.
     * model.Clazz)
     */
    @Transactional
    public void insert(Clazz clazz) {
        clazzMapper.insert(clazz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * io.hime.test.ibatis.service.IClazzService#update(io.hime.test.ibatis.
     * model.Clazz)
     */
    public void update(Clazz clazz) {
        clazzMapper.update(clazz);

    }

    /*
     * (non-Javadoc)
     * 
     * @see io.hime.test.ibatis.service.IClazzService#findById(java.lang.Long)
     */
    public Clazz findById(Long id) {
        return clazzMapper.findById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see io.hime.test.ibatis.service.IClazzService#deleteById(java.lang.Long)
     */
    public void deleteById(Long id) {
        clazzMapper.deleteById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * io.hime.test.ibatis.service.IClazzService#findByName(java.lang.String,
     * com.github.miemiedev.mybatis.paginator.domain.PageBounds)
     */
    @Cacheable(value = "redis", key = "#q")
    public List<Clazz> findByName(String q, PageBounds page) {
        return clazzMapper.findByName(q, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * io.hime.test.ibatis.service.IClazzService#findAll(com.github.miemiedev
     * .mybatis.paginator.domain.PageBounds)
     */
    public List<Clazz> findAll(PageBounds p) {
        return clazzMapper.findAll(p);
    }

    public List<String> findTest() {
        List<String> test = new ArrayList<String>();
        test.add("test1");
        test.add("test2");
        return test;
    }
}

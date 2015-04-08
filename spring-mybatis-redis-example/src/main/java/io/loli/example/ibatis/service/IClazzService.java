package io.loli.example.ibatis.service;

import io.loli.example.ibatis.model.Clazz;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface IClazzService {

    public abstract void insert(Clazz clazz);

    public abstract void update(Clazz clazz);

    public abstract Clazz findById(Long id);

    public abstract void deleteById(Long id);

    public abstract List<Clazz> findByName(String q, PageBounds page);

    public abstract List<Clazz> findAll(int page, int limit);

    public abstract List<String> findTest();

}
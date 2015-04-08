package io.loli.example.ibatis.mapper;

import io.loli.example.ibatis.model.Clazz;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Repository
public interface ClazzMapper {

    @Insert("insert into clazz(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int insert(Clazz name);

    @Update("update clazz set name=#{name} where id=#{id}")
    public void update(Clazz clazz);

    @Select("select * from clazz where id=#{id}")
    public Clazz findById(@Param("id") Long id);

    @Delete("delete from clazz where id=#{id}")
    public void deleteById(Long id);

    @Select("select * from clazz where name like #{q}")
    public List<Clazz> findByName(String q, PageBounds page);

    @Select("select * from clazz where 1=1")
    public List<Clazz> findAll(PageBounds page);

}

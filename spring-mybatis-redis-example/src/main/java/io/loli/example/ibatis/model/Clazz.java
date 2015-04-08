package io.loli.example.ibatis.model;

import java.io.Serializable;

public class Clazz implements Serializable {

    private static final long serialVersionUID = -6596706097032316916L;
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

}

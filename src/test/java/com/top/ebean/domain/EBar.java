package com.top.ebean.domain;

import com.avaje.ebean.annotation.Cache;

import javax.persistence.Entity;

@Cache
@Entity
public class EBar extends EBase {

    String name;


    public EBar() {
    }

    public EBar(String name) {
        this.name = name;
    }

    public String toString() {
        return "[id:" + id + " name:" + name + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

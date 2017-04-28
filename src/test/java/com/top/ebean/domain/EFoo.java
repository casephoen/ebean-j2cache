package com.top.ebean.domain;

import com.avaje.ebean.annotation.Cache;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Cache(enableQueryCache=true)
@Entity
public class EFoo extends EBase {

    public enum Status {
        NEW,
        ACTIVE,
        INACTIVE
    }

    Status status;

    String name;

    String notes;

    @OneToMany(cascade = CascadeType.ALL)
    List<EBar> barList;

    public EFoo() {
    }

    public EFoo(String name) {
        this.name = name;
        this.status = Status.NEW;
    }

    public String toString() {
        return "[id:" + id + " name:" + name + "]";
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

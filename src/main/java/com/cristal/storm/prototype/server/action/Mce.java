package com.cristal.storm.prototype.server.action;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Transient;

public class Mce {

    @Id
    Long id;

    public String uri;
    public Set<String> tags;

    // @Transient String doNotPersist;

    @SuppressWarnings("unused")
    private Mce() {
    }

    public Mce(String uri, Set<String> tags) {
        this.uri = uri;
        this.tags = tags;
    }
}
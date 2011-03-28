package com.cristal.storm.prototype.server.action;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Transient;

public class Mce {

    @Id
    Long id;

    String uri;
    Set<String> tag;

    // @Transient String doNotPersist;

    @SuppressWarnings("unused")
    private Mce() {
    }

    public Mce(String uri, Set<String> tag) {
        this.uri = uri;
        this.tag = tag;
    }
}
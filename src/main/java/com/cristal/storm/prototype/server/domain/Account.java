package com.cristal.storm.prototype.server.domain;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Account extends DatastoreObject {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private String name;
    
    //Keys
    private List<Key<AppUser>> owningUser;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public Account() {
        owningUser = new ArrayList();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public String toString() {
        return "[name : " + getName() + ", id: " + getId() + ", owners : " + getOwners() + "]";
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public String getString() {
        return this.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addOwner(AppUser owner) {
        this.owningUser.add(new Key<AppUser>(AppUser.class, owner.getId()));
    }

    public List<Key<AppUser>> getOwners() {
        return owningUser;
    }
}
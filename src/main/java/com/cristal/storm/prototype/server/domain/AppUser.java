/**
 *
 *
 * @author Jose Rose
 * 2011-07-18
 */
package com.cristal.storm.prototype.server.domain;

import javax.persistence.Transient;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class AppUser extends DatastoreObject {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    @Transient
    public static String EMAIL_FIELD_NAME = "email"; 
    private String email;
    
    // Keys
    private Key<AppUser>   boss;
    private Key<AppUser>[] subordinates;
    private Key<Domain>    userDomain;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public AppUser() {
    }
    
    public AppUser(String email) {
        this.email = email;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "[email : " + getEmail() + ", id: " + getId() + ", boss : " + getBoss() + ", subordinates : " + getSubordinates() + "]";
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public String getDescription() {
        return this.toString();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBoss(Key<AppUser> boss) {
        this.boss = boss;
    }

    public Key<AppUser> getBoss() {
        return boss;
    }
    
    public void setDomain(Domain userDomain) {
        this.userDomain = new Key<Domain>(Domain.class, userDomain.getId());
    }

    public Key<Domain> getDomain() {
        return userDomain;
    }

    public void setSubordinates(Key<AppUser>[] subordinates) {
        this.subordinates = subordinates;
    }

    public Key<AppUser>[] getSubordinates() {
        return subordinates;
    }
}

/**
 *
 *
 * @author Jose Rose
 * 2011-07-18
 */
package com.cristal.storm.prototype.server.domain;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class AppUser extends DatastoreObject {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private String email;

    // Keys
    private Key<AppUser>    boss;
    private Key<AppUser>[]  subodinates;

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
        return "[email : " + getEmail() + ", id: " + getId() + ", boss : " + getBoss() + ", subodinates : " + getSubodinates() + "]";
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public String getString() {
        return this.toString();
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    void setBoss(Key<AppUser> boss) {
        this.boss = boss;
    }

    Key<AppUser> getBoss() {
        return boss;
    }

    private void setSubodinates(Key<AppUser>[] subodinates) {
        this.subodinates = subodinates;
    }

    private Key<AppUser>[] getSubodinates() {
        return subodinates;
    }
}

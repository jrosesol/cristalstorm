/**
 *
 *
 * @author Jose Rose
 * 2011-03-27
 */
package com.cristal.storm.prototype.server.action;

import java.util.Set;

import javax.persistence.Id;

import com.googlecode.objectify.Key;

/**
 * TODO: Add comments for User
 *
 */
public class User {
    @Id
    Long id;
    
    String userName;
    Key<Mce> mceCollection;

    // @Transient String doNotPersist;

    @SuppressWarnings("unused")
    private User() {
    }

    public User(String userName) {
        this.userName = userName;
    }
}

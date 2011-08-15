/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.server.domain;

import javax.persistence.Transient;

/**
 * TODO: Add comments for Domain
 *
 */
public class Domain extends DatastoreObject {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    @Transient
    public static String NAME_FIELD_NAME = "name";
    private String name;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public Domain() {
        
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        return "";
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public String getDescription() {
        return this.toString();
    }

    public void setName(String domainName) {
        this.name = domainName;
    }

    public String getName() {
        return name;
    }
}

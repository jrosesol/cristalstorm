/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.server.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;

/**
 * TODO: Add comments for DomainTimeCodes
 *
 */
@Entity
public class DomainTimeCodes extends DatastoreObject {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * This list stores all time codes for the DOMAIN. They cannot be deleted
     * as this would be a data integrity issue. Once a time code is added
     * it will stay there.
     */
    @Embedded protected TimeEntryCodes domainTimeCodes;
    
    @Transient public static String NAME_FIELD_NAME = "name";
    @Indexed private String name;
    
    @Transient public static String DOMAIN_FIELD_NAME = "domain";
    @Indexed protected Key<Domain> domain;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public DomainTimeCodes() {
        domainTimeCodes = new TimeEntryCodes();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Override
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
   
    public void setTimeCode(Long timeCode, String timeCodeValue) {
        domainTimeCodes.addTimeCode(timeCode, timeCodeValue);
    }
   
    public List<Long> getTimeCodes() {
        return domainTimeCodes.getTimeCodes();
    }
    
    public List<String> getTimeCodeValues() {
        return domainTimeCodes.getTimeCodeValues();
    }
    
    public void initTimeCodes() {
        domainTimeCodes.initTimeCodes();
    }

    public void setDomain(Domain domain) {
        this.domain = new Key<Domain>(Domain.class, domain.getId());
        setDomainName(domain.getName());
    }
    
    private void setDomainName(String name) {
        this.name = name;
    }
    
    public String getDomainName() {
        return name;
    }

    public Key<Domain> getDomain() {
        return domain;
    }
}

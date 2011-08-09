/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import com.cristal.storm.prototype.shared.proxy.TimeEntryCode;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

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
    @Embedded
    private List<TimeEntryCode> domainTimeEntryCodes;
    
    @Transient
    public static String DOMAIN_FIELD_NAME = "domain";
    private Key<Domain> domain;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public DomainTimeCodes() {
        this.domainTimeEntryCodes = new ArrayList<TimeEntryCode>();
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
    
    public void addTimeEntryCode(TimeEntryCode timeEntryCode) {
        domainTimeEntryCodes.add(timeEntryCode);
    }
    
    public List<TimeEntryCode> getTimeEntryCodes() {
        return domainTimeEntryCodes;
    }

    public void setDomain(Key<Domain> domain) {
        this.domain = domain;
    }

    public Key<Domain> getDomain() {
        return domain;
    }
}

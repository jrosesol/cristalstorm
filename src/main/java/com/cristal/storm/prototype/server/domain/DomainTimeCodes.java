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
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
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
    
    public void addTimeCode(TimeCodeType timeCodeType, String timeCodeValue) {
        domainTimeEntryCodes.add(new TimeEntryCode(timeCodeType, timeCodeValue));
    }
    
    public void addTimeCode(TimeCodeType timeCodeType) {
        domainTimeEntryCodes.add(new TimeEntryCode(timeCodeType));
    }

    public List<TimeCodeType> getTimeCodeTypes() {
        List<TimeCodeType> timeCodeList = new ArrayList<TimeCodeType>();

        for (TimeEntryCode timeCode : domainTimeEntryCodes) {
            timeCodeList.add(timeCode.getTimeEntryCode());
        }
        return timeCodeList;
    }
    
    public List<String> getTimeCodeValues() {
        List<String> timeCodeList = new ArrayList<String>();

        for (TimeEntryCode timeCode : domainTimeEntryCodes) {
            timeCodeList.add(timeCode.getTimeEntryValue());
        }
        return timeCodeList;
    }

    public void setDomain(Key<Domain> domain) {
        this.domain = domain;
    }

    public Key<Domain> getDomain() {
        return domain;
    }
}

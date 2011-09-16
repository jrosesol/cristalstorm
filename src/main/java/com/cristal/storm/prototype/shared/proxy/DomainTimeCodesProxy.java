/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.shared.proxy;

import java.util.List;
import java.util.Map;

import com.cristal.storm.prototype.server.domain.DomainTimeCodes;
import com.cristal.storm.prototype.server.locator.ObjectifyLocator;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * TODO: Add comments for DomainTimeCodesProxy
 * 
 */
@ProxyFor(value = DomainTimeCodes.class, locator = ObjectifyLocator.class)
public interface DomainTimeCodesProxy extends BaseProxy {

    public List<Long> getTimeCodes();
    public List<String> getTimeCodeValues();
    //public void setTimeCode(Long timeCode, String timeCodeValue);

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Time codes for the time entries. <br>
     * <code>NORMAL</code> : is the value for a normal work entry. <br>
     * <code>EXTENDED</code> : is a user defined code which only allows to input
     * time.
     * 
     */
     static long NORMAL         = 1;
     static long PAID_VACATION  = 2;
     static long SICKNESS       = 3;
     static long HOLIDAY        = 4;
     static long LUNCH          = 5;
     static long EXTENDED       = 6;
}

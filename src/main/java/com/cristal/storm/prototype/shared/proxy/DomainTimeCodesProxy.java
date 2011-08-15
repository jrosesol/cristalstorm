/**
 *
 *
 * @author Jose Rose
 * 2011-08-08
 */
package com.cristal.storm.prototype.shared.proxy;

import java.util.List;

import com.cristal.storm.prototype.server.domain.DomainTimeCodes;
import com.cristal.storm.prototype.server.locator.ObjectifyLocator;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * TODO: Add comments for DomainTimeCodesProxy
 *
 */
@ProxyFor(value = DomainTimeCodes.class, locator = ObjectifyLocator.class)
public interface DomainTimeCodesProxy extends BaseProxy {
    public List<TimeCodeType> getTimeCodeTypes();
    public List<String> getTimeCodeValues();
}

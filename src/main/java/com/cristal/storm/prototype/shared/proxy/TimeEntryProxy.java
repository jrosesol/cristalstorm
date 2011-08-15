package com.cristal.storm.prototype.shared.proxy;

import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.server.domain.TimeEntry;
import com.cristal.storm.prototype.server.locator.ObjectifyLocator;
import com.cristal.storm.prototype.shared.proxy.TimeEntryCode.TimeCodeType;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = TimeEntry.class, locator = ObjectifyLocator.class)
public interface TimeEntryProxy extends BaseProxy {
    public void setTimeEntryTimestamp(Date timeEntryTimestamp);
    public Date getTimeEntryTimestamp();
    public void setSpentTime(double spentTime);
    public double getSpentTime();
    public Long getId();
    public long getOwningUserId();
    public long getOwningActivityId();
    public long getOwningAccountId();
    public TimeCodeType getTimeCode();
    public void setTimeCode(TimeCodeType timeEntryCode);
    public String getTimeCodeValue();
    public void setTimeCodeValue(String timeCodeValue);
}

package com.cristal.storm.prototype.shared.proxy;

import java.util.Date;

import com.cristal.storm.prototype.server.domain.TimeEntry;
import com.cristal.storm.prototype.server.locator.ObjectifyLocator;
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
    public Long getTimeCode();
    public void setTimeCode(Long timeEntryCode);
    public String getTimeCodeValue();
    public void setTimeCodeValue(String timeCodeValue);
}

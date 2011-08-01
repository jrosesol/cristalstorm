package com.cristal.storm.prototype.shared.proxy;

import java.util.Date;
import java.util.List;

import com.cristal.storm.prototype.server.domain.TimeEntry;
import com.cristal.storm.prototype.server.locator.ObjectifyLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = TimeEntry.class, locator = ObjectifyLocator.class)
public interface TimeEntryProxy extends EntityProxy {
    public void setTimeEntryTimestamp(Date timeEntryTimestamp);
    public Date getTimeEntryTimestamp();
    public void setSpentTime(double spentTime);
    public double getSpentTime();
    public Long getId();
    public long getOwningUserId();
    public long getOwningActivityId();
    public long getOwningAccountId();
}

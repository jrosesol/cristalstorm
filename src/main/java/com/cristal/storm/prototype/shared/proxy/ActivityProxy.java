package com.cristal.storm.prototype.shared.proxy;

import com.cristal.storm.prototype.server.domain.Activity;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.cristal.storm.prototype.server.locator.ObjectifyLocator;

@ProxyFor(value = Activity.class, locator = ObjectifyLocator.class)
public interface ActivityProxy extends EntityProxy {
    public String getName();
    public void setName(String name);
    public Long getId();
}

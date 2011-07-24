package com.cristal.storm.prototype.shared.proxy;

import com.cristal.storm.prototype.server.domain.AppUser;
import com.cristal.storm.prototype.server.locator.ObjectifyLocator;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

@ProxyFor(value = AppUser.class, locator = ObjectifyLocator.class)
public interface AppUserProxy extends EntityProxy {
    public String getEmail();
}

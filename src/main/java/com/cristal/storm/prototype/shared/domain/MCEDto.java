/**
 * 
 */
package com.cristal.storm.prototype.shared.domain;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Admin
 *
 */
public class MCEDto implements IsSerializable {
    public String uri;

    public String tag;

    public Date created;
}

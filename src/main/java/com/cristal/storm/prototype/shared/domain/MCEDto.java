/**
 * 
 */
package com.cristal.storm.prototype.shared.domain;

import java.util.Date;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Admin
 *
 */
public class MceDto implements IsSerializable {
    public String uri;

    public String tag;

    public Date created;
}
/**
 *
 *
 * @author Jose Rose
 * 2011-04-30
 */
package com.cristal.storm.prototype.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * TODO: Add comments for Resources
 *
 */
public interface Resources extends ClientBundle {
    Resources INSTANCE = GWT.create(Resources.class);

    @Source("public/html/timesheet.html")
    TextResource synchronous();

}

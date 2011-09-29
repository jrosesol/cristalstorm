/**
 *
 *
 * @author Jose Rose
 * 2011-09-23
 */
package com.cristal.storm.prototype.client.mvp;

import com.cristal.storm.prototype.client.util.UtilFunc;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.Proxy;

/**
 * PresenterWithID is used to force ID in each widget. Not very necessary for singleton as any event
 * directed to this widget cannot be ambiguous. Otherwise it can be used to check if an event is
 * really directed to this specific presenter by firing the event using this WID.
 *
 */
@Singleton
public abstract class PresenterWithID<V extends View, Proxy_ extends Proxy<?>> extends PresenterWidget<V> {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * Widget Identifier is unique to ensure the relationship between an event and
     * implementing handler.
     */
    public final long WIDGET_ID = UtilFunc.generateUID();

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public PresenterWithID(EventBus eventBus, V view, Proxy_ proxy) {
        super(eventBus, view);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Handlers
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}

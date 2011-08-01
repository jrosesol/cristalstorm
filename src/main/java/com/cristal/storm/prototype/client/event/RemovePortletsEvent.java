/**
 *
 *
 * @author Jose Rose
 * 2011-07-30
 */
package com.cristal.storm.prototype.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.gwtplatform.mvp.client.HasEventBus;

/**
 * This is a temp event to remove all attached portlets, I'll remove it when I find a way to do this better.
 *
 */
public class RemovePortletsEvent extends GwtEvent<RemovePortletsEvent.RemovePortletsHandler> {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static final Type<RemovePortletsHandler> TYPE = new Type<RemovePortletsHandler>();

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////
    public interface RemovePortletsHandler extends EventHandler {
        public void onRemovePortlets(RemovePortletsEvent event);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public RemovePortletsEvent() {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Type<RemovePortletsHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(RemovePortletsHandler handler) {
        handler.onRemovePortlets(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    public static Type<RemovePortletsHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source) {
        source.fireEvent(new RemovePortletsEvent());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}

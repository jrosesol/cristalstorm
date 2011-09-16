/**
 *
 *
 * @author Jose Rose
 * 2011-08-05
 */
package com.cristal.storm.prototype.client.event;

import com.cristal.storm.prototype.shared.proxy.TimeEntryProxy;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * This event is launched by the Time Entry Wizard when the user creates a new time entry.
 *
 */
public class CreateTimeEntryEvent extends GwtEvent<CreateTimeEntryEvent.CreateTimeEntryHandler> {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static final Type<CreateTimeEntryHandler> TYPE = new Type<CreateTimeEntryHandler>();

    private final long VIEW_UID;
    private final TimeEntryProxy timeEntryProxy;
    
    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////
    public interface CreateTimeEntryHandler extends EventHandler {
        public void onCreateTimeEntry(CreateTimeEntryEvent event);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public CreateTimeEntryEvent(final long VIEW_UID, final TimeEntryProxy timeEntryProxy) {
        this.VIEW_UID = VIEW_UID;
        this.timeEntryProxy = timeEntryProxy;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Type<CreateTimeEntryHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(CreateTimeEntryHandler handler) {
        handler.onCreateTimeEntry(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    public static Type<CreateTimeEntryHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, final long VIEW_UID, final TimeEntryProxy timeEntryProxy) {
        source.fireEvent(new CreateTimeEntryEvent(VIEW_UID, timeEntryProxy));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    public long getVIEW_UID() {
        return VIEW_UID;
    }
    
    public TimeEntryProxy getTimeEntry() {
        return timeEntryProxy;
    }
}


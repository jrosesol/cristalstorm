/**
 *
 *
 * @author Jose Rose
 * 2011-03-17
 */
package com.cristal.storm.prototype.client.event;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * UpdateDataBindedObjectsEvent
 *
 */
public class UpdateDataBindedObjectsEvent extends GwtEvent<UpdateDataBindedObjectsEvent.UpdateDataBindedObjectsHandler> {
    
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static final Type<UpdateDataBindedObjectsHandler> TYPE = new Type<UpdateDataBindedObjectsHandler>();
    
    /**
     * TODO: Add comments for DATA_EVENT_TYPE
     *
     */
    public enum DATA_EVENT_TYPE {
        LIST_ALL_TIME_ENTRIES,
        LIST_ALL_ACCOUNTS,
        LIST_ALL_ACTIVITIES,
        REVEAL_PRESENTERS,
        DATA_IS_AVAILABLE,
        PREPARE_DATA
    };
    
    private DATA_EVENT_TYPE eventType;
    
    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////
    public interface UpdateDataBindedObjectsHandler extends EventHandler {
        public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent updateDataBindedObjectsEvent, DATA_EVENT_TYPE eventType);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public UpdateDataBindedObjectsEvent(DATA_EVENT_TYPE eventType) {
        this.eventType = eventType;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Type<UpdateDataBindedObjectsHandler> getAssociatedType() {
        return getType();
    }

    @Override
    protected void dispatch(UpdateDataBindedObjectsHandler handler) {
        handler.onUpdateDataBindedObjects(this, eventType);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    public static Type<UpdateDataBindedObjectsHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, DATA_EVENT_TYPE eventType) {
        source.fireEvent(new UpdateDataBindedObjectsEvent(eventType));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public DATA_EVENT_TYPE getEventType() {
        return eventType;
    }

}

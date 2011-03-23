/**
 *
 *
 * @author Jose Rose
 * 2011-03-17
 */
package com.cristal.storm.prototype.client.event;

import com.cristal.storm.prototype.shared.domain.MceDto;
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
    
    private MceDto eventMce; 

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////
    public interface UpdateDataBindedObjectsHandler extends EventHandler {
        public void onUpdateDataBindedObjects(UpdateDataBindedObjectsEvent event);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public UpdateDataBindedObjectsEvent(MceDto mceFromEventQueue) {
        this.eventMce = mceFromEventQueue;
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
        handler.onUpdateDataBindedObjects(this);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    public static Type<UpdateDataBindedObjectsHandler> getType() {
        return TYPE;
    }

    public static void fire(HasHandlers source, MceDto mceFromEventQueue) {
        source.fireEvent(new UpdateDataBindedObjectsEvent(mceFromEventQueue));
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    
    public MceDto getMceDto() {
        return eventMce;
    }
}

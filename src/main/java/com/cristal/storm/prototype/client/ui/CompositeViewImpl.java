/**
 *
 *
 * @author Jose Rose
 * Dec 25, 2010
 */
package com.cristal.storm.prototype.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.View;

/**
 * TODO: Add comments for CompositeViewImpl
 * 
 */
public abstract class CompositeViewImpl extends Composite implements View {
    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    public CompositeViewImpl() {

    }

    // /////////////////////////////////////////////////////////////////////////
    // Handlers
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    @Override
    @Deprecated
    public void addContent(Object slot, Widget content) {
    }

    @Override
    @Deprecated
    public void removeContent(Object slot, Widget content) {
    }

    @Override
    @Deprecated
    public void setContent(Object slot, Widget content) {
    }

    @Override
    public void addToSlot(Object slot, Widget content) {
      addContent(slot, content);
    }

    @Override
    public void removeFromSlot(Object slot, Widget content) {
      removeContent(slot, content);
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
      setContent(slot, content);
    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}

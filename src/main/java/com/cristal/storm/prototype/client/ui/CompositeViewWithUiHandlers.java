/**
 *
 *
 * @author Jose Rose
 * Dec 25, 2010
 */
package com.cristal.storm.prototype.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.View;

/**
 * TODO: Add comments for CompositeViewWithUiHandlers
 * 
 */
public abstract class CompositeViewWithUiHandlers<C extends UiHandlers> extends
        CompositeViewImpl implements HasUiHandlers<C> {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    private C uiHandlers;
    
    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    public CompositeViewWithUiHandlers() {
    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Handlers
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Access the {@link UiHandlers} associated with this {@link View}.
     * <p>
     * <b>Important!</b> Never call {@link #getUiHandlers()} inside your constructor
     * since the {@link UiHandlers} are not yet set.
     * 
     * @return The {@link UiHandlers}, or {@code null} if they are not yet set.
     */
    protected C getUiHandlers() {
      return uiHandlers;
    }

    @Override
    public void setUiHandlers(C uiHandlers) {
      this.uiHandlers = uiHandlers;
    }
    
    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}

/**
 *
 *
 * @author Jose Rose
 * Dec 15, 2010
 */
package com.cristal.storm.prototype.client.mvp.view;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * TODO: Add comments for AppStartPageUiHandlers
 *
 */
public interface AppStartPageUiHandlers extends UiHandlers{
    /**
     * Add some content when the button is clicked.
     */
    public void onContentA();
    public void onContentB();
    
    
    /**
     * When user clicks stormit.
     */
    public void onStormit();
}

/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.view;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * TODO: Add comments for TimesheetUiHandlers
 *
 */
public interface TimesheetUiHandlers extends UiHandlers {
    /**
     * Handler method to save all the time entries currently
     * displayed in the Time Entry panel.
     */
    public void saveTimeEntries();
    
    
    public void updateTimeEntriesForDateRangeChange();
}

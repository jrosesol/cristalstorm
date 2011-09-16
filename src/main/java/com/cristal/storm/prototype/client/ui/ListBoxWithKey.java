/**
 *
 *
 * @author Jose Rose
 * 2011-08-16
 */
package com.cristal.storm.prototype.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;

/**
 * TODO: Add comments for ListBoxWithValue
 *
 */
public class ListBoxWithKey extends ListBox {
    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    
    private Map<Integer, Long> keyIndexMapping;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    
    public ListBoxWithKey() {
        keyIndexMapping = new HashMap<Integer, Long>();
    }
    
    public void addItem(String item, Long keyValue) {
        keyIndexMapping.put(getItemCount(), keyValue);
        super.addItem(item);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    public Long getKeyValue() {
        return keyIndexMapping.get(getSelectedIndex());
    }
}

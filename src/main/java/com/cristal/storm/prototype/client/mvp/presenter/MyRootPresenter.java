/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.cristal.storm.prototype.client.mvp.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.RootPresenter;

public class MyRootPresenter extends RootPresenter {

    public static final class MyRootView extends RootView {
        @Override
        public void setInSlot(Object slot, Widget content) {
            if (RootPanel.get("mainContent") != null)
                RootPanel.get("mainContent").add(content);
        }
    }

    @Inject
    public MyRootPresenter(EventBus eventBus, MyRootView myRootView) {
        super(eventBus, myRootView);
    }
}

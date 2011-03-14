/**
 *
 *
 * @author Jose Rose
 * 2011-03-14
 */
package com.cristal.storm.prototype.client.mvp.view;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.HelperType;
import gwtquery.plugins.draggable.client.gwt.DraggableWidget;
import gwtquery.plugins.droppable.client.DroppableOptions.DroppableTolerance;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;
import gwtquery.plugins.droppable.client.gwt.DragAndDropCellList;
import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import com.cristal.storm.prototype.client.mvp.presenter.MceCollectionWidgetPresenter.MceCollectionWidgetViewInterface;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * MceCollectionWidget Presenter's view
 *
 */
//public class MceCollectionWidgetView extends ViewWithUiHandlers<MceCollectionWidgetUiHandlers> implements
//    MceCollectionWidgetViewInterface, HasWidgets {
public class MceCollectionWidgetView extends Composite implements
    MceCollectionWidgetViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static MceCollectionWidgetViewUiBinder uiBinder = GWT
            .create(MceCollectionWidgetViewUiBinder.class);

    /*
     * @UiField annotaded vars. can be used here from your ui.xml template
     */
    @UiField(provided = true)
    DragAndDropCellList<MCE> mceCollectionDraggable;

    private final Widget widget;
    
    private static Resources DEFAULT_RESOURCES = GWT.create(Resources.class);
    
    private List<MCE> mceListVisible;

    private SelectionModel<MCE> mceSelectionModel;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface MceCollectionWidgetViewUiBinder extends UiBinder<Widget, MceCollectionWidgetView> {
        Widget createAndBindUi(MceCollectionWidgetView mceCollectionWidgetView);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public MceCollectionWidgetView() {
        MCECell.Images images = GWT.create(MCECell.Images.class);
        MCECell textCell = new MCECell(images.icon());
        
        ProvidesKey<MCE> keyProvider = new ProvidesKey<MCE>() {
            public Object getKey(MCE item) {
                // Always do a null check.
                return (item == null) ? null : item.getURI();
            }
        };
        
        mceCollectionDraggable = new DragAndDropCellList<MCE>(textCell, DEFAULT_RESOURCES, keyProvider);
        mceSelectionModel = new SingleSelectionModel<MCE>(keyProvider);
        
        widget = uiBinder.createAndBindUi(this);
        initWidget(widget);
        
        setupWidget();
    }
    
    private void setupWidget() {

        
        mceCollectionDraggable.setSelectionModel(mceSelectionModel);

        // mceCollectionDraggable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);


        // TODO Remove this hardcoded definition of tags
        Set<String> mcetags1 = new TreeSet<String>();
        mcetags1.add("search");
        mcetags1.add("mail");
        mcetags1.add("travel");

        // TODO Remove this hardcoded definition of MCE
        MCE mce = new MCE("kayak.com", mcetags1);
        // MCE mce3 = new MCE("kayak.com", mcetags2);
        mceListVisible = new Vector<MCE>();
        mceListVisible.add(mce);
        mceCollectionDraggable.setRowData(0, mceListVisible);
        mceSelectionModel.setSelected(mce, true);

        // The cell of this CellList are only draggable
        mceCollectionDraggable.setCellDraggableOnly();

        // setup the drag operation
        DraggableOptions options = new DraggableOptions();
        // use a clone of the original cell as drag helper
        options.setHelper(HelperType.CLONE);
        // set the opacity of the drag helper
        options.setOpacity((float) 0.9);
        // append the drag helper to the body element
        options.setAppendTo("body");
        // configure the drag operations of the cell list with this options
        mceCollectionDraggable.setDraggableOptions(options);

    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public void addMceToCollection() {
        // TODO Auto-generated method stub
        
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

    
    ///////////////////////////////////////////////////////////////////////////
    // TODO: FIND WHAT TO DO WITH THIS
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void addToSlot(Object slot, Widget content) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeFromSlot(Object slot, Widget content) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setUiHandlers(MceCollectionWidgetUiHandlers uiHandlers) {
        // TODO Auto-generated method stub
        
    }
}


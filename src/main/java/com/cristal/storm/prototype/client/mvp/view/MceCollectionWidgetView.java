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
import com.cristal.storm.prototype.shared.domain.MceDto;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
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
    DragAndDropCellList<MceDto> mceCollectionDraggable;
    
    @UiField
    ShowMorePagerPanel pagerPanel;
    
    /**
     * The pager used to display the current range.
     */
    @UiField
    RangeLabelPager rangeLabelPager;

    private final Widget widget;
    
    private static Resources DEFAULT_RESOURCES = GWT.create(Resources.class);
    
    private List<MceDto> mceListVisible;

    private SelectionModel<MceDto> mceSelectionModel;
    

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
        MceCell.Images images = GWT.create(MceCell.Images.class);
        MceCell textCell = new MceCell(images.icon());
        
        ProvidesKey<MceDto> keyProvider = new ProvidesKey<MceDto>() {
            public Object getKey(MceDto item) {
                // Always do a null check.
                return (item == null) ? null : item.uri;
            }
        };
        
        mceCollectionDraggable = new DragAndDropCellList<MceDto>(textCell, DEFAULT_RESOURCES, keyProvider);
        mceSelectionModel = new SingleSelectionModel<MceDto>(keyProvider);
        
        widget = uiBinder.createAndBindUi(this);
        initWidget(widget);
        
        setupWidget();
        
    }
    
    private void setupWidget() {
        mceCollectionDraggable.setSelectionModel(mceSelectionModel);
        mceCollectionDraggable.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
        mceCollectionDraggable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);
        mceCollectionDraggable.setSelectionModel(mceSelectionModel);
        // mceCollectionDraggable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        //create a pager using the mceCollectionDraggable

        // TODO Remove this hardcoded definition of tags
        String mcetags1 = "search mail travel";

        // TODO Remove this hardcoded definition of MCE
        MceDto mce = new MceDto();
        mce.uri = "kayak.com";
        mce.tag = mcetags1;
        
        mceListVisible = new Vector<MceDto>();
        mceListVisible.add(mce);

        for (int i = 0; i < 15; i++) {
        	MceDto mceDuplicate = new MceDto();
            mce.uri = "perdu.com"+i;
            mce.tag = mcetags1;
			mceListVisible.add(mceDuplicate);
		}
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
        
        //Setup a scroll
        mceCollectionDraggable.setPageSize(30);
        pagerPanel.setDisplay(mceCollectionDraggable);
        rangeLabelPager.setDisplay(mceCollectionDraggable);

    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    
    /**
	 * The function adds an MCE to the MCE Collection. Tags are tokenized: we
	 * assume a tag is succession of alphanum chars, a dash or an underscore
	 * 
	 * @param uriText
	 * @param tagsText
	 */
	@Override
	public void addMceToCollection(String uriText, String tagsText) {
		RegExp regExp = RegExp.compile("([A-Za-z0-9_\\-]+)");
		SplitResult split = regExp.split(tagsText.toLowerCase());
		StringBuffer tags = new StringBuffer();
		for (int i = 0; i < split.length(); i++) {
			if (!split.get(i).isEmpty()) {
				tags.append(split.get(i));
			}
		}

		MceDto mce = new MceDto();
        mce.uri = uriText;
        mce.tag = tags.toString();
		mceListVisible.add(mce);
		mceCollectionDraggable.setRowData(mceListVisible);
		mceSelectionModel.setSelected(mce, true);
	}

	// /////////////////////////////////////////////////////////////////////////
	// Functions
	// /////////////////////////////////////////////////////////////////////////

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


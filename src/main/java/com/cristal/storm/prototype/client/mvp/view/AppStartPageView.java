/**
 *
 *
 * @author Jose Rose
 * Dec 15, 2010
 */
package com.cristal.storm.prototype.client.mvp.view;

import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.HelperType;
import gwtquery.plugins.draggable.client.gwt.DraggableWidget;
import gwtquery.plugins.droppable.client.DroppableOptions.DroppableTolerance;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;
import gwtquery.plugins.droppable.client.gwt.DragAndDropCellList;
import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.cristal.storm.prototype.client.mvp.presenter.AppStartPagePresenter.AppStartPageViewInterface;
import com.cristal.storm.prototype.client.ui.CommandLineBoxView;


/**
 * AppStartPage Presenter's view
 *
 */
public class AppStartPageView extends ViewWithUiHandlers<AppStartPageUiHandlers> implements
        AppStartPageViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static AppStartPageViewUiBinder uiBinder = GWT
            .create(AppStartPageViewUiBinder.class);

    /*
     * @UiField annotated vars. can be used here from your ui.xml template
     */
    
    @UiField
    public Button stormitButton;
    
    @UiField
    public TextBox uriText;
    
    @UiField
    public TextBox tagsText;
    
    @UiField(provided = true)
    public DragAndDropCellList<MCE>mceCollectionDraggable;
    
    @UiField
    public AbsolutePanel centerAbsPanel;

    private Widget widget;
    
    private List<MCE> mceListVisible;
    
    private SelectionModel<MCE> mceSelectionModel;
    

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface AppStartPageViewUiBinder extends UiBinder<Widget, AppStartPageView> {
    }
    
    private static Resources DEFAULT_RESOURCES = GWT.create(Resources.class);

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public AppStartPageView(CommandLineBoxView commandLineBox) {
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
		mceCollectionDraggable.setSelectionModel(mceSelectionModel);

           //mceCollectionDraggable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
           
           widget = uiBinder.createAndBindUi(this);
           
           HorizontalPanel horzPanel = new HorizontalPanel();
           
           
        // Push the data into the widget.
        
        //TODO Remove this hardcoded definition of tags 
        Set<String> mcetags1 = new TreeSet<String>();
        mcetags1.add("search");
        mcetags1.add("mail");
        mcetags1.add("travel");
        
        //TODO Remove this hardcoded definition of MCE 
        MCE mce = new MCE("kayak.com", mcetags1);
        //MCE mce3 = new MCE("kayak.com", mcetags2);
        mceListVisible = new Vector<MCE>();
        mceListVisible.add(mce);
        mceCollectionDraggable.setRowData(0,mceListVisible);
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
        
        
        /**
         * Create a droppable CellList
         */
        final FlowPanel aGwtPanel = new FlowPanel();
        aGwtPanel.add(new Label("test"));
        DroppableWidget<FlowPanel> droppablePanel = new DroppableWidget<FlowPanel>(aGwtPanel);
        // configure the drop behaviour (see next paragraph)
        droppablePanel.setTolerance(DroppableTolerance.POINTER);
        
        droppablePanel.getOriginalWidget();
        
        droppablePanel.addDropHandler(new DropEventHandler() {

            public void onDrop(DropEvent event) {
                // retrieve the droppable widget
                 DroppableWidget<FlowPanel> droppableLabel =
                   (DroppableWidget<FlowPanel>)event.getDroppableWidget();
                
                // retrieve the dropped draggable widget (we assume it is a
                // draggable label)
                DraggableWidget<Label> draggableLabel =
                  (DraggableWidget<Label>)event.getDraggableWidget();

                Label toto = (Label)(droppableLabel.getOriginalWidget().getWidget(0));
                Label toto2 = (Label)(droppableLabel.getOriginalWidget().getWidget(0));
                aGwtPanel.add(toto2);
                //toto.setText("Let's eat!!!!");

                // remove the draggabeLable
                //draggableLabel.removeFromParent();
                //aGwtPanel.add( new Label(event.getDraggableWidget().toString()));
                //event.getDraggableWidget().removeFromParent();
                
                aGwtPanel.add(new Label("just dragged item1"));
                aGwtPanel.add(new Label("just dragged item2"));
            }
        });
        
        
        horzPanel.add(droppablePanel);
        
        centerAbsPanel.add(horzPanel);

        //dragController.makeDraggable(horzPanel);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    @UiHandler("stormitButton")
    void onStormitButtonClicked(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onStormit();
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public String getUriText() {
        return uriText.getText();
    }

    @Override
    public String getTagsText() {
        return tagsText.getText();
    }

	/**
	 * The function adds an MCE to the MCE Collection. Tags are tokenized: we
	 * assume a tag is succession of alphanum chars, a dash or an underscore
	 * 
	 * @param uriText
	 * @param tagsText
	 */
	@Override
	public void addToMCECollection(String uriText, String tagsText) {
		RegExp regExp = RegExp.compile("([A-Za-z0-9_\\-]+)");
		SplitResult split = regExp.split(tagsText.toLowerCase());
		Set<String> tags = new TreeSet<String>();
		for (int i = 0; i < split.length(); i++) {
			if (!split.get(i).isEmpty()) {
				tags.add(split.get(i));
			}
		}
		
		MCE mce = new MCE(uriText, tags);
		int mceIndex = ((Vector<MCE>) mceListVisible).indexOf(mce);
		
		// On verifie si le MCE (identifie par son URI) est deja present
		if (mceIndex >= 0) {
			MCE existingMCE = mceListVisible.get(mceIndex);
			if (!existingMCE.getTags().equals(mce.getTags())) {
				existingMCE.setTags(tags);
			}
		} else {
			mceListVisible.add(mce);
		}
		mceCollectionDraggable.setRowData(mceListVisible);
		mceSelectionModel.setSelected(mce, true);
	}

	@Override
	public void tagCollectionFilter(final String filter) {
    	//TODO Algorithm to filter the MCE
    	//Tokenize tags
    	RegExp regExp = RegExp.compile(filter);
    	for(MCE mce : mceListVisible){
    		if(regExp.test(mce.getTags())){
    		}
    	}
    	mceCollectionDraggable.setRowData(mceListVisible);
    }
    
    
}

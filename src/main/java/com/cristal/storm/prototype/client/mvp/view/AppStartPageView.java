/**
 *
 *
 * @author Jose Rose
 * Dec 15, 2010
 */
package com.cristal.storm.prototype.client.mvp.view;

import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.HelperType;
import gwtquery.plugins.droppable.client.DroppableOptions.DroppableTolerance;
import gwtquery.plugins.droppable.client.events.DropEvent;
import gwtquery.plugins.droppable.client.events.DropEvent.DropEventHandler;
import gwtquery.plugins.droppable.client.gwt.DragAndDropCellList;
import gwtquery.plugins.droppable.client.gwt.DroppableWidget;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
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
    public final CellList<String> mceCollection;
    
    @UiField
    public AbsolutePanel centerAbsPanel;

    private final Widget widget;
    
    private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
            "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface AppStartPageViewUiBinder extends UiBinder<Widget, AppStartPageView> {
    }
    
    private static class MyTextCell extends AbstractCell<String> {

        public MyTextCell() {
        }

        @Override
        public void render(String value, Object key, SafeHtmlBuilder sb) {
          // Value can be null, so do a null check..
          if (value == null) {
            return;
          }

          sb.appendHtmlConstant("<table>");

          // Add the name and address.
          sb.appendHtmlConstant("<td style='font-size:95%;'>");
          sb.appendEscaped(value);
          sb.appendHtmlConstant("</td></tr></table>");
        }
      }
    
    private static Resources DEFAULT_RESOURCES = GWT.create(Resources.class);

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    @Inject
    public AppStartPageView(CommandLineBoxView commandLineBox) {
        widget = uiBinder.createAndBindUi(this);
        
        TextCell testCell = new TextCell();
        mceCollection = new CellList<String>(testCell);

        // create a DragController to manage drag-n-drop actions
        // note: This creates an implicit DropController for the boundary panel
        //PickupDragController dragController = new PickupDragController(centerAbsPanel, true);

        HorizontalPanel horzPanel = new HorizontalPanel();
        
        // Create a cell to render each value.
        //MyTextCell textCell = new MyTextCell();
        TextCell textCell = new TextCell();
        
        
        //CellList<String> cellList = new CellList<String>(textCell);

        DragAndDropCellList<String> dndCellList = new DragAndDropCellList<String>(textCell, DEFAULT_RESOURCES);


        dndCellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
        // Push the data into the widget.
        dndCellList.setRowData(0, DAYS);
        
        
        // The cell of this CellList are only draggable
        dndCellList.setCellDraggableOnly();
        
        // setup the drag operation
        DraggableOptions options = new DraggableOptions();
        // use a clone of the original cell as drag helper
        options.setHelper(HelperType.CLONE);
        // set the opacity of the drag helper
        options.setOpacity((float) 0.9);
        // append the drag helper to the body element
        options.setAppendTo("body");
        // configure the drag operations of the cell list with this options
        dndCellList.setDraggableOptions(options);
        
        horzPanel.add(dndCellList);
        
        /**
         * Create a droppable CellList
         */
        final FlowPanel aGwtPanel = new FlowPanel();
        aGwtPanel.add(new Label("test"));
        DroppableWidget<FlowPanel> droppablePanel = new DroppableWidget<FlowPanel>(aGwtPanel);
        // configure the drop behaviour (see next paragraph)
        droppablePanel.setTolerance(DroppableTolerance.POINTER);
        
        droppablePanel.addDropHandler(new DropEventHandler() {

            public void onDrop(DropEvent event) {
                // retrieve the droppable widget
                // DroppableWidget<Label> droppableLabel =
                // (DroppableWidget<Label>)event.getDroppableWidget();
                // retrieve the dropped draggable widget (we assume it is a
                // draggable label)
                // DraggableWidget<Label> draggableLabel =
                // (DraggableWidget<Label>)event.getDraggableWidget();

                // droppableLabel.getOriginalWidget().setText("I ate : "+draggableLabel.getOriginalWidget().getText());

                // remove the draggabeLable
                // draggableLabel.removeFromParent();
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
                aGwtPanel.add(new Label("just dragged item"));
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

    @Override
    public void addToUriStack(String uriText) {
    }
}

/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.cristal.storm.prototype.client.mvp.view;

import java.util.Arrays;
import java.util.List;

import com.cristal.storm.prototype.client.mvp.presenter.TimesheetCellListPresenter.TimesheetCellListViewInterface;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * TimesheetCellList Presenter's view
 * 
 */
public class TimesheetCellListView extends ViewWithUiHandlers<TimesheetCellListUiHandlers> implements
        TimesheetCellListViewInterface {

    // /////////////////////////////////////////////////////////////////////////
    // Members
    // /////////////////////////////////////////////////////////////////////////
    private static TimesheetCellListViewUiBinder uiBinder = GWT.create(TimesheetCellListViewUiBinder.class);

    /*
     * @UiField annotaded vars. can be used here from your ui.xml template
     */
    // @UiField
    // SimplePanel simplePanel;

    @UiField(provided = true)
    public CellList<String> timesheetCellList;

    private final Widget widget;
    
    /**
     * The list of data to display.
     */
    private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
        "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    interface TimesheetCellListViewUiBinder extends UiBinder<Widget, TimesheetCellListView> {
    }

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public TimesheetCellListView() {

        // Create a cell to render each value.
        TextCell textCell = new TextCell();

        // Create a CellList that uses the cell.
        timesheetCellList = new CellList<String>(textCell);

        // Has to be at the beginning
        widget = uiBinder.createAndBindUi(this);

        // /// INIT //////
        timesheetCellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

        // Add a selection model to handle user selection.
        final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
        timesheetCellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                String selected = selectionModel.getSelectedObject();
                if (selected != null) {
                    Window.alert("You selected: " + selected);
                }
            }
        });
        
        // Set the total row count. This isn't strictly necessary, but it affects
        // paging calculations, so its good habit to keep the row count up to date.
        timesheetCellList.setRowCount(DAYS.size(), true);

        // Push the data into the widget.
        timesheetCellList.setRowData(0, DAYS);

    }

    // /////////////////////////////////////////////////////////////////////////
    // Overrides
    // /////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Functions
    // /////////////////////////////////////////////////////////////////////////

    // /////////////////////////////////////////////////////////////////////////
    // Get / Set
    // /////////////////////////////////////////////////////////////////////////

}

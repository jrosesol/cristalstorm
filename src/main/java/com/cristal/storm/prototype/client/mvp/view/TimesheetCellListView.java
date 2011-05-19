/**
 *
 *
 * @author Jose Rose
 * 2011-04-29
 */
package com.cristal.storm.prototype.client.mvp.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.cristal.storm.prototype.client.mvp.presenter.TimesheetCellListPresenter.TimesheetCellListViewInterface;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
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
    public CellTable<TimeEntry> timesheetCellList;

    /**
     * The pager used to change the range of data.
     */
    @UiField(provided = true)
    SimplePager pager;

    private final Widget widget;

    /**
     * The list of data to display.
     */
    private static final List<String> DAYS = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                                                           "Friday", "Saturday");

    String[] catNames = {"a", "b"};

    // /////////////////////////////////////////////////////////////////////////
    // Interfaces
    // /////////////////////////////////////////////////////////////////////////

    interface TimesheetCellListViewUiBinder extends UiBinder<Widget, TimesheetCellListView> {
    }

    /**
     * Information about a contact.
     */
    public static class TimeEntry implements Comparable<TimeEntry> {

        /**
         * The key provider that provides the unique ID of a contact.
         */
        public static final ProvidesKey<TimeEntry> KEY_PROVIDER = new ProvidesKey<TimeEntry>() {
            public Object getKey(TimeEntry item) {
                return item == null ? null : item.getId();
            }
        };

        private static int nextId = 0;

        private String hours;
        private Category category;
        private final int id;

        public TimeEntry() {
            this.id = nextId;
            nextId++;

            hours = "";
            category = new Category("");
        }

        public int compareTo(TimeEntry o) {
            return (o == null || o.hours == null) ? -1 : -o.hours.compareTo(hours);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof TimeEntry) {
                return id == ((TimeEntry) o).id;
            }
            return false;
        }

        /**
         * @return the
         */
        public String getHours() {
            return hours;
        }

        public Category getCategory() {
            return category;
        }

        /**
         * @return the unique ID of the contact
         */
        public int getId() {
            return this.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        /**
         * Set the
         * 
         * @param hours
         *            the hours
         */
        public void setHours(String hours) {
            this.hours = hours;
        }

        public void setCategory(Category category) {
            assert category != null : "category cannot be null";
            this.category = category;
        }

    }

    /**
     * The provider that holds the list of contacts in the database.
     */
    private ListDataProvider<TimeEntry> dataProvider = new ListDataProvider<TimeEntry>();

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    @Inject
    public TimesheetCellListView() {

        // Create a cell to render each value.
        TextCell textCell = new TextCell();

        // Create a CellList that uses the cell.
        timesheetCellList = new CellTable<TimeEntry>(TimeEntry.KEY_PROVIDER);

        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(timesheetCellList);

        // Has to be at the beginning
        widget = uiBinder.createAndBindUi(this);

        // /// INIT //////
        timesheetCellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

        // Initialize the categories.
        categories = new Category[catNames.length];
        for (int i = 0; i < catNames.length; i++) {
            categories[i] = new Category(catNames[i]);
        }

        // Add a selection model so we can select cells.
        // final SelectionModel<TimeEntry> selectionModel = new
        // MultiSelectionModel<TimeEntry>(
        // TimeEntry.KEY_PROVIDER);
        // timesheetCellList.setSelectionModel(selectionModel,
        // DefaultSelectionEventManager.<TimeEntry> createCheckboxManager());

        // Add a selection model to handle user selection.
        final SingleSelectionModel<TimeEntry> selectionModel = new SingleSelectionModel<TimeEntry>();
        timesheetCellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            public void onSelectionChange(SelectionChangeEvent event) {
                // String selected = selectionModel.getSelectedObject();
            }
        });

        // Attach a column sort handler to the ListDataProvider to sort the
        // list.
        // ListHandler<TimeEntry> sortHandler = new ListHandler<TimeEntry>(
        // ContactDatabase.get().getDataProvider().getList());
        // cellTable.addColumnSortHandler(sortHandler);

        // Initialize the columns
        initTableColumns(selectionModel);

        // Push the data into the widget.
        // timesheetCellList.setRowData(0, DAYS);
        List<TimeEntry> aDataList = new ArrayList<TimeEntry>();
        aDataList.add(new TimeEntry());
        aDataList.get(0).setHours("2h");
        dataProvider.setList(aDataList);

        // Set the total row count. This isn't strictly necessary, but it
        // affects
        // paging calculations, so its good habit to keep the row count up to
        // date.
        timesheetCellList.setRowCount(aDataList.size(), true);

        //
        addDataDisplay(timesheetCellList);
    }

    /**
     * Add a display to the database. The current range of interest of the
     * display will be populated with data.
     * 
     * @param display
     *            a {@Link HasData}.
     */
    public void addDataDisplay(HasData<TimeEntry> display) {
        dataProvider.addDataDisplay(display);
    }

    /**
     * A contact category.
     */
    public static class Category {

        private final String displayName;

        private Category(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private final Category[] categories;

    /**
     * The constants used in this Content Widget.
     */
    static interface DatabaseConstants extends Constants {
        String[] contactDatabaseCategories();
    }

    /**
     * Add the columns to the table.
     */
    private void initTableColumns(final SelectionModel<TimeEntry> selectionModel/*
                                                                                 * ,
                                                                                 * ListHandler
                                                                                 * <
                                                                                 * TimeEntry
                                                                                 * >
                                                                                 * sortHandler
                                                                                 */) {

        // First name.
        Column<TimeEntry, String> firstNameColumn = new Column<TimeEntry, String>(new EditTextCell()) {
            @Override
            public String getValue(TimeEntry object) {
                return object.getHours();
            }
        };
        // firstNameColumn.setSortable(true);
        // sortHandler.setComparator(firstNameColumn, new
        // Comparator<TimeEntry>() {
        // public int compare(TimeEntry o1, TimeEntry o2) {
        // return o1.getHours().compareTo(o2.getHours());
        // }
        // });
        timesheetCellList.addColumn(firstNameColumn, "Hours");
        firstNameColumn.setFieldUpdater(new FieldUpdater<TimeEntry, String>() {
            public void update(int index, TimeEntry object, String value) {
                // Called when the user changes the value.
                object.setHours(value);
                dataProvider.refresh();
            }
        });
        timesheetCellList.setColumnWidth(firstNameColumn, 20, Unit.PCT);

        // Category.
        final Category[] categories = this.categories;
        List<String> categoryNames = new ArrayList<String>();
        for (Category category : categories) {
            categoryNames.add(category.getDisplayName());
        }
        SelectionCell categoryCell = new SelectionCell(categoryNames);
        Column<TimeEntry, String> categoryColumn = new Column<TimeEntry, String>(categoryCell) {
            @Override
            public String getValue(TimeEntry object) {
                return object.getCategory().getDisplayName();
            }
        };
        timesheetCellList.addColumn(categoryColumn, "Project");
        categoryColumn.setFieldUpdater(new FieldUpdater<TimeEntry, String>() {
            public void update(int index, TimeEntry object, String value) {
                for (Category category : categories) {
                    if (category.getDisplayName().equals(value)) {
                        object.setCategory(category);
                    }
                }
                dataProvider.refresh();
            }
        });
        timesheetCellList.setColumnWidth(categoryColumn, 130, Unit.PX);
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

/**
 *
 *
 * @author Jose Rose
 * 2011-04-25
 */
package com.cristal.storm.prototype.client.mvp.view;

import java.util.Calendar;
import java.util.Date;

import com.cristal.storm.prototype.client.controller.DataStoreProxy;
import com.cristal.storm.prototype.client.i18n.AppsConstants;
import com.cristal.storm.prototype.client.mvp.presenter.TimesheetPresenter.TimesheetViewInterface;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetPresenter.ActivityCalendarWidgetViewInterface;
import com.cristal.storm.prototype.client.ui.ActivityCalendarWidgetView;
import com.cristal.storm.prototype.client.ui.DroppablePanel;
import com.cristal.storm.prototype.client.util.Resources;
import com.cristal.storm.prototype.shared.service.CommandWatchDog;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * Timesheet Presenter's view
 *
 */
public class TimesheetView extends ViewWithUiHandlers<TimesheetUiHandlers> implements
        TimesheetViewInterface {

    ///////////////////////////////////////////////////////////////////////////
    // Members
    ///////////////////////////////////////////////////////////////////////////
    private static TimesheetViewUiBinder uiBinder = GWT
            .create(TimesheetViewUiBinder.class);

    /*
     * @UiField annotated vars. can be used here from your ui.xml template
     */

    @UiField
    HorizontalPanel dayColumns;
    @UiField
    Button saveActivity;
    @UiField
    DateBox beginDateBoxPicker;
    @UiField
    DateBox endDateBoxPicker;
    @UiField
    Label lblPeriods;
    @UiField
    Label lblTo;

    private final Widget widget;

    ///////////////////////////////////////////////////////////////////////////
    // Interfaces
    ///////////////////////////////////////////////////////////////////////////

    interface TimesheetViewUiBinder extends UiBinder<Widget, TimesheetView> {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public TimesheetView() {
        widget = uiBinder.createAndBindUi(this);   
        
        // Set the correct language
        AppsConstants lConstants = (AppsConstants) GWT.create(AppsConstants.class);
        lblPeriods.setText(lConstants.periods() + ":");
        saveActivity.setText(lConstants.save());
        lblTo.setText(lConstants.to());
        

        // Set the default date format
        beginDateBoxPicker.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("E, dd/MM/yyyy, HH:mm:ss")));
        endDateBoxPicker.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getFormat("E, dd/MM/yyyy, HH:mm:ss")));
        beginDateBoxPicker.setValue(new Date(System.currentTimeMillis()));
        endDateBoxPicker.setValue(new Date(System.currentTimeMillis()));
        
        beginDateBoxPicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                updateDateBox(false);
                getUiHandlers().updateTimeEntriesForDateRangeChange();
            }
        });
        
        endDateBoxPicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                updateDateBox(true);
                getUiHandlers().updateTimeEntriesForDateRangeChange();
            }
        });
        
        updateDateBox(false);
    }

    private void updateDateBox(Boolean reverseOrder) {
        Date startOfWeek = null;
        Date endOfWeek = null;
        
        if (reverseOrder == true) {
            startOfWeek = endDateBoxPicker.getValue();
            endOfWeek = beginDateBoxPicker.getValue();
        }
        else {
            startOfWeek = beginDateBoxPicker.getValue();
            endOfWeek = endDateBoxPicker.getValue();
        }
        
        // Set the date range
        computeWeekRange(startOfWeek, endOfWeek);
        
        beginDateBoxPicker.setValue(startOfWeek);
        endDateBoxPicker.setValue(endOfWeek);
    }

    private void computeWeekRange(Date startOfWeek, Date endOfWeek) {
        //
        int firstDayOfWeek = 1; /*Monday*/
        
        // Set the date from Monday through Sunday for the current week
        int curDayOfWeek = startOfWeek.getDay();
                
        if (curDayOfWeek == 0) {
            curDayOfWeek = 7; // To wrap the week
        }

        CalendarUtil.addDaysToDate(startOfWeek, (firstDayOfWeek - curDayOfWeek));
        startOfWeek.setHours(0);
        startOfWeek.setMinutes(0);
        startOfWeek.setSeconds(0);
        
        // Compute the last day of the week
        endOfWeek.setTime(startOfWeek.getTime());
        CalendarUtil.addDaysToDate(endOfWeek, (startOfWeek.getDay() + 5));
        endOfWeek.setHours(23);
        endOfWeek.setMinutes(59);
        endOfWeek.setSeconds(59);

    }

    ///////////////////////////////////////////////////////////////////////////
    // Overrides
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public void setHandlers(final EventBus eventBus, final CommandWatchDog commandWatchDog) {
//        monday.setHandlers(eventBus, commandWatchDog);
//        thuesday.setHandlers(eventBus, commandWatchDog);
//        wednesday.setHandlers(eventBus, commandWatchDog);
//        thursday.setHandlers(eventBus, commandWatchDog);
//        friday.setHandlers(eventBus, commandWatchDog);
//        saturday.setHandlers(eventBus, commandWatchDog);
//        sunday.setHandlers(eventBus, commandWatchDog);
    }

    @Override
    public void setActivityCalendarWidget(ActivityCalendarWidgetViewInterface activityCalendar) {
        dayColumns.add((ActivityCalendarWidgetView)activityCalendar);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Functions
    ///////////////////////////////////////////////////////////////////////////
    
    @UiHandler("saveActivity")
    void handleSaveClick(ClickEvent e) {
        
    }

    @Override
    public Date getStartWeekTime() {
        return beginDateBoxPicker.getValue();
    }

    @Override
    public Date getEndWeekTime() {
        return endDateBoxPicker.getValue();
    }
    
//    @UiHandler("beginDateBoxPicker")
//    void handleStartDateChange(ValueChangeHandler<Date> e) {
//        updateDateBox();
//    }
//    
//    @UiHandler("endDateBoxPicker")
//    void handleEndDateChange(ValueChangeHandler<Date> e) {
//        updateDateBox();
//    }

    ///////////////////////////////////////////////////////////////////////////
    // Get / Set
    ///////////////////////////////////////////////////////////////////////////

}


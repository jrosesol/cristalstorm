/**
 * Copyright 2010 ArcBees Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
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

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList.Resources;
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

import com.cristal.storm.prototype.client.mvp.presenter.MainPagePresenter;

/**
 * @author Philippe Beaudoin
 */
public class MainPageView extends ViewWithUiHandlers<MainPageUiHandlers>
        implements MainPagePresenter.MainPageViewInterface {

    private static MainPageViewUiBinder uiBinder = GWT
            .create(MainPageViewUiBinder.class);

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
    public DragAndDropCellList<MCE> mceCollectionDraggable;

    @UiField
    public AbsolutePanel centerAbsPanel;

    private Widget widget;

    private List<MCE> mceListVisible;

    private SelectionModel<MCE> mceSelectionModel;

    private static Resources DEFAULT_RESOURCES = GWT.create(Resources.class);

    interface MainPageViewUiBinder extends UiBinder<Widget, MainPageView> {
        Widget createAndBindUi(MainPageView mainPageView);
    }

    // ////////// TEST - GWT CANVAS ////////////////////
    Canvas canvas;
    Canvas backBuffer;
    
    // canvas size, in px
    static final int height = 400;
    static final int width = 400;
    
    Context2d context;
    
    static final String holderId = "canvasholder";
    // /////////////////////////////////////////////////

    @Inject
    public MainPageView() {
        MCECell.Images images = GWT.create(MCECell.Images.class);

        MCECell textCell = new MCECell(images.icon());

        ProvidesKey<MCE> keyProvider = new ProvidesKey<MCE>() {
            public Object getKey(MCE item) {
                // Always do a null check.
                return (item == null) ? null : item.getURI();
            }
        };

        mceCollectionDraggable = new DragAndDropCellList<MCE>(textCell,
                DEFAULT_RESOURCES, keyProvider);

        mceSelectionModel = new SingleSelectionModel<MCE>(keyProvider);
        mceCollectionDraggable.setSelectionModel(mceSelectionModel);

        // mceCollectionDraggable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

        widget = uiBinder.createAndBindUi(this);
        
        
        // ////////// TEST - GWT CANVAS ////////////////////
        canvas = Canvas.createIfSupported();
        
        // init the canvases
        canvas.setWidth(width + "px");
        canvas.setHeight(height + "px");
        canvas.setCoordinateSpaceWidth(width);
        canvas.setCoordinateSpaceHeight(height);
        context = canvas.getContext2d();
        
        context.setFillStyle("#ed9d33");
        context.beginPath();
        context.arc(202.0, 78.0, 10.0, 0.0, Math.PI * 2.0, true);
        context.closePath();
        context.fill();
        
        // /////////////////////////////////////////////////
        

        HorizontalPanel horzPanel = new HorizontalPanel();

        // Push the data into the widget.

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

        /**
         * Create a droppable CellList
         */
        DroppableWidget<Canvas> droppablePanel = new DroppableWidget<Canvas>(canvas);
        // configure the drop behaviour (see next paragraph)
        droppablePanel.setTolerance(DroppableTolerance.POINTER);

        droppablePanel.getOriginalWidget();

        droppablePanel.addDropHandler(new DropEventHandler() {

            private double startX = 202.0 - 10.0;
            public void onDrop(DropEvent event) {
/*                // retrieve the droppable widget
                DroppableWidget<FlowPanel> droppableLabel = (DroppableWidget<FlowPanel>) event
                        .getDroppableWidget();

                // retrieve the dropped draggable widget (we assume it is a
                // draggable label)
                DraggableWidget<Label> draggableLabel = (DraggableWidget<Label>) event
                        .getDraggableWidget();

                Label toto = (Label) (droppableLabel.getOriginalWidget()
                        .getWidget(0));
                Label toto2 = (Label) (droppableLabel.getOriginalWidget()
                        .getWidget(0));
                aGwtPanel.add(toto2);
                // toto.setText("Let's eat!!!!");

                // remove the draggabeLable
                // draggableLabel.removeFromParent();
                // aGwtPanel.add( new
                // Label(event.getDraggableWidget().toString()));
                // event.getDraggableWidget().removeFromParent();

                aGwtPanel.add(new Label("just dragged item1"));
                aGwtPanel.add(new Label("just dragged item2"));*/
                
                context.setFillStyle("#ed9d33");
                context.beginPath();
                context.arc(startX, 78.0, 10.0, 0.0, Math.PI * 2.0, true);
                context.closePath();
                context.fill();
                
                startX -= 10.0;
            }
        });

        horzPanel.add(droppablePanel);

        centerAbsPanel.add(horzPanel);

    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @UiHandler("stormitButton")
    void onStormitButtonClicked(ClickEvent event) {
        if (getUiHandlers() != null) {
            getUiHandlers().onStormit();
        }
    }

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
        // TODO Algorithm to filter the MCE
        // Tokenize tags
        RegExp regExp = RegExp.compile(filter);
        for (MCE mce : mceListVisible) {
            if (regExp.test(mce.getTags())) {
            }
        }
        mceCollectionDraggable.setRowData(mceListVisible);
    }
}

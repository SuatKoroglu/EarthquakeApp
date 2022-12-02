package com.example.application.views.earthquakes;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.application.data.entity.Earthquake;
import com.example.application.data.repository.EarthquakeRepository;
import com.example.application.data.service.EarthquakeService;
import com.example.application.data.service.services;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.html.H2;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Earthquakes")
@Route(value = "Earthquakes")
@RouteAlias(value = "")
public class EarthquakesView extends VerticalLayout {
    Grid<Earthquake> grid = new Grid<>(Earthquake.class, false);
    TextField filterText= new TextField();
    Filterform form;
    EarthquakeService service;

    public EarthquakesView(EarthquakeService service) {
        this.service=service;
        //I have give a classname for using ccs later
        addClassName("Earthquake-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(
            getToolbar(),
            getContent()
        );
        updatelist();  
    }
    //update the grid with by the given parameters in textfield
    void updatelist() {
        grid.setItems(service.findallearthquakes(filterText.getValue()));
    }
    //the contets is here
    private Component getContent() {
        HorizontalLayout content= new HorizontalLayout(grid,form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }
    //contents are put together here for clean coding
    private void configureForm() {
        form= new Filterform(Collections.emptyList());
        form.setWidth("25em");
    }
    //the objects in toolbar are put together here for clean coding
    private Component getToolbar() {
        filterText.setPlaceholder("Filter by name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e-> updatelist());

        Button filterbutton =new Button("Filters");
        HorizontalLayout toolbar= new HorizontalLayout(filterText, filterbutton);
        toolbar.addClassName("toolbar");
        filterbutton.addClickListener(e-> updatelist());

        return toolbar;
    }

 
    //Creating a grid with 3 parameters
    private void configureGrid() {

        grid.addClassName("Earthquake-grid");
        grid.setSizeFull();
        grid.addColumn(Earthquake -> Earthquake.getPlace()).setHeader("Country/Place");
        grid.addColumn(Earthquake -> Earthquake.getMagnitude()).setHeader("Magnitude");
        grid.addColumn(Earthquake -> Earthquake.getDate()).setHeader("Date/Time");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
    }

}

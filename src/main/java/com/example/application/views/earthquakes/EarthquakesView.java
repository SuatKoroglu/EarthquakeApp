package com.example.application.views.earthquakes;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.application.data.entity.Earthquake;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
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

    public EarthquakesView() {

        addClassName("Earthquake-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(
            getToolbar(),
            getContent()
        );
    }

    private Component getContent() {
        HorizontalLayout content= new HorizontalLayout(grid,form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form= new Filterform(Collections.emptyList());
        form.setWidth("25em");
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Filter By Country...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        
        Button filterbutton =new Button("Filters");
        HorizontalLayout toolbar= new HorizontalLayout(filterText, filterbutton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void configureGrid() {

        grid.addClassName("Earthquake-grid");
        grid.setSizeFull();
        grid.addColumn(Earthquake -> Earthquake.getCountry()).setHeader("Country");
        grid.addColumn(Earthquake -> Earthquake.getPlace()).setHeader("Place");
        grid.addColumn(Earthquake -> Earthquake.getMagnitude()).setHeader("Magnitude");
        grid.addColumn(Earthquake -> Earthquake.getDate()).setHeader("Date");
        grid.addColumn(Earthquake -> Earthquake.getTime()).setHeader("Time");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}

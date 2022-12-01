package com.example.application.views.earthquakes;

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

    public EarthquakesView() {
        addClassName("Earthquake-view");
        setSizeFull();
        configureGrid();

        add(
            getToolbar(),
            grid
        );
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

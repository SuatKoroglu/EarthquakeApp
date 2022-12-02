package com.example.application.views.earthquakes;

import java.util.List;

import org.springframework.context.annotation.Primary;

import com.example.application.data.entity.Earthquake;
import com.example.application.views.earthquakes.Filterform.FilterformEvent.CloseEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class Filterform extends FormLayout {
    
    ComboBox<Earthquake> box= new ComboBox<>("Country");
    TextField days= new TextField("Days");
    Button save= new Button("Save");
    Button cancel = new Button("Cancel");
    
    public Filterform(List<Earthquake> countrys){
        addClassName("filter-form");
        

        add(
            box,
            days,
            createButtonLayout()
        );
    }
    //save and cancel buttons and their settings
    private Component createButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        
        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);
        cancel.addBlurListener(event->fireEvent(new CloseEvent(this)) );
        return new HorizontalLayout(save,cancel);
    }

   // Events
  public static abstract class FilterformEvent extends ComponentEvent<Filterform> {
    private Earthquake earthquake;

    protected FilterformEvent(Filterform source, Earthquake earthquake) {
      super(source, false);
      
    }

    

  

  

    public static class CloseEvent extends FilterformEvent {
        CloseEvent(Filterform source) {
        super(source, null);
    }
  }

 
    }
}

package com.bpb.book.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("radiobutton")
public class ExampleRadioButton extends VerticalLayout {

    public ExampleRadioButton() {

        TextField message = new TextField();
        message.setReadOnly(true);
        message.setSizeFull();
        RadioButtonGroup<String> group = new RadioButtonGroup<>();
        group.setLabel("Gender");
        group.setItems("Male", "Female", "Not Defined");
        group.addValueChangeListener(event -> message.setValue(String.format(
                "Select group value to '%s'", event.getValue())));
        add(group,message);

    }
}

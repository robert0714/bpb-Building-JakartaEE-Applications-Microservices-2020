package com.bpb.book.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("textfield")
public class ExampleTextField extends VerticalLayout{

    public ExampleTextField() {

        TextField textField = new TextField();
        textField.setLabel("Name");
        textField.setValue("Geovanny");
        add(textField);
    }

}

package com.bpb.book.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.Route;

@Route("numberfield")
public class ExampleNumberField extends VerticalLayout{

    public ExampleNumberField() {

        NumberField numberField = new NumberField("Age of Birth");
        NumberField numberField2 = new NumberField("Value from 1 to 10");
        numberField2.setValue(1d);
        numberField2.setHasControls(true);
        numberField2.setMin(1);
        numberField2.setMax(10);
        add(numberField,numberField2);
    }
}

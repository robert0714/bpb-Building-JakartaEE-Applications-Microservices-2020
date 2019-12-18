package com.bpb.book.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.checkbox.Checkbox;


@Route("checkbox")
public class ExampleCheckBox extends VerticalLayout {

    public ExampleCheckBox() {

        Checkbox checkbox1 = new Checkbox("Example Checkbox 1");
        Checkbox checkbox2 = new Checkbox("Example Checkbox 2");
        Checkbox checkbox3 = new Checkbox("Example Checkbox 3");
        Checkbox checkbox4 = new Checkbox("Example Checkbox 4");

        checkbox2.setValue(true);

        checkbox1.addValueChangeListener(event ->
                checkbox2.setValue(! checkbox1.getValue()));
        add(checkbox1, checkbox2, checkbox3, checkbox4);

    }

}

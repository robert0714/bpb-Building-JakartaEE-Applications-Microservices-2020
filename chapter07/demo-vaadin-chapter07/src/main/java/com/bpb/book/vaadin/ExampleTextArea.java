package com.bpb.book.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;


@Route("textarea")
public class ExampleTextArea extends VerticalLayout {

    public ExampleTextArea() {

        TextArea area = new TextArea("Example TextArea");
        area.setValue("One row\n"+
                "Two rows\n"+
                "Three rows");
        add(area);
    }
}

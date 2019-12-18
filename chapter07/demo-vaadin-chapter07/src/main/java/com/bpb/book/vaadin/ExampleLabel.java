package com.bpb.book.vaadin;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("label")
public class ExampleLabel extends VerticalLayout {

    public ExampleLabel(){

        Label label = new Label("Example Label");
        add(label);

    }
}

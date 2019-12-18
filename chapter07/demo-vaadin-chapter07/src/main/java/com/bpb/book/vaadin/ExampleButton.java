package com.bpb.book.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("button")
public class ExampleButton extends VerticalLayout {

    public ExampleButton() {

        Button button = new Button("Example Button");
        button.addClickListener(clickEvent ->
                Notification.show("Hello, this button"));
        add(button);

    }
}

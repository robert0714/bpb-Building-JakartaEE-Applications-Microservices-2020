package com.bpb.book.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("notification")
public class ExampleNotification extends VerticalLayout {

    public ExampleNotification(){
        Button button = new Button("Example Notification");
        Label content = new Label(
            "Hello, Testing with notification!");
        NativeButton buttonInside = new NativeButton("Bye");
        Notification notification = new Notification(content, buttonInside);
        notification.setDuration(5000);
        buttonInside.addClickListener(event -> notification.close());
        notification.setPosition(Notification.Position.MIDDLE);
        button.addClickListener(event -> notification.open());
        add(button);
    }
}

package com.bpb.book.vaadin;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route("applayout")
public class ExampleAppLayout extends AppLayout {
    public ExampleAppLayout() {
        Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
        Tabs tabs = new Tabs(new Tab("Home"),new Tab("Product"), new Tab("About"));
        img.setHeight("50px");
        addToNavbar(img, tabs);
    }
}

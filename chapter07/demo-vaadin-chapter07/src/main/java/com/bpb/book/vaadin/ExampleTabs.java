package com.bpb.book.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("tabs")
public class ExampleTabs extends VerticalLayout {

    public ExampleTabs() {

        Tab tab1 = new Tab("Tab Panama");
        Div page1 = new Div();
        page1.setText("Page#Aristides");
        Tab tab2 = new Tab("Tab Colombia");
        Div page2 = new Div();
        page2.setText("Page#Geovanny");
        page2.setVisible(false);
        Tab tab3 = new Tab("Tab Brasil");
        Div page3 = new Div();
        page3.setText("Page#Octavio");
        page3.setVisible(false);
        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        Tabs tabs = new Tabs(tab1, tab2, tab3);
        Div pages = new Div(page1, page2, page3);
        Set<Component> pagesShown = Stream.of(page1)
                .collect(Collectors.toSet());
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.addSelectedChangeListener(event -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });
        add(tabs,pages);
    }
}

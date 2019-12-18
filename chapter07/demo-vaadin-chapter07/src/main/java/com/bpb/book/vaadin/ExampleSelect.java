package com.bpb.book.vaadin;


import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;

@Route("select")
public class ExampleSelect extends VerticalLayout{

    public ExampleSelect() {

        Select<String> select = new Select<>();
        select.setLabel("Language");
        select.setItems("Java", "Scala", "Kotlin");

        Div value = new Div();
        value.setText("Select a Language");
        select.addValueChangeListener(
                event -> value.setText("Selected: " + event.getValue()));
        add(select, value);
    }

}

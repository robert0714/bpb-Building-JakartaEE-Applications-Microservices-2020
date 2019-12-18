package com.bpb.book.vaadin;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("combobox")
public class ExampleComboBox extends VerticalLayout {

    public ExampleComboBox() {

        ComboBox<String> country = new ComboBox<>("My Select");
        country.setItems("Brasil", "Colombia", "Panama", "India");
        country.setAllowCustomValue(true);
        add(country);

    }
}

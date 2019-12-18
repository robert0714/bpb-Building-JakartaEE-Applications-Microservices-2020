package com.bpb.book.vaadin;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.router.Route;

import java.util.Locale;

@Route("datepicker")
public class ExampleDatePicker extends VerticalLayout{

    public ExampleDatePicker() {

        DatePicker datePicker = new DatePicker();
        datePicker.setLabel("Start Date");
        datePicker.addValueChangeListener(
                event -> datePicker.setLocale(Locale.UK));
        add(datePicker);
    }
}

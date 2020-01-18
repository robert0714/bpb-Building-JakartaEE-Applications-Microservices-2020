package com.bpb.book.vaadin;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("accordion")
public class ExampleAccordion extends VerticalLayout {

    public ExampleAccordion()
    {
        Accordion accordion = new Accordion();
        VerticalLayout personalInformationLayout = new VerticalLayout();
        personalInformationLayout.add(
                new TextField("Name"),
                new TextField("Document")
        );
        accordion.add("Personal Information", personalInformationLayout);
        VerticalLayout healthInformationLayout = new VerticalLayout();
        healthInformationLayout.add(
                new TextField("Eps"),
                new TextField("City")
        );
        accordion.add("Health Information", healthInformationLayout);
        add(accordion);
    }
}

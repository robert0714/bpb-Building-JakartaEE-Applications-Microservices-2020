package com.bpb.book.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.Route;

@Route("emailfield")
public class ExampleEmailField extends VerticalLayout{

    public ExampleEmailField() {

        EmailField emailField = new EmailField("Email");
        emailField.setClearButtonVisible(true);
        emailField.setErrorMessage("Enter a valid email address");
        add(emailField);
    }
}

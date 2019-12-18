package com.bpb.book.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("passwordfield")
public class ExamplePasswordField extends VerticalLayout{

    public ExamplePasswordField() {

        TextField textField = new TextField();
        textField.setLabel("User");
        textField.setValue("Geovanny");
        PasswordField passwordField = new PasswordField();
        passwordField.setLabel("Password");
        passwordField.setPlaceholder("Enter password");
        passwordField.setValue("12345678");
        add(textField,passwordField);

    }
}

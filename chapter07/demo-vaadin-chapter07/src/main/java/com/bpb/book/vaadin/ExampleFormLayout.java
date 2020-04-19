package com.bpb.book.vaadin;


import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("formlayout")
public class ExampleFormLayout extends VerticalLayout {

    public ExampleFormLayout(){

    FormLayout formLayout = new FormLayout();
    TextField firstName = new TextField();
    firstName.setValueChangeMode(ValueChangeMode.EAGER);
    TextField lastName = new TextField();
    lastName.setValueChangeMode(ValueChangeMode.EAGER);
    TextField phone = new TextField();
    phone.setValueChangeMode(ValueChangeMode.EAGER);
    DatePicker birthDate = new DatePicker();
    ComboBox<String> gender = new ComboBox<>();
        gender.setItems("Male", "Female", "Not Defined");
        gender.setAllowCustomValue(true);
    Label infoLabel = new Label();
    NativeButton save = new NativeButton("Save");
    NativeButton reset = new NativeButton("Reset");
    formLayout.addFormItem(firstName, "First name");
    formLayout.addFormItem(lastName, "Last name");
    formLayout.addFormItem(birthDate, "Birthdate");
    formLayout.addFormItem(gender, "Gender");
    HorizontalLayout actions = new HorizontalLayout();
    actions.add(save,reset);
    add(formLayout,infoLabel,actions);
    }
}

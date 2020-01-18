package com.bpb.book.vaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("grid")
public class ExampleGrid extends VerticalLayout{

    public ExampleGrid() {

        TextField firstName = new TextField();

        Binder<Person> binder = new Binder(Person.class);
        binder.bind(firstName, Person::getFirstName, Person::setFirstName);

        Binder<Person> binder1 = new Binder();
        binder1.bind(firstName,"title");

        Binder<Person> binder2 = new Binder();
        binder2.bind(firstName, Person::getFirstName, Person::setFirstName);

        Binder<Person> binder4 = new Binder(Person.class);
        binder4.bind(firstName,"title");




        List<Person> personList = new ArrayList<>();
        personList.add(new Person(100, "Aristides", "Villareal", 40,"128-952-267"));
        personList.add(new Person(101, "Octavio", "Goncalves", 28,"942-227-127"));
        personList.add(new Person(102, "Geovanny", "Mendoza", 23,"237-265-337"));
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(personList);
        grid.removeColumnByKey("idPerson");
        grid.setColumns("firstName", "lastName", "age", "phoneNumber");
        add(grid);
    }



}

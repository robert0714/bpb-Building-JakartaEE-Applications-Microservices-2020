package com.bpb.book.vaadin;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("menubar")
public class ExampleMenuBar extends VerticalLayout {

    public ExampleMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        Text selected = new Text("");
        Div message = new Div(new Text("Selected: "), selected);

        MenuItem home = menuBar.addItem("Home");
        MenuItem product = menuBar.addItem("Product");
        MenuItem account = menuBar.addItem("Account");
        menuBar.addItem("Sign Out", e -> selected.setText("Sign Out"));

        SubMenu homeSubMenu = home.getSubMenu();
        MenuItem dashboard = homeSubMenu.addItem("Dashboard");

        SubMenu prodcutSubMenu = product.getSubMenu();
        MenuItem sales = prodcutSubMenu.addItem("Sales");
        MenuItem billing = prodcutSubMenu.addItem("Billing");

        dashboard.getSubMenu().addItem("Dashboard",
                e -> selected.setText("Dashboard"));

        SubMenu usersSubMenu = sales.getSubMenu();
        usersSubMenu.addItem("Find", e -> selected.setText("Find"));
        usersSubMenu.addItem("Add", e -> selected.setText("Add"));
        usersSubMenu.addItem("Delete", e -> selected.setText("Delete"));

        SubMenu billingSubMenu = billing.getSubMenu();
        billingSubMenu.addItem("Invoices", e -> selected.setText("Invoices"));
        billingSubMenu.addItem("Balance Events",
                e -> selected.setText("Balance Events"));

        account.getSubMenu().addItem("Edit Profile",
                e -> selected.setText("Edit Profile"));
        account.getSubMenu().addItem("Privacy Settings",
                e -> selected.setText("Privacy Settings"));

        add(menuBar,message );
    }
}

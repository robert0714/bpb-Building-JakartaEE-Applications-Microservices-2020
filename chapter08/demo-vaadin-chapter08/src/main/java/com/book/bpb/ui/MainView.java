package com.book.bpb.ui;

import com.book.bpb.ui.views.rolelist.roleList;
import com.book.bpb.ui.views.userlist.userList;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Route("")
@PWA(name = "Project Base for Vaadin Flow with CDI", shortName = "Project Base")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainView extends Div implements RouterLayout, PageConfigurator {

        public MainView() {

            H2 title = new H2("esConference");
            title.addClassName("main-layout__title");

            RouterLink users = new RouterLink(null, userList.class);
            users.add(new Icon(VaadinIcon.LIST), new Text("Users"));
            users.addClassName("main-layout__nav-item");
            // Only show as active for the exact URL, but not for sub paths
            users.setHighlightCondition(HighlightConditions.sameLocation());


            RouterLink roles = new RouterLink(null, roleList.class);
            roles.add(new Icon(VaadinIcon.ARCHIVES), new Text("Roles"));
            roles.addClassName("main-layout__nav-item");

            Div navigation = new Div(users,roles );
            navigation.addClassName("main-layout__nav");

            Div header = new Div(title,navigation);
            header.addClassName("main-layout__header");
            add(header);

            addClassName("main-layout");

        }

        @Override
        public void configurePage(InitialPageSettings settings) {
            settings.addMetaTag("apple-mobile-web-app-capable", "yes");
            settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
        }

}

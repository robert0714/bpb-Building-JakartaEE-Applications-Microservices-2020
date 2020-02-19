package com.bpb.book.vaadin;

import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("lumo")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class ExampleLumo extends VerticalLayout {

    public ExampleLumo()
    {
        LoginForm component = new LoginForm();
        component.addLoginListener(e -> {
            boolean isAuthenticated = authenticate(e);
            if (isAuthenticated) {
                navigateToMainPage();
            } else {
                component.setError(true);
            }
        });
        add(component);
    }
    @SuppressWarnings("unused")
    private boolean authenticate(AbstractLogin.LoginEvent e) {
        return false;
    }
    private void navigateToMainPage() {
    }
}

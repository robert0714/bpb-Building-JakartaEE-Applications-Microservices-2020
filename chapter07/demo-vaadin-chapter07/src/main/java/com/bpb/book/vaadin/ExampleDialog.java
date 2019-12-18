package com.bpb.book.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("dialog")
public class ExampleDialog extends VerticalLayout {

    public ExampleDialog() {

        Button button = new Button("Open Dialog");
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        Label messageLabel = new Label();
        NativeButton confirmButton = new NativeButton("Confirm", event -> {
            messageLabel.setText("Confirmed!");
            dialog.close();
        });
        NativeButton cancelButton = new NativeButton("Cancel", event -> {
            messageLabel.setText("Cancelled...");
            dialog.close();
        });
        dialog.add(confirmButton, cancelButton);
        button.addClickListener(e-> dialog.open());
        add(button,messageLabel);
    }
}

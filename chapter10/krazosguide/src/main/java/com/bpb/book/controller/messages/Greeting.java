/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bpb.book.controller.messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author avbravo
 */
@Named("greeting")
@RequestScoped
public class Greeting {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

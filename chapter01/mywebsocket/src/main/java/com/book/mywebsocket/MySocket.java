package com.book.mywebsocket;


import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class MySocket implements Serializable {

    Integer count = 0;
    @Inject
    @Push(channel = "notification")
    private PushContext push;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    
    public void pushAction() {
        try {
            count++;
            push.send(count);
        } catch (Exception e) {
            JsfUtil.error(e.getLocalizedMessage());
        }
    }

  
}

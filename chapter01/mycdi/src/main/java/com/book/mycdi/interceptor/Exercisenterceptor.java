/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mycdi.interceptor;

import com.book.mycdi.model.Person;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author avbravo
 */
@Exercise
@Interceptor
public class Exercisenterceptor {

    @Inject
    Person person;

    @AroundInvoke
    public Object authorize(InvocationContext ic) throws Exception {
              try {
            if (!person.getWalk()) {
                return ic.proceed();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw e;
        }

    }
}

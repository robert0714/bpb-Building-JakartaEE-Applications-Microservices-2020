/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.book.mycdi.interceptor;

import com.book.mycdi.model.Dog;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author avbravo
 */
@Feed
@Interceptor
public class FeedInterceptor {

    @Inject
    Dog dog;

    @AroundInvoke
    public Object authorize(InvocationContext ic) throws Exception {
              try {
            if (!dog.getHunger()) {
                return ic.proceed();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw e;
        }

    }
}

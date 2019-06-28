package com.book.myjpa;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-12T23:16:09")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, String> productCode;
    public static volatile SingularAttribute<Products, Integer> quantity;
    public static volatile SingularAttribute<Products, Integer> productID;
    public static volatile SingularAttribute<Products, BigDecimal> price;
    public static volatile SingularAttribute<Products, String> name;

}
package com.example.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderedProduct {
    private final String productId;
    private final String productName;
    private int quantity;
    private double price;

    // here we need to provide @JsonProperty for all the fields
    // without the @JsonProperty, jackson can't identify which json
    // field should match to which constructor parameter
    @JsonCreator
    public OrderedProduct(
            @JsonProperty("productId") String productId,
            @JsonProperty("productName") String productName,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("price") double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    /*
     * we can also use this constructor also,
     * the main purpose of the constructor is to provide a way for jackson
     * to convert json to object. by default this is done by the no-arg constructor
     * But if we use final field in the constructor, we can't create no-arg constructor.
     * that's why we need to specifically create a constructor with @JsonCreator annotation
     * to tell jackson the way to covert json to object.
     */
//    @JsonCreator
//    public OrderedProduct(
//            @JsonProperty("productId") String productId,
//            @JsonProperty("productName") String productName
//    ) {
//        this.productId = productId;
//        this.productName = productName;
//    }
}

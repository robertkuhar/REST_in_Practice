package com.restbucks.ordering.domain;

import javax.xml.bind.annotation.XmlEnumValue;

public enum Drink {
    @XmlEnumValue(value="espresso")
    ESPRESSO(1.5),
    @XmlEnumValue(value="latte")
    LATTE(2.0),
    @XmlEnumValue(value="cappuccino")
    CAPPUCCINO(2.0),
    @XmlEnumValue(value="flatWhite")
    FLAT_WHITE(2.0);

    // price support
    final double price;
    Drink(double price) { this.price = price; }
    public double getPrice() { return this.price; }
 }

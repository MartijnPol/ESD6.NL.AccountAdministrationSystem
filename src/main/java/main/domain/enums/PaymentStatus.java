package main.domain.enums;

import java.io.Serializable;

/**
 * @author Thom van de Pas on 4-4-2018
 */
public enum PaymentStatus implements Serializable {

    PAID("Paid"),
    OPEN("Open"),
    CANCELLED("Cancelled");

    private final String name;

    PaymentStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
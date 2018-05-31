package main.domain.enums;

import java.io.Serializable;

/**
 * @author Thom van de Pas on 30-5-2018
 */
public enum RoadType implements Serializable {

    A("A"),
    N("N");

    private final String name;

    RoadType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

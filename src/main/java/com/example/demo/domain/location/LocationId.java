package com.example.demo.domain.location;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LocationId implements Serializable {
    private final String value;
    
    // spring data jpa constructor
    private LocationId() {
        this.value = null;
    }

    private LocationId(String aValue) {
        this.value = aValue;
    }

    public static LocationId from(String aValue) {
        return new LocationId(aValue);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        LocationId that = (LocationId) obj;
        return obj.equals(that.value);
    }
}
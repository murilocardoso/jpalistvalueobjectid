package com.example.demo.domain.person;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonId implements Serializable {
    private final String value;
    
    // spring data jpa constructor
    private PersonId() {
        this.value = null;
    }

    private PersonId(String aValue) {
        this.value = aValue;
    }

    public static PersonId from(String aValue) {
        return new PersonId(aValue);
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
        PersonId that = (PersonId) obj;
        return obj.equals(that.value);
    }
}

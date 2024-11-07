package com.example.demo.domain.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import org.springframework.data.domain.Persistable;

import com.example.demo.domain.location.Location;
import com.example.demo.domain.location.LocationId;

import jakarta.persistence.Version;

@Entity
@Table(name = "person")
public class Person implements Persistable<PersonId> {
    
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id", nullable = false))
    private final PersonId id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "person_location",
        joinColumns = { 
            @JoinColumn(name = "person_id"),
        }
    )
    @AttributeOverride(name = "value", column = @Column(name = "location_id"))
    private final Collection<LocationId> locations;

    // some other attributes
    
    @Version 
    private Long version;

    // spring data jpa constructor
    private Person() {
        this.id = null;
        this.locations = Collections.emptyList();        
    }

    private Person(PersonId anId, Collection<LocationId> someLocations)  {
        this.id = anId;
        this.locations = someLocations;
    }

    public static Person from(PersonId anId, Collection<Location> someLocations) {
        return new Person(anId, new ArrayList<>(someLocations.stream().map(Location::getId).collect(Collectors.toList())));
    }

    public void addLocation(Location alocation) {
        // some check
        this.locations.add(alocation.getId());
    }

    public void removeLocation(Location alocation) {
        // some check
        this.locations.removeIf(l -> l.equals(alocation.getId()));
    }

    public PersonId getId() {
        return this.id;
    }

    public Collection<LocationId> getLocations() {
        return Collections.unmodifiableCollection(this.locations);
    }

    @Override
    public boolean isNew() {
        return ((this.version == null) || (this.version == 0));
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        Person that = (Person) obj;
        return obj.equals(that.id);
    }
}
package com.example.demo.domain.location;

import org.springframework.data.domain.Persistable;
import jakarta.persistence.Version;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location implements Persistable<LocationId> {
    
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id", nullable = false))
    private final LocationId id;

    // some other attributes
    
    @Version 
    private Long version;
    
    // spring data jpa constructor
    private Location() {
        this.id = null;
    }

    private Location(LocationId anId) {
        this.id = anId;
    }

    public static Location from(LocationId anId) {
        return new Location(anId);
    }

    public LocationId getId() {
        return this.id;
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
        Location that = (Location) obj;
        return obj.equals(that.id);
    }
}